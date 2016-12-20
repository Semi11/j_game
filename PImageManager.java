import processing.core.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

class PImageManager {
  private PApplet pApplet;
  private List<PImage> img_list = new ArrayList<PImage>();
  private String path = "data";

  public PImageManager(PApplet p) {
    pApplet = p;
  }

  public void loadAll() {
    File dir = new File(path);
    File[] files = dir.listFiles();

    for (int i=0; i<files.length; i++) {
      img_list.add(pApplet.loadImage(i+".png"));
    }
  }

  public PImage getImage(int img_id) {
    return img_list.get(img_id);
  }
}