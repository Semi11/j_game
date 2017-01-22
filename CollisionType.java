public enum CollisionType {
    COLLISION0(CollisionStrategy.C0),
    COLLISION1(CollisionStrategy.C1),
    COLLISION2(CollisionStrategy.C2);

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
		if(other.getTag() == GameObjectTag.ENEMY){
		    g_obj.getStatusInfo().damage(other.getStatusInfo().getPower());
		}
	    }
	},
	//enemy & enemy bullet
	C1 {      
	    void collision(GameObject g_obj, GameObject other) {
		if(other.getTag() == GameObjectTag.PLAYER){
		    g_obj.getStatusInfo().damage(other.getStatusInfo().getPower());
		}      
	    }
	},
	//player bullet
	C2 {      
	    void collision(GameObject g_obj, GameObject other) {	
		if(g_obj.getTag() == GameObjectTag.PLAYER){
		    C0.collision(g_obj, other);
		}else if(g_obj.getTag() == GameObjectTag.ENEMY){
		    C1.collision(g_obj, other);
		}
	    }
	};	
	abstract void collision(GameObject g_obj, GameObject other);
    }
}
