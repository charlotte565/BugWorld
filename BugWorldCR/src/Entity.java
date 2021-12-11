import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public interface Entity {
	public int getX();

	public void setX(int x) ;

	public int getY() ;

	public void setY(int y) ;

	public Image getImage() ;

	public void setImage(Image image);

	public ImageView getView() ;

	public void setView(ImageView view);
}
