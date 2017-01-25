import processing.core.PApplet;
import java.util.Deque;
import java.util.ArrayDeque;

public class ActionGame {
    private PApplet app;
    private String data_path;
    private Scean scean;
    private Deque<Scean> scean_stack = new ArrayDeque<Scean>();
    
    public ActionGame(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	this.pushScean(new TitleScean(this,app,path));
	//this.scean = new GameScean(this,app,path);
    }

    public void update(){
	app.background(0);

	if(scean_stack.size()==0)endGame();
	
	scean = scean_stack.getFirst();
	if(!scean.update())scean_stack.pop();
	scean.draw();
	
	InputManager.INSTANCE.update();
    }

    public void pushScean(Scean s){
	scean_stack.push(s);
    }

    public void endGame(){
	System.exit(0);
    }
}
