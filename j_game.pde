GameObjectManager gm;

void setup() {
  size(1000, 1000);
  background(0);
  frameRate(60);

  final String DATAPATH = sketchPath("data") + "/";

  gm = new GameObjectManager(this, DATAPATH);
  gm.addGameObject(0, 100, 100);

}

void draw() {
  background(0);

  gm.update();
  gm.draw();
  exit();
}