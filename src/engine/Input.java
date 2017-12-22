package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {
    private GameContainer gc;
    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLastFrame = new boolean[NUM_KEYS];

    public Input(GameContainer gc) {
        this.gc = gc;
        gc.getWindow().getCanvas().addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void update(){
        for (int i = 0; i < NUM_KEYS; i++) {
            keysLastFrame[i] = keys[i];
        }
    }

    public boolean isKey(int keyCode){
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode){
        return !keys[keyCode] && keysLastFrame[keyCode];
    }

    public boolean isKeyDown(int keyCode){
        return keys[keyCode] && !keysLastFrame[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
