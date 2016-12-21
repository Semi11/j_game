import processing.core.*;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

class PImageManager {
  private PApplet pApplet;
  private List<PImage> img_list = new ArrayList<PImage>();
  private String path = "/home/student/j15/j15426/J2program/processing/j_game/data/GameObjectImage/";

  public PImageManager(PApplet p) {
    pApplet = p;
    this.loadAll();
  }

  public void loadAll() {
    File dir = new File(path);
    File[] files = dir.listFiles();
    for (int i=0; i<files.length; i++) {
      img_list.add(pApplet.loadImage(path+i+".png"));
    }
  }

  public PImage getImage(int img_id) {
    return img_list.get(img_id);
  }
}