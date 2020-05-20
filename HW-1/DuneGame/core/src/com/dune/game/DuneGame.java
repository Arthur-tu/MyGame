package com.dune.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class DuneGame extends ApplicationAdapter {

	private static class Tank {
		private Vector2 position;
		private Texture texture;
		private float angle;
		private float speed;
		//float x1;
		//float y1;

		public Tank(float x, float y) {
			this.position = new Vector2(x, y);
			this.texture = new Texture("tank.png");
			this.speed = 200.0f;
		}

		public void update(float dt) {
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				angle += 180.0f * dt;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				angle -= 180.0f * dt;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
				position.x += speed * MathUtils.cosDeg(angle) * dt;
				position.y += speed * MathUtils.sinDeg(angle) * dt;
			}
		}

		public float getx(){return position.x - 40;}

		public float gety(){return  position.y - 40;}

		//public float setX(float newx){return (position.x = newx);}

		//public float setY(float newy){return (position.y = newy);}

		public void render(SpriteBatch batch) {
			//if (!flag){
			batch.draw(texture, position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle, 0, 0, 80, 80, false, false);
			//} else {
			//	System.out.println(1);
			//position.x = setX (position.x+80);
			//	batch.draw(texture, position.x - 40, position.y - 40, 40, 40, 80, 80, 1, 1, angle, 0, 0, 80, 80, false, false);
			//}
		}

		public void dispose() { texture.dispose();}
	}

	private static class Circle{

		private Vector2 pos;
		private Texture tex;
		float x1;
		float y1;

		public Circle (float x, float y) {
			this.pos = new Vector2(x, y);
			this.tex = new Texture("circle.png");
		}

		public void render(SpriteBatch batch, boolean flag) {
			if (!flag) {
				batch.draw(tex, pos.x, pos.y);
			} else {
				x1=getRandomX();
				y1=getRandomY();
				batch.draw(tex,x1,y1);
				pos.x=setX(x1);
				pos.y=setY(y1);
			}
		}

		public float getRandomX(){
			int x;
			return x = (int) (Math.random()*1000-100);
		}

		public float getRandomY(){
			int y;
			return y = (int) (Math.random()*700-100);
		}

		public float setX(float newx) { return pos.x = newx;}

		public float setY(float newy) { return pos.y = newy;}

		public float getCirclex(){return  pos.x;}

		public float getCircley(){return  pos.y;}

		public void dispose() { tex.dispose(); }
	}

	SpriteBatch batch;
	Tank tank;
	Circle circle;
	static boolean info = false;
	//static boolean info2 = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		tank = new Tank(200,200);
		circle = new Circle(300,300);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		Gdx.gl.glClearColor(0, 0.4f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
	    //info2 = (tank.getx() == 40);
		tank.render(batch);
		circle.render(batch, info);
		info = Math.abs(tank.getx() - circle.getCirclex()) < 45 && Math.abs(tank.gety() - circle.getCircley()) < 45;
		batch.end();
	}

	public void update(float dt) { tank.update(dt);}

		@Override
		public void dispose () {
			batch.dispose();
			tank.dispose();
			circle.dispose();
		}
	}
