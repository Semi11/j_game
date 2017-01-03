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
	M1 {      
	    void move(PosInfo pos_info, int cnt) {
		
	    }
	},
	M0 {      
	    void move(PosInfo pos_info, int cnt) {
		double x = pos_info.getVel().x;
		if(x==0.0)x=-2.0;
		if(pos_info.getColDir().equals(DIRECTION.LEFT)||pos_info.getColDir().equals(DIRECTION.RIGHT)){x*=-1.0;}
		pos_info.setVel(x, pos_info.getVel().y);
		pos_info.update();
	    }
	}, 
	M2 {
	    void move(PosInfo pos_info, int cnt) {
		pos_info.setVel(2.0, 90);
		pos_info.update();
	    }
	};

	abstract void move(PosInfo pos_info, int cnt);
    }
}
