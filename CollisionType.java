public enum CollisionType {
    COLLISION0(CollisionStrategy.C0),
    COLLISION1(CollisionStrategy.C1),
    COLLISION2(CollisionStrategy.C2),
    COLLISION3(CollisionStrategy.C3);

    private CollisionStrategy type;

    private CollisionType(CollisionStrategy type) {
	this.type = type;
    }

    public void collision(GameObject g_obj ,GameObject other) {
	this.type.collision(g_obj, other);
    }

    private enum CollisionStrategy {
	//player
	C0 {      
	    void collision(GameObject g_obj, GameObject other) {	
		if(other.getTag() == GameObjectTag.ENEMY || other.getTag() == GameObjectTag.ITEM){
		    other.getStatusInfo().damage(g_obj.getStatusInfo().getPower());
		}
	    }
	},
	//enemy & enemy bullet
	C1 {      
	    void collision(GameObject g_obj, GameObject other) {
		if(other.getTag() == GameObjectTag.PLAYER){
		    other.getStatusInfo().damage(g_obj.getStatusInfo().getPower());
		}      
	    }
	},
	//enemy bullet
	C2 {      
	    void collision(GameObject g_obj, GameObject other) {
		if(other.getTag() == GameObjectTag.PLAYER){
		    C1.collision(g_obj, other);
		    g_obj.getStatusInfo().damage(other.getStatusInfo().getPower());
		}      
	    }
	},
	//player bullet
	C3 {      
	    void collision(GameObject g_obj, GameObject other) {	
		if(other.getTag() == GameObjectTag.ENEMY){
		    C0.collision(g_obj, other);
		    g_obj.getStatusInfo().damage(other.getStatusInfo().getPower());
		}
	    }
	};
	abstract void collision(GameObject g_obj, GameObject other);
    }
}
