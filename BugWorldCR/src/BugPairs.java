
public class BugPairs implements Container<Bug, Bug>{
	private Bug bug1 ;
	private Bug bug2;
	private int distance;
	
	public BugPairs(Bug bug1, Bug bug2){
		this.bug1 = bug1;
		this.bug2 = bug2;
		this.distance = calculateDistance(bug1, bug2);
	}
	  
	@Override
	public String toString() {
		return "Bug 1 "  + bug1.name + ", Bug 2 " + bug2.name + " distance " + distance +".";
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



	public Bug getBug1() {
		return bug1;
	}



	public void setBug1(Bug bug1) {
		this.bug1 = bug1;
	}



	public Bug getBug2() {
		return bug2;
	}



	public void setBug2(Bug bug2) {
		this.bug2 = bug2;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int calculateDistance(Bug bug1, Bug bug2) {
		int distance = Math.abs(bug1.x - bug2.x) + Math.abs(bug1.y - bug2.y);
		
		return distance;
	}

}
