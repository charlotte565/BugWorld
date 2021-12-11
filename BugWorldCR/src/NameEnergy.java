
public class NameEnergy implements Container<String, Integer>{
	
	private String name;
	private int energy;
	
	public NameEnergy(String name, int energy){
		this.name = name;
		this.energy = energy;
	}
	


	@Override
	public String toString() {
		return "Name" + name + ", Energy" + energy + ".";
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getEnergy() {
		return energy;
	}



	public void setEnergy(Integer energy) {
		this.energy = energy;
	}


  
	@Override
	public String getT() {
		return name;
	}



	@Override
	public Integer getP() {
		return energy;
	}


}
