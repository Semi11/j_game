interface MoveableStrategy {
  public void update();
}

class MoveStrategy0 implements MoveableStrategy {
  PosInfo pos_info;

  public MoveStrategy0() {
    pos_info.setVel(2.0,0.0);
  }
  
  public void update(){
   pos_info.update(); 
  }
}