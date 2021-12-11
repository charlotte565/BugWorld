import java.util.Comparator;

public class BugEnergyComparator implements Comparator<Bug> {

	
	public int compare(Bug o1, Bug o2) {
		
		return Integer.compare(o1.getEnergy(), o2.getEnergy());
	}

}
  