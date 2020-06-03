package com.dune.game.core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Projectile {
    private Vector2 position;
    private Vector2 velocity;
    private TextureRegion textureRegion;
    private boolean flag;

    public boolean isFlag(){ return flag;}

    public void setFlag(boolean info){flag = info;}

    public Vector2 getPosition() {return position;}

    public void setup( Vector2 startPosition, float angle) {
        velocity.add(500.0f * MathUtils.cosDeg(angle), 500.0f * MathUtils.sinDeg(angle));
        position.set(startPosition);
    }

    public Projectile(TextureAtlas textureAtlas, float angle){
       this.textureRegion = textureAtlas.findRegion("bullet");
       this.position = new Vector2(0,0);
       this.velocity = new Vector2(0,0);
       this.flag = false;
    }

    public void update(float dt) {
        position.mulAdd(velocity, dt);
        checkBounds();
    }

    public void render (SpriteBatch batch){if (flag) batch.draw(textureRegion, getPosition().x, getPosition().y);}

    private void checkBounds() {
        if (position.x < 0 || position.x>1280){
           flag = false;
      }
        if (position.y<0 || position.y>720){
            flag = false;
       }
    }
}
