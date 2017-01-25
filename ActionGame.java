import processing.core.PApplet;
import java.util.Deque;
import java.util.ArrayDeque;

public class ActionGame {
    private PApplet app;
    private String data_path;
    private Scean scean;
    private Deque<Scean> scean_stack = new ArrayDeque<Scean>();
    private Scean tmp_scean;
    
    public ActionGame(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	this.pushScean(new TitleScean(this,app,path));
    }

    public void update(){
	app.background(0);
	
	if(tmp_scean!=null){
	    scean_stack.push(tmp_scean);
	    tmp_scean = null;
	}

	if(scean_stack.size()==0)endGame();
	
	scean = scean_stack.getFirst();
	if(!scean.update())scean_stack.pop();
	scean.draw();

	InputManager.INSTANCE.update();
    }

    public void pushScean(Scean s){
	this.tmp_scean = s;
    }

    public void endGame(){
	System.out.println("endGame");
	System.exit(0);
    }
}
