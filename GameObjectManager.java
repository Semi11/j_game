import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;

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
	g.setCollisionType(data.get("CollisionType"));
	g.setTag(data.get("Tag"));

	return g;
    }
}

class GameObjectManager {
    private Drawable drawer;
    private GameObjectFactory g_obj_fac;
    private MapManager map_manager;
    private CollisionManager col_manager;
    private List<GameObject> g_obj_list = new ArrayList<GameObject>();
    private GameObject player;

    public GameObjectManager(PApplet p, String path) {
	drawer = new DrawPImage(p, path, "GameObject");
	g_obj_fac = new GameObjectFactory(drawer, path);
	map_manager = new MapManager(p, path, "map");
	col_manager = new CollisionManager(g_obj_list, map_manager);
	add(map_manager.getGameObjectData());
    }

    public void add(JSONArray g_objects_data){
	for(int i=0;i<g_objects_data.size();i++){
	    JSONObject g_obj_data = g_objects_data.getJSONObject(i);
	    Vec2 pos = new Vec2(g_obj_data.getFloat("x"),g_obj_data.getFloat("y"));
	    Vec2 size = new Vec2(g_obj_data.getFloat("width"),g_obj_data.getFloat("height"));
	    int id = Integer.parseInt(g_obj_data.getString("type"));
	    add(id,pos,size);
	}
    }
    
    public void add(int g_obj_id, Vec2 pos, Vec2 size) {
	GameObject g = g_obj_fac.getGameObject(g_obj_id);	
	g.init(pos, size);
	g.setManager(this);
	g_obj_list.add(g);
	if(g_obj_id == 0){
	    player = g;
	}
    }

    public GameObject getPlayer(){
	return this.player;
    }
    
    public void update() {
	for (GameObject g : g_obj_list) {
	    g.update();
	    if (!g.getPosInfo().isColDir(PosInfo.DOWN)) {
		g.getPosInfo().setAcc(0.0, 0.5);
	    }
	}
	col_manager.update();
    }
    
    public void draw() {
	map_manager.draw();
	for (GameObject g : g_obj_list) {
	    g.draw();
	}
    }
}
