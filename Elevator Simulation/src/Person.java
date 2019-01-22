import java.util.Random; 

public class Person extends Event {
		
	public int personID = 0; 
	public boolean direction = false;
	public int source = 0; 
	public int destination = 0; 
	public double time = 0; 
	
	public Person(int pID,double time){
		this.time = time; 

		this.personID = pID; 
		generateRandom(); 
	}

	void execute(AbstractSimulator simulator) {
		//System.out.println("[Time: " + time + "] Person " + personID + ": " + source + ", " + destination + ", " + direction);
		
	}
	
	public void generateRandom(){
		
		Random rn = new Random(); 
		int randFloor = rn.nextInt(100);
		if(randFloor < 50){
			while(source==0){
				this.source = rn.nextInt(MasterController.NUM_FLOORS);
			}	
		}
		
		//System.out.println("source: " + source); 
		destination = rn.nextInt(MasterController.NUM_FLOORS);
		while(source == destination){
			destination = rn.nextInt(MasterController.NUM_FLOORS);
		}
		if(source < destination){
			this.direction = true;  //going up  
		}
		else{
			this.direction = false; //going down
		}
		MasterController.floorRatio[this.source][this.destination] += 1; 
		//System.out.println("[Time: " + time + "] Person " + personID + ": " + source + ", " + destination + ", " + direction);
		
	}
	
}
