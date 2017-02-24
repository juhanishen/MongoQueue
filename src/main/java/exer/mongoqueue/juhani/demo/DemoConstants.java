package exer.mongoqueue.juhani.demo;

class DemoConstants {
	public static final String DBName = "test";
	public static final String Topic = "topic1";
    
	public static final String CappedCollectionName = "capped_col";
    public static final String cappedColNameFN= "name";
    public static final String cappedColTopicFN = "topic";
    public static final String cappedColOptionTailableFN ="tailable";
    public static final String cappedColOptionAwaitDataFN = "await_data";
    public static final String cappedColCursorFN ="ts"; //shall not change in anyway
    
    //db.pub_sub_seq.insert({¡°topic¡±:"topic1","value":NumberInt(0)})  
    public static final String SequenceCollectionName = "pub_sub_seq";
    public static final String SequenceKeyFN = "topic";
    public static final String SequenceValueFN = "value";
    		
    		
    		

}
