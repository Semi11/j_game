ActionGame ag;

void settings(){
    size(960, 640);
}

void setup() {
    background(0);
    frameRate(60);
    imageMode(CORNER);

    final String DATAPATH = sketchPath("data") + "/";

    registerMethod("keyEvent",InputManager.INSTANCE);
    ag = new ActionGame(this,DATAPATH);
}

void draw() {
    ag.update();
}