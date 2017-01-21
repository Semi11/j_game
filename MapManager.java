import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import processing.core.PApplet;

class MapManager {
    private Drawable drawer;
    private JSONObject map_obj = new JSONObject();
    private JSONArray layer_obj_array;
    private ArrayList<TileLayer> tile_layer_list = new ArrayList<TileLayer>();
    private TileLayer col_layer;
    private ObjectLayer obj_layer;    
    private int height;
    private int width;
    private int tile_height;
    private int tile_width;
    private PApplet app;

    public MapManager(PApplet p, String path, String name) {
	drawer = new DrawPImage(p, path, "Map");
	app = p;
	loadMap(name);
    }

    void loadMap(String file_name) {
	map_obj = app.loadJSONObject(file_name + ".json");

	tile_width = map_obj.getInt("tilewidth");
	tile_height = map_obj.getInt("tileheight");
	width = map_obj.getInt("width");
	height = map_obj.getInt("height");

	loadLayer();
    }


    void loadLayer() {
	layer_obj_array = map_obj.getJSONArray("layers");

	for (int i=0;i<layer_obj_array.size();i++) {
	    JSONObject layer_obj = layer_obj_array.getJSONObject(i);
	    String type = layer_obj.getString("type");

	    if(type.equals("tilelayer")){
		TileLayer l = new TileLayer(layer_obj);
		tile_layer_list.add(l);
		if (l.getName().equals("collision")) {
		    col_layer=l;
		}
	    }else if(type.equals("objectgroup")){
		obj_layer = new ObjectLayer(layer_obj);
	    }
	}
    }

    public JSONArray getGameObjectData(){
	return obj_layer.getObjects();
    }

    public Vec2 posToTile(Vec2 pos) {
	Vec2 p = pos;
	p.set(pos.x/(double)tile_width, pos.y/(double)tile_height);
	return p;
    }

    public Vec2 tileToPos(int idx){
	return new Vec2((double)((idx%width)*tile_width),(double)((idx/width)*tile_height));
    }

    public Vec2 getTileCollision(PosInfo pos_info, Vec2 new_pos) {
	Vec2 vel = pos_info.getVel();
	Vec2 old_pos = pos_info.getPos().sub(vel);
	Vec2 size = pos_info.getSize();
	Vec2 from = new Vec2(Math.min(old_pos.x,new_pos.x), Math.min(old_pos.y,new_pos.y));
	Vec2 to = new Vec2(Math.max(old_pos.x,new_pos.x)+size.x-1, Math.max(old_pos.y,new_pos.y)+size.y-1);
	int[] data = col_layer.getData();
	
	from = posToTile(from);
	to = posToTile(to);

	for(int x = (int)from.x;x<=to.x;x++){
	    for(int y = (int)from.y;y<=to.y;y++){
		int idx = (int)y*width+(int)x;
		if(idx<0 || idx>=data.length || data[idx]==0)continue;
		return tileToPos(idx);		
	    }
	}
	
	return null;
    }

    public int getTileWidth(){
	return tile_width; 
    }

    public int getTileHeight(){
	return tile_height;
    }
    
    public int getStageWidth(){
	return width * tile_width;
    }
    
    public int getStageHeight(){
	return height * tile_height;
    }    

    void draw() {

	for ( int i=0; i<tile_layer_list.size(); i++) {
	    if (!tile_layer_list.get(i).isVisible())continue;

	    int[] data = tile_layer_list.get(i).getData();

	    for (int y=0; y< height; y++) {
		for (int x=0; x<width; x++) {
		    int idx = data[y*width+x]-1;
		    if (idx != -1) {
			drawer.draw(idx, tile_width * x, tile_height * y);
		    }
		}
	    }
	}
    }

    private class Layer {
	private String name;
	boolean visible;

	Layer(JSONObject layer_object) {
	    setName(layer_object.getString("name"));
	    setVisible(layer_object.getBoolean("visible"));
	}

	void setName(String name) {
	    this.name = name;
	}

	void setVisible(boolean visible) {
	    this.visible =  visible;
	}

	String getName() {
	    return name;
	}

	boolean isVisible() {
	    return visible;
	}
    }

    private class TileLayer extends Layer {
	private int[] data;

	TileLayer(JSONObject layer_object) {
	    super(layer_object);
	    loadData(layer_object.getJSONArray("data"));      
	}

	void loadData(JSONArray data) {
	    this.data = data.getIntArray();
	}

	int[] getData() {
	    return data;
	}
    }


    private class ObjectLayer extends Layer {
	private JSONArray objects;

	ObjectLayer(JSONObject layer_object) {
	    super(layer_object);
	    loadObjects(layer_object.getJSONArray("objects"));      
	}

	void loadObjects(JSONArray o) {
	    this.objects = o;
	}

	JSONArray getObjects() {
	    return objects;
	}
    }

}
