
import java.util.LinkedList;



public class Elevator {
	public double time = 0; 
	public static int idle = 3; 
	public static boolean idleAll = true; 
	public int elevID = 0;
	public int source = 0; 
	public int destination = 0; 
	public boolean direction = false;
	public boolean hasJob = false;
	public LinkedList<Job> currJob = new LinkedList<Job>();
	public static LinkedList<Person> patrons = new LinkedList<Person>(); 

	
	public Elevator(int elevNum, int source){
		this.elevID = elevNum; 
		this.source = source;  
	}
	
	public void run(){
		while(true){
			if(!currJob.isEmpty()){
				setIdleFalse();
				getJobIdle(); 
				doJob(); 
			}
			
		}
	}
	
	public void getJobIdle(){
		
			while (!currJob.getFirst().floorQ.isEmpty()){
				direction = currJob.getFirst().direction; 
				//sets max destination 
				if(patrons.getLast().destination > this.destination && direction == true){
					this.destination = patrons.getFirst().destination; 
				}
				//sets min destination 
				else if(patrons.getLast().destination < this.destination && direction == false){
					this.destination = patrons.getFirst().destination; 
				}
				patrons.push(currJob.getFirst().floorQ.pop()); 
					
			}
		
	}

	public void doJob(){
		if(direction){
			 
			while(this.source <= this.destination){
				source++; 
				
				//removes patrons from queue when they reach the correct floor
				int ind = 0;
				Person searchP = patrons.get(ind);
				while(searchP != null){
					if (searchP.destination ==source){
						patrons.remove(searchP);
					}
					searchP = patrons.get(ind++); 
				}
				if(!currJob.isEmpty()){
					int index = 0; 
					Job head = currJob.get(index);
					while(head != null){
						if (head.floorNum == source){
							//FIX THIS
							getJobIdle(); 
							head = currJob.get(index++);
						}
					}
				}
			}
		}
		else if(!direction){
			int ind = 0; 
			while(this.source >= this.destination ){
				source--;
				Person searchP = patrons.get(ind);
				while(searchP != null){
					if (searchP.destination ==source){
						patrons.remove(searchP);
					}
					searchP = patrons.get(ind++); 
				}
				if(!currJob.isEmpty()){
					int index = 0; 
					
					Job head = currJob.get(index);
					while(head != null){
						if (head.floorNum == source){
							//FIX THIS
							getJobIdle(); 
							head = currJob.get(index++);
						}
					}
				}
			}
		}
		//idleAll = false; 
		
	}
	public int getSource(){
		return this.source; 
	}
	public int getDestination(){
		return this.destination; 
	}
	public void setIdleFalse(){
		idleAll = false; 
	}
	
	public void setJob(int source, int destination){
		this.source = source; 
		this.destination = destination; 
	}


	

}





