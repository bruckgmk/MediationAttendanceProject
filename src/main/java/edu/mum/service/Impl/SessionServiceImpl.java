package edu.mum.service.Impl;

import edu.mum.domain.Location;
import edu.mum.domain.Schedule;
import edu.mum.domain.Session;
import edu.mum.repository.LocationRepository;
import edu.mum.repository.ScheduleRepository;
import edu.mum.repository.SessionRepository;
import edu.mum.service.LocationService;
import edu.mum.service.ScheduleService;
import edu.mum.service.SessionService;
import edu.mum.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private static final String FILE_PATH = "src/main/resources/barcodes/Bar.txt";

    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Override
    public List<Session> findAll() {
        return (List<Session>)sessionRepository.findAll();
    }
    @Autowired
    private EntityManagerFactory emf;
    @Override
    public Session save(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session findById(Long sessionId) {
        return sessionRepository.findById(sessionId).get();
    }

    @Override
    public void deleteById(Long id) {
sessionRepository.deleteById(id);
    }

    private static volatile Collection<Session> sessions;

    @Override
    @Transactional
    public List<Session> getBarcodeRecordsList() {
        // Default is all dates
        return getBarcodeRecordsList(LocalDate.ofEpochDay(0), LocalDate.now());


    }

    @Override
    @Transactional
    public List<Session> getBarcodeRecordsList(LocalDate startDate, LocalDate endDate) {

        return getSessions()
                .stream()
                .collect(Collectors.toList());
    }

    private Collection<Session> getSessions() {
        if(null == sessions) {
            sessions = loadBarcodeRecords();
        }

        return sessions;
    }
    private synchronized Collection<Session> loadBarcodeRecords() {
        if(null != sessions) {
            return sessions;
        }

        System.out.println("Loading scanned barcode records...");

        File file = new File(FILE_PATH);
        long fileSize = file.length();

        Map<String, Session> dataMap = new HashMap<String, Session>((int)(fileSize/20));

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();

                if(line.isEmpty()) {
                    continue;
                }

                // Removes duplicates
                if(dataMap.containsKey(line)) {
                    System.out.println("Duplicate line found in scanned barcode " + line);
                    continue;
                }
                else {
                    dataMap.put(line,  convertLineToSession(line));
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Session> result = new ArrayList(dataMap.values());
        System.out.println("The list is "+result.size());



        EntityManager em = emf.createEntityManager();

        int index =0;
        em.getTransaction().begin();
        for(Session br : result) {

            em.persist(br);
            if(index % 100==0) {
                System.out.println(".. 100 Tx...");
                em.getTransaction().commit();
                em.getTransaction().begin();
            }
            System.out.println(br);
        }
        em.getTransaction().commit();

        System.out.println("The list is "+result.size());
        return dataMap.values();
    }
    private Session convertLineToSession(String line) {
        String[] parts = line.split(",");
        System.out.println(parts[2] + " "+parts[3]);
        String session = parts[0];
        LocalDate date = DateUtil.convertDateToLocalDate(DateUtil.convertOldFormatStringToDate(parts[1]));
        LocalTime time = LocalTime.of(00, 00);
        Schedule timeslot = scheduleRepository.findByTitle(parts[2]);
        Location location = locationRepository.findByName(parts[3]);
        System.out.println("BarCode: "+parts[0]+" Date: "+date +" time: "+time +" TimeSlote: "+parts[2]+" Location:"+location);

        return new Session(session, date, time, location, timeslot);
    }
}
