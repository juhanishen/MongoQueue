package exer.mongoqueue.juhani.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSequence {
   private final static MongoSequence sequence =new MongoSequence();
   private MongoClient mongoClient;
   private MongoDatabase db;
   private MongoCollection seqCol;
   
   public static synchronized MongoSequence getInstance(){
	   return sequence;
   }
  
   private MongoSequence(){	   
	   mongoClient = new MongoClient();
	   db = mongoClient.getDatabase(DemoConstants.DBName);
	   seqCol = db.getCollection(DemoConstants.SequenceCollectionName);
   }
   
   public int getNextSeq(String topic){
 	/*   doc = self._db[self._name].find_and_modify(
	            query={'_id': sname},
	            update={'$inc': { 'value': inc } },
	            upsert=True,
	            new=True),
   */
	   DBObject query =new BasicDBObject(DemoConstants.SequenceKeyFieldName,topic);
	   DBObject udpate  = new BasicDBObject("$inc",new BasicDBObject("value",1));
	   
       return 1;   
   }
   
   
}
