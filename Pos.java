class PosInfo {
  private Vec2 pos; 
  private Vec2 vel;
  private Vec2 acc;

  public PosInfo() {
    pos = new Vec2();
    vel = new Vec2();
    acc = new Vec2();
  }

  public void setPos(double x, double y) {
    this.pos.set(x, y);
  }
  
  public Vec2 getPos() {
    return this.pos;
  }

  public void setVel(double vel, double ang) {
    double rad = Math.toRadians(ang); 
    this.vel.set(vel * Math.cos(rad), vel * Math.sin(rad));
  }

  public void update() {
    pos = pos.add(vel);
  }
}