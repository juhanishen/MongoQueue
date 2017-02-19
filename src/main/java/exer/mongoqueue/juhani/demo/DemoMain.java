package exer.mongoqueue.juhani.demo;

public class DemoMain {

	public static void main(String[] args) {
		new DemoPub().publish();	
		new DemoSub().start();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        new DemoPub().publish();	
        new DemoPub().publish();	
	}
}
