import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Grasshopper extends Bug {
//	protected String species = "Grasshopper";
//	protected char a = 'G';
	


	public Grasshopper(String name,   int energy, int ID) {
		super("Grasshopper", name, 'G', energy, ID);
		this.image = new Image(getClass().getResourceAsStream("grasshopper.png"), World.getImagewidth(), World.getImagewidth(), true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
		this.foodP = 0;
		this.smell = 15;
	}


}
