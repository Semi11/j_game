import java.awt.event.KeyEvent;

public enum ActType {
    ACT0(ActStrategy.A0),
    ACT1(ActStrategy.A1),
    ACT2(ActStrategy.A2),
    ACT3(ActStrategy.A3);

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
	//e1
	A3 {    
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
	};
	
	abstract void act(GameObject g_obj);
    }
}
