import processing.core.PApplet;
import processing.core.PImage;
import java.util.List;
import java.util.ArrayList;
import java.io.File;

class PImageManager {
  private PApplet pApplet;
  private List<PImage> img_list = new ArrayList<PImage>();
  private String path;

  public PImageManager(PApplet p, String path, String dir) {
    pApplet = p;
    this.path = path + dir + "Image";
    this.loadAll();
  }

  public void loadAll() {
    File dir = new File(path);
    File[] files = dir.listFiles();
    for (int i=0; i<files.length; i++) {
      img_list.add(pApplet.loadImage(path+"/"+i+".png"));
    }
  }

  public PImage getImage(int img_id) {
    return img_list.get(img_id);
  }
}