import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import processing.core.PApplet;

class MapManager {
    private Drawable drawer;
    private JSONObject map_obj = new JSONObject();
    private JSONArray layer_obj_array;
    private ArrayList <Layer> layer_list = new ArrayList<Layer>();
    private Layer col_layer;
    private int high;
    private int widh;
    private int tile_high;
    private int tile_widh;
    private PApplet pApplet;

    public MapManager(PApplet p, String path, String name) {
	drawer = new DrawPImage(p, path, "Map");
	pApplet = p;
	load_map(name);
    }

    void load_map(String file_name) {
	map_obj = pApplet.loadJSONObject(file_name + ".json");

	tile_widh = map_obj.getInt("tilewidth");
	tile_high = map_obj.getInt("tileheight");
	widh = map_obj.getInt("width");
	high = map_obj.getInt("height");

	init_layer();
    }


    void init_layer() {
	layer_obj_array = map_obj.getJSONArray("layers");

	for (int i=0; i<layer_obj_array.size(); i++) {
	    Layer l = new Layer(layer_obj_array.getJSONObject(i));
	    layer_list.add(l);
	    if (l.getName().equals("collision")) {
		col_layer=l;
	    }
	}
    }

    Vec2 posToTile(Vec2 pos) {
	Vec2 p = pos;
	p.set(pos.x/(double)tile_widh, pos.y/(double)tile_high);
	return p;
    }

    Vec2 tileToPos(int idx){
	return new Vec2((double)((idx%widh)*tile_widh),(double)((idx/widh)*tile_high));
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
		int idx = (int)y*widh+(int)x;
		if(idx<0 || idx>=data.length || data[idx]==0)continue;
		return tileToPos(idx);		
	    }
	}
	
	return null;
    }

    public int getTileWidth(){
	return tile_widh; 
    }

    public int getTileHight(){
	return tile_high;
    }

    void draw() {

	for ( int i=0; i<layer_list.size(); i++) {
	    if (!layer_list.get(i).isVisible())continue;

	    int[] data = layer_list.get(i).getData();

	    for (int y=0; y< high; y++) {
		for (int x=0; x<widh; x++) {
		    int idx = data[y*widh+x]-1;
		    if (idx != -1) {
			drawer.draw(idx, tile_widh * x, tile_high * y);
		    }
		}
	    }
	}
    }

    private class Layer {
	private int[] layer_data;
	private String name;
	boolean visible;

	Layer(JSONObject layer_object) {
	    setData(layer_object.getJSONArray("data"));      
	    setName(layer_object.getString("name"));
	    setVisible(layer_object.getBoolean("visible"));
	}

	void setData(JSONArray data) {
	    layer_data = data.getIntArray();
	}

	void setName(String name) {
	    this.name = name;
	}

	void setVisible(boolean visible) {
	    this.visible =  visible;
	}

	int[] getData() {
	    return layer_data;
	}

	String getName() {
	    return name;
	}

	boolean isVisible() {
	    return visible;
	}
    }
}
