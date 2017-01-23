import processing.core.PApplet;

public class ActionGame {
    private PApplet app;
    private String data_path;
    private Scean scean;

    public enum  __SCEAN{
	TITLE,
	GAME,
    }
    
    public ActionGame(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	this.scean = new GameScean(this,app,path);
    }

    public void update(){
	app.background(0);
	
	scean.update();
	scean.draw();
	
	InputManager.INSTANCE.update();
    }

    public void changeScean(__SCEAN s){
	switch(s){
	case TITLE:
	    this.scean = new TitleScean(this,app,data_path);
	    break;
	case GAME:
	    this.scean = new GameScean(this,app,data_path);
	    break;
	default:
	    break;
	}
    }
}
