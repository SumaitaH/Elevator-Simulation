import java.util.LinkedList; 

 

public class Job {
	public static int jobNum = 0;
	public int jobID = 0; 
	public LinkedList<Person> floorQ = new LinkedList<Person>(); 
	//public Person P; 
	public boolean direction = false;
	public int floorNum = 0; 
	
	
	public Job (LinkedList<Person> queue, boolean dir, int fNum){
		jobID = jobNum; 
		jobNum++; 	
		this.floorQ = queue; 
		this.direction = dir; 
		this.floorNum = fNum; 
	}
	public Job (int fNum, boolean dir){
		this.floorNum = fNum; 
		this.direction = dir; 
	}

}

