enum GameObjectTag {
  ENEMY,
}

class GameObject implements Cloneable {
  private PosInfo pos_info;
  private StatusInfo sta_info;
  private MoveType move;
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

  public void init(double px, double py) {
    pos_info.setPos(px, py);
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
    this.move = MoveType.valueOf("MOVE"+type);
  }

  public void setTag(String tag) {
    this.tag =  GameObjectTag.valueOf(tag);
  }

  public void setSize(double x, double y) {
    this.pos_info.setSize(x,y);
  }

  public void setGravity(boolean g){
    this.gravity =g; 
  }
  
  public boolean getGravity(){
   return this.gravity; 
  }
  
  public PosInfo getPosInfo(){
   return this.pos_info; 
  }

  public boolean update() {
    move.update(pos_info, cnt);
    cnt++;
    return true;
  }

  public void draw() {
    drawer.draw(img_id, pos_info);
  }

  boolean isAlive() {
    return alive;
  }
}