import java.util.List;
import java.util.ArrayList;

class CollisionManager {
    List<GameObject> g_obj_list;
    MapManager map_manager;

    public CollisionManager(List<GameObject> g_obj_list, MapManager map_manager) {
	this.g_obj_list = g_obj_list;
	this.map_manager = map_manager;
    }
    
    public void update() {
	for (GameObject g : g_obj_list) {
	    PosInfo pi = g.getPosInfo();
	    if(isInStage(pi)){
		collisionTestMap(pi, g.isColisionStage());
	    }else{g.kill();}
	}
	for (GameObject g : g_obj_list) {
	    for (GameObject other : g_obj_list) {
		if(!g.equals(other)){
		    collisionTestObject(g,other);
		}
	    }
	}
    }

    protected boolean isInStage(PosInfo pos_info){
	Vec2 pos = pos_info.getPos();
	Vec2 size = pos_info.getSize();
	
	if((pos.x+size.x)<0 || pos.x>map_manager.getStageWidth() || (pos.y+size.y)<0 || pos.y>map_manager.getStageHeight()){
	    return false;
	}
	return true;
    }

    protected void collisionTestMap(PosInfo pos_info, boolean col_stage) {
	Vec2 pos = pos_info.getPos();
	Vec2 vel = pos_info.getVel();
	Vec2 size = pos_info.getSize();
	
	//x
	Vec2 tile_pos = map_manager.getTileCollision(pos_info,new Vec2(pos.x,pos.y-vel.y));
	if (tile_pos!=null) {
	    if (vel.x > 0) {
		if(col_stage)pos.x = tile_pos.x-size.x;
		pos_info.colDir(PosInfo.RIGHT);
	    } else if (vel.x < 0) {
		if(col_stage)pos.x = tile_pos.x+map_manager.getTileWidth();
		pos_info.colDir(PosInfo.LEFT);
	    }
	    if(col_stage)pos_info.setVel(0.0, vel.y);
	}
	
	//y
	tile_pos =  map_manager.getTileCollision(pos_info,new Vec2(pos.x-vel.x,pos.y));
	if (tile_pos != null) {
	    if (vel.y > 0) {
		if(col_stage)pos.y = tile_pos.y-size.y;
		pos_info.colDir(PosInfo.DOWN);
	    } else if (vel.y < 0) {
		if(col_stage)pos.y = tile_pos.y+map_manager.getTileHeight()+1;
		pos_info.colDir(PosInfo.UP);
	    }
	    if(col_stage)pos_info.setVel(vel.x, 0.0);
	}
     
    }
    
    protected void collisionTestObject(GameObject g_obj, GameObject other) {
	if(g_obj.getTag() == other.getTag())return;
	
	PosInfo pos_info = g_obj.getPosInfo();
	PosInfo other_pos_info = other.getPosInfo();
	Vec2 size = new Vec2(pos_info.getSize().x/2.0, pos_info.getSize().y/2.0);
	Vec2 other_size = new Vec2(other_pos_info.getSize().x/2.0, other_pos_info.getSize().y/2.0);
	Vec2 center_pos = pos_info.getCenterPos();
	Vec2 other_center_pos = other_pos_info.getCenterPos();

	if(Math.abs(center_pos.x - other_center_pos.x) < (size.x + other_size.x)
	   &&
	   Math.abs(center_pos.y - other_center_pos.y) < (size.y + other_size.y)){
	    g_obj.collsion(other);
	}

    }
}
