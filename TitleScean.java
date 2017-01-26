import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class TitleScean implements Scean{
    private final int MENU_NUM = 3;
    private PApplet app;
    private ActionGame ag;
    private String data_path;
    private Drawable drawer;
    private List<Utility> obj_list = new ArrayList<Utility>();
    private List<Utility> menu_list = new ArrayList<Utility>();
    private Utility cursol;
    private int select_num = 0;

    public TitleScean(ActionGame ag,PApplet app, String path){
	this.ag=ag;
	this.app=app;
	this.data_path=path;
	this.drawer = new DrawPImage(app, path, "Extra", new PosInfo(0,0));
	
	Utility title = new Utility(drawer,new Vec2(175,50),new Vec2(672,146),0);
	Utility m1 = new Utility(drawer,new Vec2(350,300),new Vec2(272,64),2);
	Utility m2 = new Utility(drawer,new Vec2(350,400),new Vec2(160,64),1);
	Utility m3 = new Utility(drawer,new Vec2(350,500),new Vec2(128,64),3);
	cursol = new Utility(drawer,new Vec2(0,0),new Vec2(275,64),4);

	obj_list.add(title);
	obj_list.add(cursol);
	menu_list.add(m1);
	menu_list.add(m2);
	menu_list.add(m3);
	for(Utility u:menu_list){
	    obj_list.add(u);
	}

    }

    protected boolean selectMenu(int s){
	switch(s){
	case 0:
	    ag.pushScean(new GameScean(ag,app,data_path));
	    break;
	case 1:
	    TextFileIO.INSTANCE.writeText(data_path + "save.dat", "0");
	    ag.pushScean(new GameScean(ag,app,data_path));
	    break;
	default:
	    return false;
	}
	return true;
    }

    public boolean update(){
	if(InputManager.INSTANCE.isKeyDown(KeyEvent.VK_ENTER)){
	    return selectMenu(select_num);
	}
	if(InputManager.INSTANCE.getKeyDownTime(KeyEvent.VK_DOWN)%2 == 1){
	    select_num++;
	}
	if(InputManager.INSTANCE.getKeyDownTime(KeyEvent.VK_UP)%2 == 1){
	    select_num--;
	}
	select_num%= MENU_NUM;
	
	PosInfo menu_pi = menu_list.get(Math.abs(select_num)).getPosInfo();
	PosInfo pi = cursol.getPosInfo();
	pi.setPos(menu_pi.getPos());

	for(Utility u: obj_list){
	    u.update();
	}

	return true;
    }

    public void draw(){
	for(Utility u: obj_list){
	    u.draw();
	}
    }
}
