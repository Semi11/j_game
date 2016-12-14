class GameObject {
  private PosInfo pos_info;
  PImage image;
  private MoveableStrategy move;
  
  public GameObject(){
    
  }
  
  public void init(float px,float py,String img_name){
    pos_info.setPos(px,py);
    image = loadImage(img_name + ".jpg");
  }
  
  public boolean update(){
     move.update();
     
     return true; 
  }
}