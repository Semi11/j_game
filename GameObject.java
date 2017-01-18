enum GameObjectTag {
    PLAYER,ENEMY,
}

class GameObject implements Cloneable {
    private PosInfo pos_info;
    private StatusInfo sta_info;
    private MoveType mover;
    private CollisionType collisioner;
    private Drawable drawer;
    private GameObjectTag tag;
    private int g_obj_id;
    private int img_id;
    private int cnt;
    private boolean alive;
    private GameObject player;

    public GameObject() {
	pos_info = new PosInfo();
	sta_info = new StatusInfo();
    }

    public GameObject(Drawable drawer) {
	this();
	this.setDrawer(drawer);
    }

    public void init(Vec2 pos, Vec2 size) {
	pos_info.setPos(pos);
	pos_info.setSize(size);
	alive = true;
    }

    public void setDrawer(Drawable d) {
	this.drawer = d;
    }

    public void setGameObjectID(int id) {
	this.g_obj_id = id;
    }

    public int getGameObjectID() {
	return this.g_obj_id;
    }

    public void setImageID(int id) {
	this.img_id = id;
    }

    public void setHp(int hp) {
	this.sta_info.setHp(hp);
    }

    public void setPower(int pow) {
	this.sta_info.setPower(pow);
    }

    public void setMoveType(String type) {
	this.mover = MoveType.valueOf("MOVE"+type);
    }

    public void setCollisionType(String type) {
	this.collisioner = CollisionType.valueOf("COLLISION"+type);
    }

    public void setTag(String tag) {
	this.tag =  GameObjectTag.valueOf(tag);
    }

    public GameObjectTag getTag() {
	return this.tag; 
    }

    public void setPlayer(GameObject p){
	this.player = p;
    }

    public GameObject getPlayer(){
	return this.player;
    }
  
    public PosInfo getPosInfo(){
	return this.pos_info; 
    }

    public StatusInfo getStatusInfo(){
	return this.sta_info; 
    }

    public boolean update() {
	mover.update(this);
	pos_info.update();
	return true;
    }

    public void draw() {
	drawer.draw(img_id, pos_info);
    }

    public void collsion(GameObject other) {
	this.collisioner.collision(this, other);
    }

    boolean isAlive() {
	return alive;
    }
}
