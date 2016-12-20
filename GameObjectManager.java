import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import processing.core.*;

class GameObjectFactory extends PApplet {
  private Drawable drawer;
  private List<GameObject> prototype_list = new ArrayList<GameObject>();

  public GameObjectFactory(Drawable d) {
    drawer = d;
  }

  public void generatePrototype() {
    CSVReader csv_reader = new CSVReader();
    List<HashMap<String, String>> g_obj_data_list;

    g_obj_data_list = csv_reader.read(dataPath("GameObjectData.csv"));

    for (HashMap<String, String> data : g_obj_data_list) {
      GameObject g = this.getGameObject();

      g.setGameObjectID(Integer.parseInt(data.get("GameObjectID")));
      g.setImageID(Integer.parseInt(data.get("ImageID")));
      g.setHp(Integer.parseInt(data.get("HP")));
      g.setPower(Integer.parseInt(data.get("Power")));
      g.setMoveType(data.get("MoveType"));
      g.setTag(data.get("Tag"));

      prototype_list.add(g);
    }
  }

  public GameObject getGameObject() {
    return new GameObject(drawer);
  }

  public GameObject getGameObject(int id) {
    return prototype_list.get(id).createClone();
  }
}

class GameObjectManager {
  private Drawable drawer;
  private GameObjectFactory g_obj_fac = new GameObjectFactory(drawer);
  private List<GameObject> g_obj_list = new ArrayList<GameObject>();

  public GameObjectManager(Drawable d) {
    drawer = d;
    g_obj_fac.generatePrototype();
  }

  public void addGameObject(int g_obj_id, double px, double py) {
    GameObject g = g_obj_fac.getGameObject(g_obj_id);
    g.init(px, py);
    g_obj_list.add(g);
  }

  public void update() {
    for (GameObject g : g_obj_list) {
      g.update();
    }
  }

  public void draw() {
    for (GameObject g : g_obj_list) {
      g.draw();
    }
  }
}