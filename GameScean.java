import java.io.File;
import processing.core.PApplet;

public class GameScean implements Scean{
    private PApplet app;
    private ActionGame ag;
    private GameObjectManager g_obj_manager;
    private MapManager map_manager;
    private CollisionManager col_manager;
    private String data_path;
    private int stage_num;
    private int now_stage;
    private GameObject player;
    private GameObject boss;
    private PosInfo screan = new PosInfo();
    
    public GameScean(ActionGame ag,PApplet app, String path){
	this.ag = ag;
	this.app =app;
	this.data_path = path;
	screan.setSize(app.width,app.height);
	File dir = new File(path + "MapData");
	stage_num = dir.listFiles().length;
	now_stage = Integer.parseInt(TextFileIO.INSTANCE.readText(path+ "save.dat").get(0));
	stageInit();
    }

    public void stageInit(){
	g_obj_manager = new GameObjectManager(app, data_path, screan);
	map_manager = new MapManager(app, data_path, "map"+now_stage, screan);
	col_manager = new CollisionManager(g_obj_manager.getObjects(), map_manager, screan);
	
	g_obj_manager.add(map_manager.getGameObjectData());
	player = g_obj_manager.getPlayer();
	boss = g_obj_manager.getBoss();
    }

    protected void updateScrean(){
	Vec2 player_pos = player.getPosInfo().getPos();
	Vec2 map = new Vec2(map_manager.getStageWidth(), map_manager.getStageHeight());
	double x = Math.max( Math.min(screan.getSize().x/2.0 - player_pos.x, 0), screan.getSize().x - map.x );
	double y = Math.max( Math.min(screan.getSize().y/2.0 - player_pos.y, 0), screan.getSize().y - map.y );
	
	screan.setPos(x,y);
    }

    protected boolean nextStage(){
	if(++now_stage > stage_num){
	    //ag.pushScean(new EndingScean(ag,app,data_path));
	    return false;
	}
	TextFileIO.INSTANCE.writeText(data_path + "save.dat", String.valueOf(now_stage));
	stageInit();

	return true;
    }
    
    public boolean update(){
	g_obj_manager.update();
	col_manager.update();
	updateScrean();
	if(!player.isAlive())stageInit();
	if(!boss.isAlive())return nextStage();
	return true;
    }

    public void draw(){
	map_manager.draw();
	g_obj_manager.draw();
    }

}
