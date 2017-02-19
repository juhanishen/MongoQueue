package exer.mongoqueue.juhani.demo;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.Connection;

public class DemoPub {
	
	private MongoClient client;
	private MongoDatabase db;
	
	public DemoPub(){
		client = new MongoClient();
		db = client.getDatabase(DemoConstants.DBName);
	}
	
	public DBCursor getCursor(DBCollection collection, String topic, 
			                                     int last_id, String topicName){
		DBObject options = new BasicDBObject().append("tailable",true).
				                                    append("await_data",true);
		DBObject ts= new BasicDBObject("$gt",last_id);
		DBObject spec = new BasicDBObject().append("ts",ts).append("k", topicName);
		DBCursor cur = collection.find(spec, options);
		cur = cur.addOption(8);
		return cur;
	}
	
	
}
