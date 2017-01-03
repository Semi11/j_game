import processing.core.PApplet;
import processing.core.PImage;

interface Drawable {
  public void draw(int img_id, PosInfo pos_info);
  public void draw(int img_id, float x, float y);
  public void draw(int img_id, float x, float y, float sx, float sy);
  }

  class DrawPImage implements Drawable {
    private PApplet pApplet;
    private PImageManager img_manager;

    public DrawPImage(PApplet p, String path, String dir) {
      pApplet = p;
      this.img_manager = new PImageManager(p, path, dir);
    }

    public void draw(int img_id, PosInfo pos_info) {
      Vec2 pos = pos_info.getPos();
      Vec2 size = pos_info.getSize();
      this.draw(img_id, (float)pos.x, (float)pos.y, (float)size.x, (float)size.y);
    }

    public void draw(int img_id, float x, float y) {
      PImage img = img_manager.getImage(img_id);
      pApplet.image(img, x, y);
    }

    public void draw(int img_id, float x, float y, float sx, float sy) {
      PImage img = img_manager.getImage(img_id);
      pApplet.image(img, x, y, sx, sy);
    }
  }