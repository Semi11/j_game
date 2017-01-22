import processing.core.PConstants;

public enum MoveType {
    MOVE0(MoveStrategy.M0), 
    MOVE1(MoveStrategy.M1),
    MOVE2(MoveStrategy.M2);

    private MoveStrategy type;

    private MoveType(MoveStrategy type) {
	this.type = type;
    }

    public void move(GameObject g_obj) {
	this.type.move(g_obj);
    }

    private enum MoveStrategy {
	//player
	M0 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		if(InputManager.INSTANCE.isKeyDown(PConstants.RIGHT) && !pos_info.isColDir(PosInfo.RIGHT)){
		    pos_info.setVel(3.0, pos_info.getVel().y);
		}else if(InputManager.INSTANCE.isKeyDown(PConstants.LEFT) && !pos_info.isColDir(PosInfo.LEFT)){
		    pos_info.setVel(-3.0, pos_info.getVel().y);
		}else{
		    pos_info.setVel(0.0, pos_info.getVel().y);
		}
		
		if(InputManager.INSTANCE.isKeyDown(PConstants.UP) && !pos_info.isColDir(PosInfo.UP) && pos_info.isColDir(PosInfo.DOWN)){
 		    pos_info.setVel(pos_info.getVel().x, -10.0);
		}else if(pos_info.isColDir(PosInfo.DOWN)){
		    pos_info.setVel(pos_info.getVel().x,0);
		}
	    }
	},
	//enemy
	M1 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		pos_info.setVel(0,0);
	    }
	},
	//bullet
	M2 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		pos_info.setAcc(0,0);		
	    }
	};
	
	abstract void move(GameObject g_obj);
    }
}
