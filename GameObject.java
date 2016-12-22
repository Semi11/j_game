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

  public boolean update() {
    move.update(pos_info, cnt);
    cnt++;
    return true;
  }

  public void draw() {
    drawer.draw(pos_info, img_id);
  }

  boolean isAlive() {
    return alive;
  }
}