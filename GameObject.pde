class GameObject {
  private PosInfo pos_info = new PosInfo();
  private MoveableStrategy move;
  private Drawable draw = new DrawPImage();
  PImage image;

  public GameObject(float px, float py, String img_name, int move_id) {
    init(px, py, img_name, move_id);
  }

  public GameObject() {
  }


  public void init(float px, float py, String img_name,int move_id) {
    pos_info.setPos(px, py);
    image = loadImage(img_name + ".png");
    move = new MoveStrategy0(pos_info);
  }

  public boolean update() {
    move.update();
    return true;
  }

  public void draw() {
    draw.draw(pos_info, image);
  }
}