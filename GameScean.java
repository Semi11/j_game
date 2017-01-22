import processing.core.PApplet;

class GameScean implements Scean{
    private PApplet app;
    private GameObjectManager g_obj_manager;
    private MapManager map_manager;
    private CollisionManager col_manager;
    private String data_path;
    private int stage_num;
    private GameObject player;
    
    public GameScean(PApplet app, String path){
	this.app =app;
	this.data_path = path;
	stage_num =0;
	stageInit();
    }

    public void stageInit(){
	g_obj_manager = new GameObjectManager(app, data_path);
	map_manager = new MapManager(app, data_path, "map"+stage_num);
	col_manager = new CollisionManager(g_obj_manager.getObjects(), map_manager);
	
	g_obj_manager.add(map_manager.getGameObjectData());
	player = g_obj_manager.getPlayer();
    }

    public void update(){
	if(!player.isAlive())stageInit();
	g_obj_manager.update();
	col_manager.update();

    }

    public void draw(){
	map_manager.draw();
	g_obj_manager.draw();
    }

}
