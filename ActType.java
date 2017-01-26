import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public enum ActType {
    ACT0(ActStrategy.A0),
    ACT1(ActStrategy.A1),
    ACT2(ActStrategy.A2),
    ACT3(ActStrategy.A3),
    ACT4(ActStrategy.A4),
    ACT5(ActStrategy.A5),
    ACT6(ActStrategy.A6),
    ACT7(ActStrategy.A7),
    ACT8(ActStrategy.A8),
    ACT9(ActStrategy.A9);


    private ActStrategy type;

    private ActType(ActStrategy type) {
	this.type = type;
    }

    public void act(GameObject g_obj) {
	this.type.act(g_obj);
    }

    private enum ActStrategy {
	//player
	A0 {
	    void act(GameObject g_obj) {		
		if(InputManager.INSTANCE.getKeyDownTime(KeyEvent.VK_Z) == 1){
		    PosInfo pos_info = g_obj.getPosInfo();
		    Vec2 size = new Vec2(20,10);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    GameObject b = g_obj.getManager().add(3,pos,size);
		    
		    if(pos_info.isFacing(PosInfo.LEFT)){
			b.getPosInfo().setVel(-5.0,0);
		    }else{
			b.getPosInfo().setVel(5.0,0);
		    }
		}
	    }
	},
	//enemy
	A1 {    
	    void act(GameObject g_obj) {
		if((g_obj.getCount() % 180) == 0){
		    PosInfo pos_info = g_obj.getPosInfo();
		    Vec2 size = new Vec2(10,10);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    GameObject b1 = g_obj.getManager().add(2,pos,size);
		    GameObject b2 = g_obj.getManager().add(2,pos,size);
		    b1.getPosInfo().setVel(2.5,0);
		    b2.getPosInfo().setVel(-2.5,0);
		}
	    }
	},
	//bullet
	A2 {    
	    void act(GameObject g_obj) {
	    }
	},
	//player bullet
	A3 {
	    void act(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();	
		if(pos_info.isColWall()){
		    Vec2 size = pos_info.getSize();
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    GameObject b = g_obj.getManager().add(2,pos,size);
		    b.getPosInfo().setVel(pos_info.getVel().reverse());
		    g_obj.kill();
		}
	    }
	},
	//e1
	A4 {    
	    void act(GameObject g_obj) {
		if((g_obj.getCount() % 180) == 0){
		    PosInfo pos_info = g_obj.getPosInfo();
		    Vec2 size = new Vec2(10,10);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    GameObject b1 = g_obj.getManager().add(2,pos,size);
		    GameObject b2 = g_obj.getManager().add(2,pos,size);
		    GameObject b3 = g_obj.getManager().add(2,pos,size);
		    b1.getPosInfo().setVel(-3.0,0);
		    b2.getPosInfo().setVel(-2.5,0);
		    b3.getPosInfo().setVel(-2.0,0);
		}
	    }
	},
	//e3
	A5 {    
	    void act(GameObject g_obj) {
		if((g_obj.getCount() % 180) == 0){
		    PosInfo pos_info = g_obj.getPosInfo();
		    Vec2 size = new Vec2(10,10);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    List<GameObject> b_list= new ArrayList<GameObject>();
		    for(int i=0;i<8;i++){
			b_list.add(g_obj.getManager().add(2,pos,size));
			b_list.get(i).getPosInfo().setVelRad(3.0,(Math.PI/4.0)*i);
		    }
		}
	    }
	},
	//e4
	A6 {    
	    void act(GameObject g_obj) {
		if((g_obj.getCount() % 120) == 0){
		    PosInfo pos_info = g_obj.getPosInfo();
		    Vec2 size = new Vec2(10,10);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    GameObject b1 = g_obj.getManager().add(2,pos,size);
		    b1.getPosInfo().setVel(-2.5,0);
		}
	    }
	},
	//boss
	A7 {    
	    void act(GameObject g_obj) {
		A5.act(g_obj);		
		if(g_obj.getStatusInfo().getHp()<10)A1.act(g_obj);
	    }
	},
	//e5
	A8 {    
	    void act(GameObject g_obj) {
		if((g_obj.getCount() % 180) == 0){
		    PosInfo pos_info = g_obj.getPosInfo();
		    Vec2 size = new Vec2(40,40);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    List<GameObject> b_list= new ArrayList<GameObject>();
		    for(int i=0;i<5;i++){
			b_list.add(g_obj.getManager().add(10,pos,size));
			b_list.get(i).getPosInfo().setVelRad(15.0,(Math.PI/6.0)*(i+7));
		    }
		}       	
	    }
	},
	//b1
	A9 {    
	    void act(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
	
		if(g_obj.getStatusInfo().getHp()<10)A1.act(g_obj);
	
		if((g_obj.getCount() % 180) == 0){
		    Vec2 size = new Vec2(15,15);
		    Vec2 pos = pos_info.getCenterPos().sub(size.half());
		    List<GameObject> b_list= new ArrayList<GameObject>();
		    for(int i=0;i<5;i++){
			b_list.add(g_obj.getManager().add(10,pos,size));
			b_list.get(i).getPosInfo().setVelRad(10.0,(Math.PI/6.0)*(i+7));
		    }
		}      

		if((g_obj.getCount() % 90) == 0){
		    Vec2 size = new Vec2(64,32);
		    Vec2 pos = new Vec2(900, (Math.random()*1000));
		    GameObject g =g_obj.getManager().add(15,pos,size);
		}
	    }
	};

	
	abstract void act(GameObject g_obj);
    }
}
