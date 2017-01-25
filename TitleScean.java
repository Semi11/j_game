import processing.core.PApplet;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class TitleScean implements Scean{
    private final int MENU_NUM = 2;
    private PApplet app;
    private ActionGame ag;
    private String data_path;
    private Drawable drawer;
    private List<Utility> obj_list = new ArrayList<Utility>();
    private List<Utility> menu_list = new ArrayList<Utility>();
    private Utility cursol;
    private char select_num = 0;

    public TitleScean(ActionGame ag,PApplet app, String path){
	this.ag=ag;
	this.app=app;
	this.data_path=path;
	this.drawer = new DrawPImage(app, path, "Title", new PosInfo(0,0));
	
	Utility title = new Utility(drawer,new Vec2(100,10),new Vec2(300,300),0);
	Utility m1 = new Utility(drawer,new Vec2(100,50),new Vec2(50,50),1);
	Utility m2 = new Utility(drawer,new Vec2(100,100),new Vec2(50,50),2);
	cursol = new Utility(drawer,new Vec2(80,50),new Vec2(50,50),3);

	obj_list.add(title);
	obj_list.add(cursol);
	menu_list.add(m1);
	menu_list.add(m2);
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
	    ag.pushScean(new GameScean(ag,app,data_path));
	    break;
	default:
	    return false;
	}
	return true;
    }

    public boolean update(){
	if(InputManager.INSTANCE.getKeyDownTime(KeyEvent.VK_ENTER) == 1){
	    return selectMenu(select_num);
	}
	if(InputManager.INSTANCE.getKeyDownTime(KeyEvent.VK_DOWN) == 1){
	    select_num++;
	}
	if(InputManager.INSTANCE.getKeyDownTime(KeyEvent.VK_UP) == 1){
	    select_num--;
	}
	select_num%= MENU_NUM;
	
	Vec2 pos = menu_list.get(select_num).getPosInfo().getPos();
	cursol.getPosInfo().setPos(pos.x-10,pos.y);
	
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
