import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

enum InputManager implements KeyListener{
    INSTANCE;
    
    private int[] keys = new int[256]; 
    private boolean[] key_state_up = new boolean[256];
    private boolean[] key_state_down = new boolean[256];
    private boolean keyPressed = false;
    private boolean keyReleased = false;

    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();

	if(key >= 0 && key < 256){
	    key_state_up[key] = true;
	    key_state_down[key] = false;
	    keyPressed = true;
	    keyReleased = false;
	}
    }
    
    public void keyReleased(KeyEvent e){
	int key = e.getKeyCode();

	if(key >= 0 && key < 256){
	    keys[key] = 0;
	    key_state_up[key] = false;
	    key_state_down[key] = true;
	    keyPressed = false;
	    keyReleased = true;
	}
    }

    public void keyTyped(KeyEvent e){}
    
    public int getKeyDownTime(int key){
	return keys[key];
    }

    public boolean isKeyUp(int key){
	return key_state_up[key];
    }

    public boolean isKeyDown(int key){
	return key_state_down[key];
    }

    public boolean isAnyKeyUp(){
	return keyReleased;
    }

    public boolean isAnyKeyDown(){
	return keyPressed;
    }

    public void update(){
	for(int i=0;i<key_state_up.length;i++){
	    if(key_state_up[i]){
		keys[i]++;
	    }
	}
	key_state_up = new boolean[256];
	keyReleased = false;
    }

}
