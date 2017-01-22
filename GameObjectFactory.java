import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class GameObjectFactory {
    private Drawable drawer;
    private List<GameObject> prototype_list = new ArrayList<GameObject>();
    private List<HashMap<String, String>> g_obj_data_list;

    public GameObjectFactory(Drawable d, String path) {
	CSVReader csv_reader = new CSVReader();
	drawer = d;
	g_obj_data_list = csv_reader.read(path + "GameObjectData.csv");
    }

    public GameObject getGameObject() {
	return new GameObject(drawer);
    }

    public GameObject getGameObject(int id) {
	HashMap<String, String> data = g_obj_data_list.get(id);
	GameObject g = this.getGameObject();

	g.setGameObjectID(Integer.parseInt(data.get("GameObjectID")));
	g.setImageID(Integer.parseInt(data.get("ImageID")));
	g.setHp(Integer.parseInt(data.get("HP")));
	g.setPower(Integer.parseInt(data.get("Power")));
	g.setMoveType(data.get("MoveType"));
	g.setActType(data.get("ActType"));
	g.setCollisionType(data.get("CollisionType"));
	g.setTag(data.get("Tag"));
	g.setCollisionStage(Boolean.valueOf(data.get("CollisionStage")));

	return g;
    }
}
