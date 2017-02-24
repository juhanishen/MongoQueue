package exer.mongoqueue.juhani.demo;

import java.util.Iterator;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DemoSub extends Thread {
	private MongoClient client;
	private DB db;
	private DBCollection cappedCol;

	public DemoSub() {
		client = new MongoClient();
		db = client.getDB(DemoConstants.DBName);
		cappedCol = db.getCollection(DemoConstants.CappedCollectionName);
	}

	public DBCursor getCursor(DBCollection collection, String topic, int last_id) {
		DBObject options = new BasicDBObject().append(DemoConstants.cappedColOptionTailableFN, true)
				.append(DemoConstants.cappedColOptionAwaitDataFN, true)
				.append(DemoConstants.cappedColTopicFN, true)
				.append(DemoConstants.cappedColNameFN,true)
				.append(DemoConstants.cappedColCursorFN, true);
		
		DBObject index = new BasicDBObject("$gt", last_id);
		BasicDBObject ts = new BasicDBObject(DemoConstants.cappedColCursorFN,index);
		
		DBObject spec = ts.append(DemoConstants.cappedColTopicFN,
				topic);
		DBCursor cur = collection.find(spec, options);
		cur = cur.addOption(8);
		return cur;
	}

	public void run() {
		int last_id = -1;
		while (true) {
			DBCursor cur = getCursor(cappedCol, DemoConstants.Topic, last_id);
			Iterator<DBObject> it = cur.iterator();
			while (it.hasNext()) {
				DBObject obj = it.next();
				System.out.println("name is:"+(String) obj.get(DemoConstants.cappedColNameFN));
				try{
				    last_id= (int) ((Double)obj.get(DemoConstants.cappedColCursorFN)).doubleValue();
				}catch(ClassCastException ce){
				    last_id= ((Integer)obj.get(DemoConstants.cappedColCursorFN)).intValue();
				}
				System.out.println("last index is:"+last_id);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
