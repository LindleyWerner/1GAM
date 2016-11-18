/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.game.snake.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

/**
 *
 * @author lindley
 */
public class GameScreen extends BaseScreen{
    SpriteBatch batch;
    private final Sprite snake, food;
    private int direction = 1; //right 1, left 2, up 3, down 4
    private float velocity = 1.0f;    
    private int snakeSize = 1;
    private final Random random;
    private boolean gameOver = false;
        
    public GameScreen(com.badlogic.gdx.Game game, BaseScreen previous) {
        super(game, previous);
        this.batch = new SpriteBatch();
        this.snake = new Sprite (new Texture("snake/snake.png"));
        this.food = new Sprite(new Texture("snake/food.png"));
        this.snake.setPosition(0, 0);
        this.random = new Random();
        
        
        
    }

    @Override
    //Executa somente uma vez no início
    public void appear() {        
        food.setPosition(random.nextFloat()*this.viewport.getWorldWidth(), random.nextFloat()*this.viewport.getWorldHeight());
                
    }

    @Override
    public void handleInput() {
        //Controles. Coloca restrição que não pode ir em uma direção contrária
        //à atual
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(direction != 4)
                direction = 3;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(direction != 3)
                direction = 4;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(direction != 1)    
                direction = 2;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(direction != 2)
                direction = 1;
        }        
    }

    @Override
    public void update(float dt) {
        float PosX = snake.getX(), PosY = snake.getY();
        if(snakeSize%4 == 0){
            velocity += 2;
        }
        switch(direction){
            case 1:
                PosX += velocity;
            break;
            case 2:
                PosX -= velocity;
            break;
            case 3:
                PosY += velocity;
            break;
            case 4:
                PosY -= velocity;
            break;
        }
        snake.setPosition(PosX, PosY);
        colisionTest();
        
        if(gameOver){
            //super.game.setScreen(new MenuScreen(super.game, this));
        }
        
        //System.out.println(this.viewport.getWorldWidth() + ", " + this.viewport.getWorldHeight());
        System.out.println(food.getX() + ", " + food.getY());
        System.out.println(PosX + ", " + PosY);
        System.out.println(snakeSize);
        System.out.println();
    }

    @Override
    public void draw() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Gdx.gl.glClearColor(1, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);            
            batch.begin();
            snake.draw(batch);
            food.draw(batch);
            
            batch.end();
           
    }

    @Override
    public void cleanUp() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        super.dispose();
    }    
    
    public void colisionTest(){        
        if(snake.getBoundingRectangle().overlaps(food.getBoundingRectangle())){
            snakeSize ++;
            food.setPosition(random.nextFloat()*this.viewport.getWorldWidth(), random.nextFloat()*this.viewport.getWorldHeight());
        }        
        if(snake.getX() < 0 || snake.getX() > this.viewport.getWorldWidth() ||
                snake.getY() < 0 || snake.getY() > this.viewport.getWorldHeight() ){
            gameOver = true;
        }
        
    }
    
}
