enum GameObjectTag {
    PLAYER,ENEMY,
}

class GameObject implements Cloneable {
  private PosInfo pos_info;
  private StatusInfo sta_info;
  private MoveType mover;
  private Drawable drawer;
  private GameObjectTag tag;
  private int g_obj_id;
  private int img_id;
  private int cnt;
  private boolean alive;
  private boolean gravity;

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

  public void setTag(String tag) {
    this.tag =  GameObjectTag.valueOf(tag);
  }

  public void setGravity(boolean g){
    this.gravity =g; 
  }
  
  public boolean isGravity(){
   return this.gravity; 
  }
  
  public PosInfo getPosInfo(){
   return this.pos_info; 
  }

  public boolean update() {
    mover.update(pos_info, cnt);
    pos_info.update();
    return true;
  }

  public void draw() {
    drawer.draw(img_id, pos_info);
  }

  boolean isAlive() {
    return alive;
  }
}
