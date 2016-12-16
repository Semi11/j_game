import processing.core.*;

interface Drawable {
  public void draw(PosInfo pos_info, String img_name);
}

class DrawPImage implements Drawable {
  private PApplet pApplet;
  private Drawable drawer = new DrawPImage();
  private PImageLoader img_loader = new PImageLoader(pApplet);

  private public void DrawPImage() {
  }

  public static Drawable getInstance() {
    return drawer;
  }

  public void draw(PosInfo pos_info, String img_name) {
    Vec2 pos = pos_info.getPos();
    PImage img = img_loader.getImage(img_name);
    pApplet.image(img, (float)pos.x, (float)pos.y);
  }
}