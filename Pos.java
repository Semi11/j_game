class PosInfo {
  private Vec2 pos; 
  private Vec2 vel;
  private Vec2 acc;

  public PosInfo() {
    pos = new Vec2();
    vel = new Vec2();
    acc = new Vec2();
  }

  public void setPos(float x, float y) {
    this.pos.set(x, y);
  }

  public void setVel(float vel, float ang) {
    double rad = Math.toRadians(ang); 
    this.vel.set(vel * (float)Math.cos(rad), vel * (float)Math.sin(rad));
  }

  public void update() {
    pos.add(vel);
  }
}