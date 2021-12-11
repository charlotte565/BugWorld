import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Beatle extends Bug {
//	protected String species = "Beatle";
//	protected char value = 'B';
	
	//food preference 0 plants, 1 bugs, 2 both
	

	public Beatle( String name,  int energy, int ID) {
		super("Beatle", name, 'B', energy, ID);
		this.image = new Image(getClass().getResourceAsStream("beetle.png"), World.getImagewidth(), World.getImagewidth(), true, true);
		this.view = new ImageView(image);
		view.setTranslateX(x*World.getImagewidth());
		view.setTranslateY(y*World.getImagewidth());
		this.foodP = 1;
		this.smell = 7;
	}


}
