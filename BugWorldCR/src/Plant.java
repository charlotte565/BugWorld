import java.util.Comparator;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant implements Entity {
	protected int value;
	protected int x;
	protected int y;
	protected Image image;
	protected ImageView view;
	
	public Plant(int value, int x, int y) {
		this.value = value;
		this.x = x;
		this.y = y;
		this.image = new Image(getClass().getResourceAsStream("grass.png"), World.getImagewidth(), World.getImagewidth(), true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
	}
	
	//second constructor is for a randomly generated plant object (random x and y coordinates)
	public Plant(int value) {
		this.value = value;
		generateCoordinates();
		this.image = new Image(getClass().getResourceAsStream("grass.png"), World.getImagewidth(), World.getImagewidth(), true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
	}
	
	public void generateCoordinates() {
		World.generatedCoordinates(this);
	}

	//A plant grows 1 value each frame, once it reaches 60 it creates a new plant next to it
	//by calling the newPlant method and the plant is then set back to value 1
	public void growPlant() {
		if(this.value + 1 <= 60)
		this.value = this.value + 1;
		else {
		Plant plant = newPlant();
		if(plant!=null) {
		Main.addPlantRoot(plant);
		setValue(1);
		}
		}
	}

	//this creates a new plant object next to the original plant in a random direction
	public Plant newPlant() {
		Plant plant = null;
		double randomD = Math.random();
		if(randomD < 0.25) {
			if(World.checkCoordinates(x-1, y)) {
			plant = new Plant(1, x -1,y );
			}
		}
		else if(randomD < 0.5) {
			if(World.checkCoordinates(x+1, y)) {
				plant = new Plant(1, x + 1, y);
	
			}
		}
		else if(randomD < 0.75) {
			if(World.checkCoordinates(x, y-1)) {
				plant = new Plant(1, x, y-1);
	
			}
		}
		else {
			if(World.checkCoordinates(x, y+1)) {
				plant = new Plant(1, x, y+1);
	
			}
		}
		return plant;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
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
	
	@Override
	public String toString() {
		return "Plant [value=" + value + ", x=" + x + ", y=" + y + "]";
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


}
