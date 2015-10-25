package org.systemexception.couchtest.main;

import com.google.gson.JsonObject;
import org.lightcouch.CouchDbClient;

import java.util.List;

/**
 * @author leo
 * @date 25/10/15 21:31
 */
public class DeleteAll {

	public static void main(String[] args) {
		CouchDbClient couchDbClient = new CouchDbClient("test_replicate", //dbName
				false,  //create db if not exists
				"http", //protocol, http or https
				"192.168.1.27", //obvious
				5984,   //port
				null,   //username
				null   //password
		);

		List<JsonObject> dataList = couchDbClient.view("_all_docs").includeDocs(true).query(JsonObject.class);

		for (JsonObject data: dataList) {
			couchDbClient.remove(data);
			System.out.println("Remove " + data);
		}
	}


}
