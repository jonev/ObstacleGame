package engine;

import game.GameManager;

import java.awt.*;
import java.awt.image.DataBufferInt;

public class Renderer {
    private int pW, pH; // pixels height and width
    private int[] p; // pixels

    public Renderer(GameContainer gc){
        pW = gc.getWidth();
        pH = gc.getHeight();
        p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData(); // acces to the pixel data to image
    }

    public void clear(){
        for (int i = 0; i < p.length; i++) p[i] = 0;
    }

    public void setPixel(int x, int y, int value){
        if(x<0 || x>pW || y<0 || y>pH) return;
        p[x + y *pW] = value;
    }

    public void drawGameBoard(GameManager gm){
        drawPlayer(gm.getPlayerPos());
        for (int horizontal = 0; horizontal < gm.BOARDSIZE; horizontal++) {
            for (int vertical = 0; vertical < gm.BOARDSIZE; vertical++) {
                if(gm.getObsticles()[vertical][horizontal]){
                    setPixel(horizontal, vertical, 0xffff);
                }
            }
        }
    }

    private void drawPlayer(int pos){
        setPixel(0, pos, 0xfffff);
    }



}
