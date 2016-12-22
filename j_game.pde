GameObjectManager gm;

void setup() {
  size(300, 300);
  background(0);
  frameRate(60);

  final String DATAPATH = sketchPath("data") + "/";

  PImageManager img_manager = new PImageManager(this, DATAPATH, "GameObjectImage");
  Drawable drawer = new DrawPImage(this, img_manager);
  gm = new GameObjectManager(drawer);
  gm.addGameObject(0, 100, 100);

}

void draw() {
  background(0);

  gm.update();
  gm.draw();
}