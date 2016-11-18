package com.game.snake;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.snake.screens.GameScreen;

public class Snake extends Game{
		
	@Override
	public void create () {
            this.setScreen(new GameScreen (this, null));            
	}
        
        private void handleInput() {
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                Gdx.app.exit();
            }
        }

	@Override
	public void render () {
            handleInput();   
            super.render();
	}
	
	@Override
	public void dispose () {
		if (this.getScreen() != null) {
                this.getScreen().dispose();
            }
	}      
}
