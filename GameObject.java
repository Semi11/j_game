import processing.core.*;

class GameObject{
  private PosInfo pos_info = new PosInfo();
  private MoveableStrategy move;
  private Drawable draw = DrawPImage.getInstance();
  PImage image;

  public GameObject(double px, double py, String img_name, int move_id) {
    init(px, py, img_name, move_id);
  }

  public GameObject() {
  }


  public void init(double px, double py, String img_name, int move_id) {
    pos_info.setPos(px, py);
    move = new MoveStrategy0(pos_info);
  }

  public void lI(PApplet p) {
    this.image = p.loadImage("test.png");
    p.image(this.image, (float)0.0, (float)0.0);
  }

  public boolean update() {
    move.update();
    return true;
  }

  public void draw() {
    draw.draw(pos_info, "test.png");
  }
}