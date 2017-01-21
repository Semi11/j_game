import processing.core.PApplet;

public class ActionGame {
    private PApplet app;
    private GameObjectManager gm;
    private String data_path;
    
    public ActionGame(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	gm = new GameObjectManager(app, data_path);
    }

    public void update(){
	app.background(0);

	gm.update();
	gm.draw();

	InputManager.INSTANCE.update();
    }

}
