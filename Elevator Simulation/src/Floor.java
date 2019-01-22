import java.util.LinkedList; 

class Floor {

	public int floorNum =0;
	public boolean upButton = false; 
	public boolean downButton = false; 
	public LinkedList<Person> upQ = new LinkedList<Person>(); 
	public LinkedList<Person> downQ = new LinkedList<Person>(); 

	public Floor(int fNum){
		this.floorNum = fNum; 
	}

	public void addPatron(Person p){
		if(p.direction==false){
			//downButton = true;
			downQ.add(p);
		}
		else {
			//upButton = true; 
			upQ.add(p); 
		}
	}
	public void createJobUp(LinkedList<Person> addP){
		Job currJob = new Job(upQ, true, floorNum);
		MasterController.mainQ.add(currJob); 


	}
	public void createJobDown(LinkedList<Person> addP){
		Job currJob = new Job(downQ,false, floorNum); 
		MasterController.mainQ.add(currJob); 
	}
	
	//At each second, FQ checks if there are patrons and sets button to "TRUE"
	public void setBool(double timer){
		if(!(upQ.isEmpty())&& upQ.getFirst().time <= timer){
			upButton = true; 
			Job upJob = new Job (floorNum, upButton);
			MasterController.mQ.add(upJob); 
		}
		if(!(downQ.isEmpty())&& downQ.getFirst().time <= timer){
			downButton = true; 
			Job downJob = new Job(floorNum, upButton);
			MasterController.mQ.add(downJob);
		}
	}
	public void sendJob(double timer){
		//System.out.println("do you come here0?");
		LinkedList<Person> addUp = new LinkedList<Person>(); 
		LinkedList<Person> addDown = new LinkedList<Person>(); 

		while(!(upQ.isEmpty()) && upQ.getFirst().time <=timer){

			//System.out.println("Floor id: " + floorNum +  " id: " + upQ.getFirst().personID);

			addUp.add(upQ.removeFirst());
		}
		while(!(downQ.isEmpty()) && downQ.getFirst().time <=timer){

			//System.out.println("Floor id: " + floorNum +  " id: " + downQ.getFirst().personID);

			addDown.add(downQ.removeFirst());
		}
		//System.out.println("do you come here:)?");
		if(!addUp.isEmpty()){
			//upButton = true;
			//System.out.println("do you come herez?");
			createJobUp(addUp);
		}

		if(!addDown.isEmpty()){
			//downButton = true;
			//System.out.println("do you come herez?");
			createJobDown(addDown);
		}


	}

}