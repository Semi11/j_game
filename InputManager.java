import processing.event.KeyEvent;

public enum InputManager {
    INSTANCE;
    
    private int[] keys = new int[256]; 
    private boolean[] key_state_up = new boolean[256];
    private boolean[] key_state_down = new boolean[256];
    private boolean keyPressed = false;
    private boolean keyReleased = false;

    public void keyEvent(KeyEvent e){
	switch(e.getAction()){
	case KeyEvent.PRESS:keyPressed(e);break;
	case KeyEvent.RELEASE:keyReleased(e);break;
	case KeyEvent.TYPE:keyTyped(e);break;
	}
    }

    public void keyPressed(KeyEvent e){
	int key = e.getKeyCode();
	if(key >= 0 && key < 256){
	    key_state_up[key] = false;
	    key_state_down[key] = true;
	    keyPressed = true;
	    keyReleased = false;
	}
    }
    
    public void keyReleased(KeyEvent e){
	int key = e.getKeyCode();
	if(key >= 0 && key < 256){
	    keys[key] = 0;
	    key_state_up[key] = true;
	    key_state_down[key] = false;
	    keyPressed = false;
	    keyReleased = true;
	}
    }

    public void keyTyped(KeyEvent e){}

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

    public int getKeyDownTime(int key){
	return keys[key];
    }

    public void update(){
	for(int i=0;i<key_state_up.length;i++){
	    if(key_state_down[i]){
		keys[i]++;
	    }
	}
	key_state_up = new boolean[256];
	keyReleased = false;
    }

}
