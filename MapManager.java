import processing.data.JSONObject;
import processing.data.JSONArray;
import java.util.ArrayList;
import processing.core.PApplet;

class MapManager {
  private Drawable drawer;
  private JSONObject map_obj = new JSONObject();
  private JSONArray layer_obj_array;
  private ArrayList <Layer> layer_list = new ArrayList<Layer>();
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
      layer_list.add((Layer)new Layer(layer_obj_array.getJSONObject(i)) );
    }
  }

  Vec2 pos_to_tile(Vec2 pos) {
    pos.set(pos.x/(float)tile_widh, pos.y/(float)tile_high);
    return pos;
  }

  void draw() {

    for ( int i=0; i<layer_list.size(); i++) {
      if (!layer_list.get(i).isVisible())continue;

      int[] data = layer_list.get(i).getData();

      for (int y=0; y< high; y++) {
        for (int x=0; x<widh; x++) {
          int idx = data[y*widh+x]-1;
          if (idx != -1) {
            drawer.draw(tile_widh * x, tile_high * y, idx);
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
    
    void setVisible(boolean visible){
     this.visible =  visible;
    }

    int[] getData() {
      return layer_data;
    }

    String getName() {
      return name;
    }
    
    boolean isVisible(){
     return visible; 
    }
  }
}