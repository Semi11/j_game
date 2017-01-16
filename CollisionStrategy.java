import processing.core.PConstants;
import processing.event.Event;

enum CollisionType {
    COLLISION0(CollisionStrategy.C0),
    COLLISION1(CollisionStrategy.C1);

    private CollisionStrategy type;

    private CollisionType(CollisionStrategy type) {
	this.type = type;
    }

    public void collision(GameObject g_obj ,GameObject other) {
	this.type.collision(g_obj, other);
    }

    private enum CollisionStrategy {
	C0 {      
	    void collision(GameObject g_obj, GameObject other) {	
	    }
	},
	C1 {      
	    void collision(GameObject g_obj, GameObject other) {
		if(other.getTag() == GameObjectTag.PLAYER){
		    other.getStatusInfo().damage(g_obj.getStatusInfo().getPower());
		    System.out.println(other.getStatusInfo().getHp());
		}
	    }
	};
	
	abstract void collision(GameObject g_obj, GameObject other);
    }
}
