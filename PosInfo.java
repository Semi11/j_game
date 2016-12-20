class PosInfo {
  private Vec2 pos; 
  private Vec2 vel;
  private Vec2 acc;
  private double dir;
  private double speed;

  public PosInfo() {
    this(0,0);
  }
  
  public PosInfo(double x, double y){
    this(x,y,180);    
  }
  
  
  public PosInfo(double x, double y,double ang){
    this(x,y,ang,2.0);
  }
  
  public PosInfo(double x, double y,double ang, double speed){
    pos = new Vec2(x,y);
    vel = new Vec2();
    acc = new Vec2();
    this.dir = ang;
    this.speed = speed;
  }


  public void setPos(double x, double y) {
    this.pos.set(x, y);
  }

  public Vec2 getPos() {
    return this.pos;
  }

  public void setVel(double vel, double ang) {
    this.dir = ang;
    double rad = Math.toRadians(ang); 
    this.vel.set(vel * Math.cos(rad), vel * Math.sin(rad));
  }

  public double getDir() {
    return this.dir;
  }

  public double getSpe(){
    return this.speed; 
  }

  public void update() {
    pos = pos.add(vel);
  }
}