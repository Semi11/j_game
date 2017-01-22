import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;
import processing.data.JSONObject;
import processing.data.JSONArray;

public class GameObjectManager {
    private GameObjectFactory g_obj_fac;
    private List<GameObject> g_obj_list = new ArrayList<GameObject>();
    private List<GameObject> add_list = new ArrayList<GameObject>();
    private GameObject player;
    private GameObject boss;

    public GameObjectManager(PApplet p, String path, PosInfo screan) {
	g_obj_fac = new GameObjectFactory(new DrawPImage(p, path, "GameObject", screan), path);	
    }

    public void add(JSONArray g_objects_data){
	for(int i=0;i<g_objects_data.size();i++){
	    JSONObject g_obj_data = g_objects_data.getJSONObject(i);
	    Vec2 pos = new Vec2(g_obj_data.getFloat("x"),g_obj_data.getFloat("y"));
	    Vec2 size = new Vec2(g_obj_data.getFloat("width"),g_obj_data.getFloat("height"));
	    int id = Integer.parseInt(g_obj_data.getString("type"));
	    if(g_obj_data.getString("name").equals("player")){
		player = add(id,pos,size);
	    }else if(g_obj_data.getString("name").equals("boss")){
		boss = add(id,pos,size);
	    }else{
		add(id,pos,size);
	    }
	}
    }
    
    public GameObject add(int g_obj_id, Vec2 pos, Vec2 size) {
	GameObject g = g_obj_fac.getGameObject(g_obj_id);	
	g.init(pos, size);
	g.setManager(this);
	add_list.add(g);
	return g;
    }
    
    public List getObjects(){
	return this.g_obj_list;
    }
    
    public GameObject getPlayer(){
	return this.player;
    }

    public GameObject getBoss(){
	return this.boss;
    }
    
    public void update() {
	Iterator<GameObject> ite = g_obj_list.iterator();
	while(ite.hasNext()){
	    GameObject g = ite.next();
	    if(g.update()){
		if (!g.getPosInfo().isColDir(PosInfo.DOWN)) {
		    g.getPosInfo().setAcc(0.0, 0.5);
		}
	    }else{
		ite.remove();
	    }
	}
	
	for (GameObject g : add_list) {
	    g_obj_list.add(g);
	}

	add_list.clear();
    }
    
    public void draw() {
	for (GameObject g : g_obj_list) {
	    g.draw();
	}
    }
}
