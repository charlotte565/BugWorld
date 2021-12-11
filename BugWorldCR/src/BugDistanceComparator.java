import java.util.Comparator;

public class BugDistanceComparator implements Comparator<BugPairs> {


	@Override  
	public int compare(BugPairs o1, BugPairs o2) {
	
		return Integer.compare(o1.getDistance(), o2.getDistance());
	} 

}
