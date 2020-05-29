package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BattleMap {
    private TextureRegion grassTexture;
    private TextureRegion oilTexture;
    private int[][] data;

    public BattleMap() {
        this.grassTexture = Assets.getInstance().getAtlas().findRegion("grass");
        this.oilTexture = Assets.getInstance().getAtlas().findRegion("oil");
        this.data= new int [16][9];
        for (int i = 0; i < 16 ; i++) {
            for (int j = 0; j < 9 ; j++) {
                if (MathUtils.random() < 0.1f){
                    data[i][j] = 1;
                } else {
                    data[i][j] = 0;
                }
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if(data[i][j] == 0) {
                    batch.draw(grassTexture,i*80,j*80);
                } else {
                    batch.draw(oilTexture,i*80,j*80);
                }
            }
        }
    }

    public boolean isOilTaken(Tank tank){
        int posX = (int) tank.position.x/80;
        int posY = (int) tank.position.y/80;
        if (data[posX][posY] == 1){
            data[posX][posY] = 0;
            return true;
        } else
        return false;
    }

}
