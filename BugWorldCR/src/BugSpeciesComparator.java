import java.util.Comparator;

public class BugSpeciesComparator implements Comparator<Bug> {

	
	public int compare(Bug o1, Bug o2) {
		
		return o1.getSpecies().compareTo(o2.getSpecies());
	}

}
