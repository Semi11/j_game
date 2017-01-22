class Vec2 {
    public double x, y;

    public Vec2(double x, double y) {
	set(x,y);
    }

    public Vec2(){
	this(0,0); 
    }

    public void set(double x, double y) {
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

    public Vec2 half(){
	return new Vec2(x / 2.0, y / 2.0);
    }

    public double getRad(){
	if(x > 0) return Math.atan(y/x);
	else return Math.atan(y/x) + Math.PI;
    }

    public Vec2 reverse(){
	return new Vec2 (-x,-y);
    }
    
}
