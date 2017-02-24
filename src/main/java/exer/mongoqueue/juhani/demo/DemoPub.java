package exer.mongoqueue.juhani.demo;


import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DemoPub {
	
	private MongoClient client;
	private DB db;
	private DBCollection cappedCol;
	
	public DemoPub(){
		client = new MongoClient();
		db = client.getDB(DemoConstants.DBName);
		cappedCol = db.getCollection(DemoConstants.CappedCollectionName);
	}
	
	public void publish(){	
		int i = MongoSequence.getInstance().getNextSeq(DemoConstants.Topic);
		DBObject doc = new BasicDBObject()
				.append(DemoConstants.cappedColTopicFN, DemoConstants.Topic)
				.append(DemoConstants.cappedColNameFN,"name"+i )
		        .append(DemoConstants.cappedColCursorFN,i);
		System.out.println("publisher is going to publish number "+i+"th message");
		cappedCol.insert(doc);
	}
}
