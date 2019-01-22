import java.util.Random; 

public class addCustomers extends Simulator{
	
	void start(){
		Random rn = new Random();
		int counter = 1; 
		int totalNum = 0; 
		while(counter <= 100){
			int rand = rn.nextInt(100);
			
			MasterController.timeP += ExpRand.exponential(.1);
			//each customer arrives at floor 
			
			//Job J = new Job(P, P.direction, P.source);
			//mainQ.push(J); 
			if(!(rand < 50)){
				Person P = new Person(counter, MasterController.timeP);
				MasterController.floorStatus.get(P.source).addPatron(P);
				
				counter++;
				
			}
			//events = MasterController.floorStatus.get(0).downQ; 
			
			

			
			
		}
//		for(int i = 0; i < MasterController.NUM_FLOORS; i++){
//			System.out.println("Size of up " + i + ": " + MasterController.floorStatus.get(i).upQ.size());
//			totalNum+=MasterController.floorStatus.get(i).upQ.size();
//			System.out.println("Size of down " + i + ": " + MasterController.floorStatus.get(i).downQ.size());
//			totalNum+=MasterController.floorStatus.get(i).downQ.size(); 
//		}
		//System.out.println("totalNum: " + totalNum);
		
		//doAllEvents(); 
	}

}
