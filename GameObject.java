enum GameObjectTag {
    PLAYER,ENEMY,BULLET
}

class GameObject implements Cloneable {
    private PosInfo pos_info;
    private StatusInfo sta_info;
    private MoveType mover;
    private ActType acter;
    private CollisionType collisioner;
    private Drawable drawer;
    private GameObjectTag tag;
    private int g_obj_id;
    private int img_id;
    private int cnt;
    private boolean alive;
    private GameObjectManager manager;
    private boolean col_stage;
    private boolean active;

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
	active = false;
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

    public void setActType(String type) {
	this.acter = ActType.valueOf("ACT"+type);
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

    public void setManager(GameObjectManager m){
	this.manager = m;
    }

    public void setCollisionStage(boolean b){
	this.col_stage = b;
    }

    public GameObjectManager getManager(){
	return this.manager;
    }
  
    public PosInfo getPosInfo(){
	return this.pos_info; 
    }

    public StatusInfo getStatusInfo(){
	return this.sta_info; 
    }

    public int getCount(){
	return this.cnt;
    }

    public boolean isColisionStage(){
	return this.col_stage;
    }

    public boolean update() {
	if(!active)return alive;
	mover.move(this);
	acter.act(this);
	pos_info.update();
	cnt++;
	if(sta_info.getHp()==0)alive=false;//only hp == 0
	return alive;
    }

    public void draw() {
	if(!active)return;
	drawer.draw(img_id, pos_info);
    }

    public void collsion(GameObject other) {
	this.collisioner.collision(this, other);
    }

    public boolean isAlive() {
	return alive;
    }

    public void kill(){
	this.sta_info.setHp(0);
    }
    
    public void activate(){
	this.active = true;
    }
    
    public void inactivate(){
	this.active = false;
    }
    
    public boolean isActive(){
	return this.active;
    }
    
}
