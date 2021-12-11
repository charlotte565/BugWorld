import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ladybug extends Bug {
//	protected String species = "Ladybug";
//	protected char value = 'L';
	


	public Ladybug(String name, int energy, int ID) {
		super("Ladybug", name, 'L',  energy, ID);
		this.image = new Image(getClass().getResourceAsStream("ladybug.png"), World.getImagewidth(), World.getImagewidth(), true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
		this.foodP = 2;
		this.smell = 9;
	}
	


}