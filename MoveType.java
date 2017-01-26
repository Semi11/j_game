import processing.core.PConstants;

public enum MoveType {
    MOVE0(MoveStrategy.M0), 
    MOVE1(MoveStrategy.M1),
    MOVE2(MoveStrategy.M2),
    MOVE3(MoveStrategy.M3),
    MOVE4(MoveStrategy.M4),
    MOVE5(MoveStrategy.M5),
    MOVE6(MoveStrategy.M6),
    MOVE7(MoveStrategy.M7),
    MOVE8(MoveStrategy.M8);

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
		    pos_info.clearColDir();
		}
	    }
	},
	//enemy
	M1 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		pos_info.setVel(0,pos_info.getVel().y);
	    }
	},
	//bullet
	M2 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		pos_info.setAcc(0,0);		
	    }
      	},
	//e2,e3
	M3 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		
		pos_info.setVel(-3.5,pos_info.getVel().y);		
		
		if(pos_info.isFacing(PosInfo.RIGHT)){
		    pos_info.setVel(3.5,pos_info.getVel().y);
		}				
		
		if(pos_info.isColDir(PosInfo.LEFT)){ 
		    pos_info.setVel(3.5,pos_info.getVel().y);
		    pos_info.clearColDir();
		}
		
		if(pos_info.isColDir(PosInfo.RIGHT)){ 
		    pos_info.setVel(-3.5,pos_info.getVel().y);
		    pos_info.clearColDir();
		}  
		
	    }
	},
	//e4
	M4 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		
		pos_info.setVel(pos_info.getVel().x, 3.5);

		if(pos_info.isFacing(PosInfo.UP)){
		    pos_info.setVel(pos_info.getVel().x, -3.5);
		}				
		
		if(pos_info.isColDir(PosInfo.UP)){ 
		    pos_info.setVel(pos_info.getVel().x, 3.5);
		    pos_info.clearColDir();
		}
		
		if(pos_info.isColDir(PosInfo.DOWN)){ 
		    pos_info.setVel(pos_info.getVel().x, -3.5);
		    pos_info.clearColDir();
		}	       
	    }
	},
	//boss
	M5 {      
	    void move(GameObject g_obj) {		
		PosInfo pos_info = g_obj.getPosInfo();
		
		M3.move(g_obj);
		
		if(g_obj.getCount() % 240 == 239){
		    pos_info.setVel(pos_info.getVel().x, -15.0); 
		    pos_info.clearColDir();		    
		}

	    }
	},
	//heaby bullet
	M6 {      
	    void move(GameObject g_obj) {
	    }
	},
	//
	M7 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		pos_info.setVel(-5.0,0);		
		pos_info.setPos(pos_info.getPos().x,Math.sin(((double)g_obj.getCount())/10.0)*7 + pos_info.getPos().y);
	    }
	},
	//b1
	M8 {      
	    void move(GameObject g_obj) {
		PosInfo pos_info = g_obj.getPosInfo();
		
		M3.move(g_obj);
		pos_info.setVel(pos_info.getVel().x, Math.sin((double)g_obj.getCount()/10.0)*10);
	       
	    }
	};
	
	abstract void move(GameObject g_obj);
    }
}
