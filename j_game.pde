boolean game_flg=true;
GameObject g_obj = new GameObject();

void setup() {
  size(300, 300);
  background(0);

  g_obj.init(100, 100, "test", 0);
  g_obj.lI(this);
}

void draw() {
  background(0);

  g_obj.update();
  g_obj.draw();
}