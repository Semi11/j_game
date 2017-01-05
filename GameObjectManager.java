import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import processing.core.PApplet;

class GameObjectFactory {
    private Drawable drawer;
    private List<GameObject> prototype_list = new ArrayList<GameObject>();
    private List<HashMap<String, String>> g_obj_data_list;

    public GameObjectFactory(Drawable d, String path) {
	CSVReader csv_reader = new CSVReader();
	drawer = d;
	g_obj_data_list = csv_reader.read(path + "GameObjectData.csv");
    }

    public GameObject getGameObject() {
	return new GameObject(drawer);
    }

    public GameObject getGameObject(int id) {
	HashMap<String, String> data = g_obj_data_list.get(id);
	GameObject g = this.getGameObject();

	g.setGameObjectID(Integer.parseInt(data.get("GameObjectID")));
	g.setImageID(Integer.parseInt(data.get("ImageID")));
	g.setHp(Integer.parseInt(data.get("HP")));
	g.setPower(Integer.parseInt(data.get("Power")));
	g.setMoveType(data.get("MoveType"));
	g.setTag(data.get("Tag"));
	g.setSize(Double.parseDouble(data.get("SizeX")), Double.parseDouble(data.get("SizeY")));
	g.setGravity(Boolean.valueOf(data.get("Gravity")));

	return g;
    }
}

class GameObjectManager {
    private Drawable drawer;
    private GameObjectFactory g_obj_fac;
    private List<GameObject> g_obj_list = new ArrayList<GameObject>();
    private MapManager map_manager;
    private CollisionManager col_manager = new CollisionManager();

    public GameObjectManager(PApplet p, String path) {
	drawer = new DrawPImage(p, path, "GameObject");
	g_obj_fac = new GameObjectFactory(drawer, path);
	map_manager = new MapManager(p, path, "map");
    }

    public void addGameObject(int g_obj_id, double px, double py) {
	GameObject g = g_obj_fac.getGameObject(g_obj_id);
	g.init(px, py);
	g_obj_list.add(g);
    }

    public void update() {
	for (GameObject g : g_obj_list) {
	    g.update();
	    if (g.isGravity() && !g.getPosInfo().isColDir(PosInfo.DOWN)) {
		g.getPosInfo().setAcc(0.0, 0.5);
	    }else{
		g.getPosInfo().setAcc(0, 0);
	    }
	}
	col_manager.update(g_obj_list, map_manager);
    }

    public void draw() {
	map_manager.draw();
	for (GameObject g : g_obj_list) {
	    g.draw();
	}
    }
}
