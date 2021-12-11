

import java.util.Comparator;

public class PlantSizeComparator implements Comparator<Plant> {

	
	public int compare(Plant o1, Plant o2) {
		
		return Integer.compare(o1.getValue(), o2.getValue());
	}

}
