package org.systemexception.couchtest.main;

import org.lightcouch.CouchDbClient;
import org.systemexception.couchtest.pojo.Data;

import java.io.*;
import java.util.List;

/**
 * @author leo
 * @date 25/10/15 21:04
 */
public class Client {

	public static void main(String[] args) throws IOException {
		CouchDbClient dbClient = new CouchDbClient("test_replicate", //dbName
				false,  //create db if not exists
				"http", //protocol, http or https
				"192.168.1.27", //obvious
				5984,   //port
				null,   //username
				null   //password
		);

		List<Data> dataList = dbClient.view("_all_docs").query(Data.class);

		for (Data data: dataList) {
			int dataCounter = data.getDataCounter();
			InputStream dataStream = data.getInputStream();
			byte[] buffer = new byte[dataStream.available()];
			dataStream.read(buffer);
			File targetFile = new File("target" + File.separator + dataCounter + ".png");
			OutputStream outStream = new FileOutputStream(targetFile);
			outStream.write(buffer);
			dataStream.close();
			outStream.close();
			System.out.println(data.getDataCounter());
		}

		System.out.println("Found " + dataList.size() + " items");
	}
}
