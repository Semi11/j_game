import processing.core.PApplet;
import java.applet.Applet;

public class ActionGame extends Applet{
    private PApplet app;
    private GameObjectManager gm;
    private String data_path;
    
    public ActionGame(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	addKeyListener(InputManager.INSTANCE);
	gm = new GameObjectManager(app, data_path);
	gm.addGameObject(0, 570, 100);
    }

    public void update(){
	app.background(0);

	gm.update();
	gm.draw();
    }

}
