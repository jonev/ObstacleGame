package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Position;
import engine.Renderer;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {
    private int playerPos = 0;
    private int currentObsticle = 0;
    private boolean[][] obsticles = new boolean[10][10];
    private boolean[][] obsticlePattern = new boolean[10][10];

    public GameManager() {
        //for (int i = 0; i < 10; i++) {
        //    for (int j = 0; j < 10; j++) {
        //        if((i+j)%7==0){
        //            obsticles[i][j] = true;
        //        }
        //    }
        //}
        obsticlePattern[0][0] = true;
        obsticlePattern[0][2] = true;
        obsticlePattern[0][4] = true;
        obsticlePattern[0][6] = true;
        obsticlePattern[0][8] = true;

    }

    @Override
    public void update(GameContainer gc, float dt) {
        if(gc.getInput().isKey(KeyEvent.VK_UP)){
            System.out.println("Up");
            if(playerPos>0) playerPos--;
        }
        if(gc.getInput().isKey(KeyEvent.VK_DOWN)){
            System.out.println("Down");
            if(playerPos<9) playerPos++;
        }
    }

    @Override
    public void render(GameContainer gc, Renderer renderer) {
        if(currentObsticle < 9) currentObsticle++;
        for (int i = 0; i < currentObsticle; i++) {
            for (int j = 0; j < 10; j++) {
                obsticles[i][j] = obsticlePattern[i][j];
            }
        }
        renderer.drawGameBoard(this);
    }

    public int getPlayerPos() {
        return playerPos;
    }

    public boolean[][] getObsticles() {
        return obsticles;
    }

    public static void main(String[] args) {
        GameContainer gc = new GameContainer(new GameManager());
        gc.start();
    }
}