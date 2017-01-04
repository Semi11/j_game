import java.util.List;
import java.util.ArrayList;

class CollisionManager {
    public void update(List<GameObject> g_obj_list, MapManager map_manager) {
	for (GameObject g : g_obj_list) {
	    collisionTestMap(g.getPosInfo(), map_manager);
	}
    }

    protected void collisionTestMap(PosInfo pos_info, MapManager map_manager) {
	Vec2 pos = pos_info.getPos();
	Vec2 vel = pos_info.getVel();
	Vec2 size = pos_info.getSize();
	Vec2 tile_pos = map_manager.getTileCollision(pos_info,new Vec2(pos.x,pos.y-vel.y));

	if (tile_pos!=null) {
	    if (vel.x > 0) {
		pos.x = tile_pos.x+size.x;
		pos_info.setColDir(DIRECTION.RIGHT);
	    } else if (vel.x < 0) {
		pos.x = tile_pos.x-size.x;
		pos_info.setColDir(DIRECTION.LEFT);
	    }
	}else{
	    pos_info.setColDir(DIRECTION.NONE);
	}
	
	tile_pos =  map_manager.getTileCollision(pos_info,new Vec2(pos.x-vel.x,pos.y));
	if (tile_pos != null) {
	    if (vel.y > 0) {
		pos.y = tile_pos.y-size.y;
		pos_info.setColDir(DIRECTION.DOWN);
	    } else if (vel.y < 0) {
		pos.y = tile_pos.y+size.y;
		pos_info.setColDir(DIRECTION.UP);
	    }
	    pos_info.setVel(0, 0);
	}
        
    }
}
