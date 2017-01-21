import processing.core.PApplet;

public class ActionGame {
    private PApplet app;
    private String data_path;
    private Scean scean;
    
    public ActionGame(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	this.scean = new GameScean(app,path);
    }

    public void update(){
	app.background(0);
	
	scean.update();
	scean.draw();
	
	InputManager.INSTANCE.update();
    }

}
