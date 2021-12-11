
public class BugPlantPairs implements Container<Bug, Bug>{
	private Bug bug ;
	private Plant plant;
	private int distance;
	
	public BugPlantPairs(Bug bug, Plant plant){
		this.bug = bug;
		this.plant = plant;
		this.distance = calculateDistance(bug, plant);
	}
	  
	@Override
	public String toString() {
		return "Bug 1 "  + bug.name + ", Plant " + plant + " distance " + distance +".";
	}

	@Override
	public Bug getT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bug getP() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int calculateDistance(Bug bug, Plant plant) {
		int distance = Math.abs(bug.x - plant.x) + Math.abs(bug.y - plant.y);
		
		return distance;
	}

	public Bug getBug() {
		return bug;
	}

	public void setBug(Bug bug) {
		this.bug = bug;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

}

