enum ActType {
    ACT0(ActStrategy.A0),
    ACT1(ActStrategy.A1);

    private ActStrategy type;

    private ActType(ActStrategy type) {
	this.type = type;
    }

    public void act(GameObject g_obj) {
	this.type.act(g_obj);
    }

    private enum ActStrategy {
	A0 {      
	    void act(GameObject g_obj) {			
	    }
	},
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
	};
	
	abstract void act(GameObject g_obj);
    }
}
