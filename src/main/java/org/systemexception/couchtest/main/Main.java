package org.systemexception.couchtest.main;

import org.lightcouch.CouchDbClient;
import org.lightcouch.Response;
import org.systemexception.couchtest.pojo.Data;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author leo
 * @date 24/10/15 22:42
 */
public class Main {

	public static void main(String[] args) {

		String id = "";

		// INSERT DATA IN PRIMARY NODE (192.168.1.27, couchA)
		CouchDbClient dbClientCouchA = new CouchDbClient(
				"test_replicate", //dbName
				false,  //create db if not exists
				"http", //protocol, http or https
				"192.168.1.27", //obvious
				5984,   //port
				null,   //username
				null   //password
		);

		for (int i = 0; i < 1000; i++) {
			Data data = new Data();
			data.setDataCounter(i);
			Response response = dbClientCouchA.saveAttachment(data.getInputStream(), data.getDataCounter() + ".png",
					"image/png");
			id = response.getId();
			System.out.println("Saved: " + data.getDataCounter() + ", " + id);
		}

		dbClientCouchA.shutdown();

//		// Give time to sync
//		try {
//			System.out.println("Waiting for replication");
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		if (isReachable("192.168.1.27")) {
//			// FETCH DATA FROM SECONDARY NODE (192.168.1.28, couchB)
//			CouchDbClient dbClientCouchB = new CouchDbClient(
//					"test_replicate", //dbName
//					false,  //create db if not exists
//					"http", //protocol, http or https
//					"192.168.1.27", //obvious
//					5984,   //port
//					null,   //username
//					null   //password
//			);
//
//			Data foundData = dbClientCouchB.find(Data.class, id);
//			System.out.println("Found data in replicated node: " + foundData.getDataCounter());
//			dbClientCouchB.shutdown();
//
//			assert (foundData.equals(data));
//		} else {
//			System.out.println("Replicator node not reachable");
//		}
	}

	private static boolean isReachable(String ip) {
		try {
			return InetAddress.getByName(ip).isReachable(500);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
