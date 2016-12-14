class Vec2 {
  public float x, y;

  public Vec2(float x, float y) {
    set(x,y);
  }

  public Vec2(){
    this(0,0); 
  }

  public void set(float x, float y) {
    this.x=x;
    this.y=y;
  }
  
  public Vec2 add(Vec2 v){
    return new Vec2(x + v.x, y + v.y);
  }
  
  public Vec2 sub(Vec2 v){
    return new Vec2(x - v.x, y - v.y);
  }
  
  public Vec2 mult(Vec2 v){
    return new Vec2(x * v.x, y * v.y);
  }
  
  public Vec2 div(Vec2 v){
    return new Vec2(x / v.x, y / v.y);
  }
  
}