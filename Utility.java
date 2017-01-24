public class Utility {
    private PosInfo pos_info;
    private Drawable drawer;
    private int img_id;

    public Utility(Drawable drawer) {
	pos_info = new PosInfo();
	sta_info = new StatusInfo();
	this.setDrawer(drawer);
    }

    public Utility(Drawable drawer , PosInfo pos, PosInfo size, int image_id){
	this(drawer);
	pos_info.setPos(pos);
	pos_info.setSize(size);
	this.img_id = image_id;
    }

    public void init(Vec2 pos, Vec2 size) {
	pos_info.setPos(pos);
	pos_info.setSize(size);
	alive = true;
    }

    public void setDrawer(Drawable d) {
	this.drawer = d;
    }

    public void setImageID(int id) {
	this.img_id = id;
    }
  
    public PosInfo getPosInfo(){
	return this.pos_info; 
    }

    public boolean update() {
	return true;
    }

    public void draw() {
	drawer.draw(img_id, pos_info);
    }

}
