class PImageLoader {
  private PApplet pApplet;
  
  public PImageLoader(PApplet p){
    pApplet = p;
  }
  
  public PImage getImage(String img_name){
    PImage img;
    img = p.loadImage(img_name);
  }
}