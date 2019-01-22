import java.util.LinkedList;

import java.util.Random;


public class MasterController extends Simulator{
	
	public static int NUM_FLOORS = 10;

	public static int NUM_ELEVS = 4;
	
	public static double timeF = 0; 
	
	public static double timeP = 0; 
	
	public static double time = 0; 
	
	//public static ListQueue Patrons = new ListQueue(); 
	
	public static LinkedList<Job> mainQ = new LinkedList<Job>();
	
	public static LinkedList<Job> mQ = new LinkedList<Job>(); 
	
	public static LinkedList<Job> busyQ = new LinkedList<Job>(); 
	
	public static LinkedList<Elevator> elevStatus = new LinkedList<Elevator>(); 
	
	public static LinkedList<Floor> floorStatus = new LinkedList<Floor>();
	
	public static int[][] floorRatio = new int[NUM_FLOORS][NUM_FLOORS];
	
	//use fRatio to store average wait time
	public static LinkedList<LinkedList<Floor>> fRatio = new LinkedList<LinkedList<Floor>>();
	

	public static void main(String[] args){
		

		for(int i = 0; i <NUM_FLOORS; i++){
			
			floorStatus.add(new Floor(i));
		}
		for(int i = 0; i <NUM_ELEVS; i++){
			elevStatus.add(new Elevator(i,0));
		}
		//adds to individual floor queue
		addCustomers addPersonsEvent = new addCustomers(); 
		addPersonsEvent.start();
		
		
		while(timeF < 10){
			timeF+=0.1;
			for(int i=0; i < floorStatus.size(); i++){
				//floorStatus.get(i).sendJob(timeF);
				floorStatus.get(i).setBool(timeF);		//check if there are patrons on each floor 
				//Master controller assigns job to elevator
				if(floorStatus.get(i).upButton == true){
					removeJob(floorStatus.get(i).floorNum, true); 
				}
				if(floorStatus.get(i).downButton == true){
					removeJob(floorStatus.get(i).floorNum, false);
				}
				
				
			}	
			
		}
		for(int i = 0; i < mainQ.size(); i++){
			System.out.println("JobID: " + mainQ.get(i).jobID);
		}
		
		
	}
//	public static void doEventsAll(){
//		while(!mainQ.isEmpty()){
//			time = mainQ.getFirst().P.time; 
//			removeJob(mainQ.pop());
//			e.execute();
//		}
//	}
	public static void sendJob (int floorNum, boolean dir){
		

	}
	

	public static void removeJob(int floorNum, boolean dir){
		if(Elevator.idle == 3 && isSameFloor()){
			//if all elevators are idle and on the same floor, assign to a random elevator 
			Random rn = new Random();
			int rand =  rn.nextInt(NUM_ELEVS);
			Job J = new Job (floorNum, dir);
			elevStatus.get(rand).currJob.add(J);
			//elevStatus.get(rand).currJob.push(mainQ.pop());

		}
		else{
			int closest = checkClosest(mainQ.getFirst().floorNum, mainQ.getFirst().direction);
			if(closest != -1){
				elevStatus.get(closest).currJob.push(mainQ.pop());
			}
			else
			{
				busyQ.push(mainQ.pop());

			}


		}
		
	}
	
	public static int checkClosest(int floor, boolean dir){
		LinkedList<Elevator> test = elevStatus; 
		//Elevator head = test.getFirst(); 
		int hasJobClosestBusy = -1; 
		int hasJobClosestIdle = -1; 
		
		for(int i = 0; i < test.size(); i++){
			if(test.get(i).direction = dir && dir ==true){ //for going up 
				//if elevator is idle and is within 4 stops away from the person
				if((!test.get(i).hasJob)&&((floor-(test.get(i).getSource())) <= 4 )){
					hasJobClosestIdle = test.get(i).elevID; 
				}
				//if elevator has job and is within 4 stops away from the person
				else if(test.get(i).hasJob && ((floor - (test.get(i).getSource())) <= 4)){
					if(test.get(i).getSource() > hasJobClosestIdle){
					hasJobClosestBusy = test.get(i).elevID; }
				}
				//if elevator is idle and more than 4 stops away from person 
				else if(!test.get(i).hasJob && ((floor - test.get(i).getSource()) > 4)){
					if(test.get(i).getSource() > hasJobClosestIdle){
						hasJobClosestIdle = test.get(i).getSource(); 
					}
				}
				//if elevator is busy and more than 4 stops away from person 
				else if(test.get(i).hasJob && ((floor - test.get(i).getSource())> 4)){
					hasJobClosestBusy = test.get(i).elevID; 
				}
			}
			else if(test.get(i).direction = dir && dir == false){ //for going down 
				//if elevator is idle and is within 4 stops away from the person
				if((!test.get(i).hasJob)&&((test.get(i).getSource() -floor) <= 4 )){
					hasJobClosestIdle = test.get(i).elevID; 
				}
				//if elevator has job and is within 4 stops away from the person
				else if(test.get(i).hasJob && ((test.get(i).getSource() -floor) <= 4)){
					if(test.get(i).getSource() > hasJobClosestIdle){
					hasJobClosestBusy = test.get(i).elevID; }
				}
				//if elevator is idle and more than 4 stops away from person 
				else if(!test.get(i).hasJob && ((test.get(i).getSource() -floor) > 4)){
					if(test.get(i).getSource() > hasJobClosestIdle){
						hasJobClosestIdle = test.get(i).getSource(); 
					}
				}
				//if elevator is busy and more than 4 stops away from person 
				else if(test.get(i).hasJob && ((test.get(i).getSource() - floor) > 4)){
					hasJobClosestBusy = test.get(i).elevID; 
				}
				
			}
			//test.pop(); 
		}
		if(hasJobClosestBusy == -1){
			return hasJobClosestBusy; 
		}
		else if(dir == true){
			if(hasJobClosestIdle >= hasJobClosestBusy){
				return hasJobClosestIdle;
			}
			else
				return hasJobClosestBusy;
			
		}
		else{
			if(hasJobClosestIdle <= hasJobClosestBusy){
				return hasJobClosestIdle;
			}
			else
				return hasJobClosestIdle; 
		}

	}
	public static boolean isSameFloor(){
		LinkedList<Elevator> test = elevStatus; 
		Elevator head = test.getFirst();
		int currFloor = head.getSource(); 
		while(test!=null){
			if(head.getSource()!=currFloor ){
				return false;
			}
			test.pop(); 
				
		}
		return true; 
	}
	
}