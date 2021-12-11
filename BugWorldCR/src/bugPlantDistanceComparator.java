import java.util.Comparator;

public class bugPlantDistanceComparator implements Comparator<BugPlantPairs> {


	@Override
	public int compare(BugPlantPairs o1, BugPlantPairs o2) {
		return Integer.compare(o1.getDistance(), o2.getDistance());
	} 

}
