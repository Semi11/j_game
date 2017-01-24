enum GameObjectTag {
    PLAYER,ENEMY,BULLET
}

public class GameObject extends Utility  {
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
    private boolean inactivatable;

    public GameObject(Drawable drawer) {
	super(drawer);
    }

    public void init(Vec2 pos, Vec2 size) {
	super.init(pos,size);
	alive = true;
    }

    // public void setDrawer(Drawable d) {
    // 	this.drawer = d;
    // }

    public void setGameObjectID(int id) {
	this.g_obj_id = id;
    }

    public int getGameObjectID() {
	return this.g_obj_id;
    }

    // public void setImageID(int id) {
    // 	this.img_id = id;
    // }

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
  
    // public PosInfo getPosInfo(){
    // 	return this.pos_info; 
    // }

    public StatusInfo getStatusInfo(){
	return this.sta_info; 
    }

    public int getCount(){
	return this.cnt;
    }

    public boolean isColisionStage(){
	return this.col_stage;
    }

    @Override
    public boolean update() {
	if(!active)return alive;
	mover.move(this);
	acter.act(this);
	pos_info.update();
	cnt++;
	if(sta_info.isAlive())return false;//only hp == 0
	return super.update();
    }

    @Override
    public void draw() {
	if(!active)return;
	super.draw();
    }

    public void collsion(GameObject other) {
	this.collisioner.collision(this, other);
    }

    public boolean isAlive() {
	return sta_info.isAlive();
    }

    public void kill(){
	this.sta_info.setHp(0);
    }

    public void setInactivatable(boolean b){
	this.inactivatable = b;
    }
    
    public void activate(){
	this.active = true;
    }
    
    public void inactivate(){
	if(!inactivatable)this.kill();
	else this.active = false;
    }
    
    public boolean isActive(){
	return this.active;
    }
    
}
