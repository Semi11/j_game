ActionGame ag;

public void settings(){
    size(1000, 1000);
}

public void setup() {
    background(0);
    frameRate(60);
    imageMode(CORNER);

    final String DATAPATH = sketchPath("data") + "/";
    ag = new ActionGame(this,DATAPATH);
}

public void draw() {
    ag.update();
}
