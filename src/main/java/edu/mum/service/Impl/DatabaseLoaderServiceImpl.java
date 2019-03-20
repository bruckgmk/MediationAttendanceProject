package edu.mum.service.Impl;

import edu.mum.domain.Session;
import edu.mum.service.DatabaseLoaderService;
import edu.mum.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
public class DatabaseLoaderServiceImpl implements DatabaseLoaderService {
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private SessionService sessionService;


	@Override
	public synchronized void loadScannedBarcodesToDatabase() throws Exception {

		List<Session> barcodes = sessionService.getBarcodeRecordsList();
		System.out.println("OKKKK++++++ "+barcodes.get(1));

	
	}


	
}
