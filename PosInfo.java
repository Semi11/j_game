class PosInfo {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int RIGHT = 2;
    public static final int LEFT = 3;
    public static final int DIRECTION = 4;
    private Vec2 pos; 
    private Vec2 vel;
    private Vec2 acc;
    private Vec2 size;
    private double dir;
    private boolean[] col_dir = new boolean[DIRECTION];
    private double speed;

    public PosInfo() {
	this(0, 0);
    }

    public PosInfo(double x, double y) {
	this(x, y, 180);
    }


    public PosInfo(double x, double y, double ang) {
	this(x, y, ang, 0.0);
    }

    public PosInfo(double x, double y, double ang, double speed) {
	pos = new Vec2(x, y);
	vel = new Vec2();
	acc = new Vec2();
	size = new Vec2();
	this.dir = ang;
	this.speed = speed;
    }

    public void setPos(double x, double y) {
	this.pos.set(x, y);
    }

    public Vec2 getPos() {
	return this.pos;
    }

    public void setVel(double x, double y) {
	this.vel.set(x, y);
    }

    public Vec2 getVel() {
	return this.vel;
    }

    public void setAcc(double x, double y) {
	this.acc.set(x,y);
    }

    public Vec2 getAcc() {
	return this.acc;
    }

    public void setSize(double x, double y) {
	this.size.set(x, y);
    }

    public Vec2 getSize() {
	return this.size;
    }

    public double getDir() {
	return this.dir;
    }

    public double getSpe() {
	return this.speed;
    }

    public boolean colDir(int dir){
	if(0>dir || dir>=DIRECTION)return false;
	
	col_dir[dir]=true;
	return true;
    }

    public boolean isColDir(int dir){
	if(0>dir || dir>=DIRECTION)return false;
	return col_dir[dir];
    }
    
    public void update() {
	pos = pos.add(vel);
	vel = vel.add(acc);
	col_dir = new boolean[DIRECTION];
    }
}
