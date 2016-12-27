import processing.core.PApplet;
import processing.core.PImage;

interface Drawable {
  public void draw(PosInfo pos_info, int img_id);
  public void draw(Vec2 pos, int img_id);
  public void draw(int x, int y, int img_id);
}

class DrawPImage implements Drawable {
  private PApplet pApplet;
  private PImageManager img_manager;

  public DrawPImage(PApplet p, String path, String dir) {
    pApplet = p;
    this.img_manager = new PImageManager(p, path, dir);;
  }

  public void draw(PosInfo pos_info, int img_id) {
    Vec2 pos = pos_info.getPos();
    PImage img = img_manager.getImage(img_id);
    pApplet.image(img, (float)pos.x, (float)pos.y);
  }
  
  public void draw(Vec2 pos, int img_id) {
    PImage img = img_manager.getImage(img_id);
    pApplet.image(img, (float)pos.x, (float)pos.y);
  }
  
  public void draw(int x, int y, int img_id) {
    PImage img = img_manager.getImage(img_id);
    pApplet.image(img,x, y);
  }
}