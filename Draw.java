import processing.core.*;

interface Drawable {
  public void draw(PosInfo pos_info, int img_id);
}

class DrawPImage implements Drawable {
  private PApplet pApplet;
  private PImageManager img_manager;

  public DrawPImage(PApplet p,PImageManager img_manager) {
    pApplet = p;
    this.img_manager = img_manager;
  }

  public void draw(PosInfo pos_info, int img_id) {
    Vec2 pos = pos_info.getPos();
    PImage img = img_manager.getImage(img_id);
    pApplet.image(img, (float)pos.x, (float)pos.y);
  }
}