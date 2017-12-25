package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Position;
import engine.Renderer;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {
    public static final int BOARDSIZE = 10;
    private int playerPos = 0;
    private int round = 0;
    private int currentObsticle = 0;
    private boolean[][] obsticles = new boolean[BOARDSIZE][BOARDSIZE]; // | from top to bottom | from left to right
    private boolean[][] obsticlePattern = new boolean[BOARDSIZE][1]; // | from top to bottom | from left to right

    public GameManager() {
        //for (int i = 0; i < 10; i++) {
        //    for (int j = 0; j < 10; j++) {
        //        if((i+j)%7==0){
        //            obsticles[i][j] = true;
        //        }
        //    }
        //}
        obsticlePattern[0][0] = true;
        obsticlePattern[2][0] = true;
        obsticlePattern[4][0] = true;
        obsticlePattern[6][0] = true;
        obsticlePattern[8][0] = true;

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
        checkForLoss();
        for (int horizontal = 1; horizontal < BOARDSIZE; horizontal++) {
            for (int vertical = 0; vertical < BOARDSIZE; vertical++) {
                obsticles[vertical][horizontal-1] = obsticles[vertical][horizontal];
            }
        }
        if(currentObsticle < obsticlePattern[0].length){
            for (int vertical = 0; vertical < BOARDSIZE; vertical++) {
                obsticles[vertical][BOARDSIZE-1] = obsticlePattern[vertical][currentObsticle];
            }
            currentObsticle++;
        } else {
            for (int vertical = 0; vertical < BOARDSIZE; vertical++) {
                obsticles[vertical][BOARDSIZE-1] = false;
            }
        }
        renderer.drawGameBoard(this);
        if(round > (BOARDSIZE + obsticlePattern[0].length)){
            System.out.println("Win");
            round = 0;
            currentObsticle = 0;
        } else {
            round++;
        }
    }

    private void checkForLoss(){
        if(obsticles[playerPos][0]){
            System.out.println("Loss");
        }
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