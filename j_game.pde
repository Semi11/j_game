GameObject g_obj = new GameObject();
GameObject g_obj2 = new GameObject();

void setup() {
  size(300, 300);
  background(0);
  frameRate(60);

  PImageManager img_manager = new PImageManager(this);
  Drawable drawer = new DrawPImage(this, img_manager);
  GameObjectManager gm = new GameObjectManager(drawer);
  gm.addGameObject(0,10,10);
  //g_obj.init(width/2, 100, "test", "MOVE0");
  //g_obj2.init(width/2, 150, "test", "MOVE1");
}

void draw() {
  background(0);

  //g_obj.update();
  //g_obj.draw();
  //g_obj2.update();
  //g_obj2.draw();
}