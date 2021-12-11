import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bug implements Comparable<Bug>, Entity {
	protected String species;
	protected String name;
	protected char value;
	protected int x;
	protected int y;
	protected int energy;
	protected int ID;  
	protected int foodP;
	protected Image image;
	protected ImageView view;
	protected int smell;

	public Bug(String species, String name, char a, int energy, int ID) {
		this.species = species;
		this.name = name;
		this.value = a;
		generateCoordinates();
		this.energy = energy;
		this.ID = ID;
		this.smell = 5;
		
		this.image = new Image(getClass().getResourceAsStream("ant.png"), World.getImagewidth(), World.getImagewidth(),
				true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
	}
	
	public void generateCoordinates() {
		World.generatedCoordinates(this);
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getValue() {
		return value;
	}

	public void setValue(char a) {
		this.value = a;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	@Override
	public String toString() {
		return "Species: " + species + ", Name: " + name + ", Symbol: " + value + ", Horizontal Position: " + x
				+ ", Vertical Position: " + y + ", Energy: " + energy + ", ID: " + ID + ".";
	}

	public String toText() {
		return "Bug [species=" + species + ", name=" + name + ", value=" + value + ", x=" + x + ", y=" + y + ", energy="
				+ energy + ", ID=" + ID + "]";
	}

//	public String toString() {
//		return "Species: " + species + ", Horizontal Position: " + x
//				+ ", Vertical Position: " + y ;
//	}


	@Override
	public int compareTo(Bug o) {
		return Integer.compare(getID(), o.getID());
	}

	public int getFoodP() {
		return foodP;
	}

	public void setFoodP(int foodP) {
		this.foodP = foodP;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public ImageView getView() {
		return view;
	}

	public void setView(ImageView view) {
		this.view = view;
	}

	public int getSmell() {
		return smell;
	}

	public void setSmell(int smell) {
		this.smell = smell;
	}


}
