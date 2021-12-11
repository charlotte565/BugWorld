import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Obstacle implements Entity{
	protected char value = 'Ø';
	protected int x;
	protected int y;

	protected Image image;
	protected ImageView view;

	public Obstacle() {
		generateCoordinates();
		this.image = new Image(getClass().getResourceAsStream("rock.png"), World.getImagewidth(), World.getImagewidth(), true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
	}
	
	public void generateCoordinates() {
		World.generatedCoordinates(this);
	}


	public char getValue() {
		return value;
	}

	public void setValue(char value) {
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
