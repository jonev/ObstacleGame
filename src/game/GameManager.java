package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {
    private int playerXpos = 0;
    private int playerYpos = 0;

    public GameManager() {

    }

    @Override
    public void update(GameContainer gc, float dt) {
        if(gc.getInput().isKey(KeyEvent.VK_UP)){
            System.out.println("Up");
            if(playerYpos>0) playerYpos --;
        }
        if(gc.getInput().isKey(KeyEvent.VK_DOWN)){
            System.out.println("Down");
            playerYpos++;
        }
        if(gc.getInput().isKey(KeyEvent.VK_LEFT)){
            System.out.println("Left");
            if(playerXpos>0) playerXpos--;
        }
        if(gc.getInput().isKey(KeyEvent.VK_RIGHT)){
            System.out.println("Right");
            playerXpos++;
        }
    }

    @Override
    public void render(GameContainer gc, Renderer renderer) {
        renderer.drawGameBoard(this);
    }

    public int getPlayerXpos() {
        return playerXpos;
    }

    public int getPlayerYpos() {
        return playerYpos;
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}