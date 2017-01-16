import processing.core.PConstants;
import processing.event.Event;

enum MoveType {
    MOVE0(MoveStrategy.M0), 
    MOVE1(MoveStrategy.M1);

    private MoveStrategy type;

    private MoveType(MoveStrategy type) {
	this.type = type;
    }

    public void update(PosInfo pos_info, int cnt) {
	this.type.move(pos_info, cnt);
    }

    private enum MoveStrategy {
	M0 {      
	    void move(PosInfo pos_info, int cnt) {
		if(InputManager.INSTANCE.isKeyDown(PConstants.RIGHT) && !pos_info.isColDir(PosInfo.RIGHT)){
		    pos_info.setVel(3.0, pos_info.getVel().y);
		}else if(InputManager.INSTANCE.isKeyDown(PConstants.LEFT) && !pos_info.isColDir(PosInfo.LEFT)){
		    pos_info.setVel(-3.0, pos_info.getVel().y);
		}else{
		    pos_info.setVel(0.0, pos_info.getVel().y);
		}
		
		if(InputManager.INSTANCE.isKeyDown(PConstants.UP) && !pos_info.isColDir(PosInfo.UP) && pos_info.isColDir(PosInfo.DOWN)){
		    pos_info.setVel(pos_info.getVel().x, -10.0);
		}
	    }
	},
	M1 {      
	    void move(PosInfo pos_info, int cnt) {
		pos_info.setVel(0,pos_info.getVel().y);
	    }
	},
	M2 {      
	    void move(PosInfo pos_info, int cnt) {
		double x = pos_info.getVel().x;
		if(x == 0) pos_info.setVel(-2.0, pos_info.getVel().y);
		if(pos_info.isColDir(PosInfo.LEFT) || pos_info.isColDir(PosInfo.RIGHT)) pos_info.setVel(-x, pos_info.getVel().y);
	    }
	};
	
	abstract void move(PosInfo pos_info, int cnt);
    }
}
