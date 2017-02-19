package exer.mongoqueue.juhani.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class MongoSequence {
   private final static MongoSequence sequence =new MongoSequence();
   private MongoClient mongoClient;
   private DB db;
   private DBCollection seqCol;
   
   public static synchronized MongoSequence getInstance(){
	   return sequence;
   }
  
   private MongoSequence(){	   
	   mongoClient = new MongoClient();
	   db = mongoClient.getDB(DemoConstants.DBName);
	   seqCol = db.getCollection(DemoConstants.SequenceCollectionName);
   }
   
   public int getNextSeq(String topic){
	   BasicDBObject query =new BasicDBObject(DemoConstants.SequenceKeyFN,topic);
	   BasicDBObject update  = new BasicDBObject("$inc",new BasicDBObject(DemoConstants.SequenceValueFN,1));
	   BasicDBObject result = (BasicDBObject) seqCol.findAndModify(query,update);
	   int value = ((Integer)result.get(DemoConstants.SequenceValueFN)).intValue();
       return value;   
   }   
}
