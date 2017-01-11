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
	    collisionTestMap(g.getPosInfo());
	}
    }

    protected void collisionTestMap(PosInfo pos_info) {
	Vec2 pos = pos_info.getPos();
	Vec2 vel = pos_info.getVel();
	Vec2 size = pos_info.getSize();
	
	Vec2 tile_pos = map_manager.getTileCollision(pos_info,new Vec2(pos.x,pos.y-vel.y));
	if (tile_pos!=null) {
	    if (vel.x > 0) {
		pos.x = tile_pos.x-size.x;
		pos_info.colDir(PosInfo.RIGHT);
	    } else if (vel.x < 0) {
		pos.x = tile_pos.x+map_manager.getTileWidth();
		pos_info.colDir(PosInfo.LEFT);
	    }
	    pos_info.setVel(0.0, vel.y);
	}
	
	tile_pos =  map_manager.getTileCollision(pos_info,new Vec2(pos.x-vel.x,pos.y));
	if (tile_pos != null) {
	    if (vel.y > 0) {
		pos.y = tile_pos.y-size.y;
		pos_info.colDir(PosInfo.DOWN);
	    } else if (vel.y < 0) {
		pos.y = tile_pos.y+map_manager.getTileHeight()+1;
		pos_info.colDir(PosInfo.UP);
	    }
	    pos_info.setVel(vel.x, 0.0);
	}
     
    }
}
