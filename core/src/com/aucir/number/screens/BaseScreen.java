package com.aucir.number.screens;

import com.aucir.number.MyGdxGame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;


public abstract class BaseScreen implements Screen {
    protected MyGdxGame game;

    public BaseScreen(MyGdxGame game) {
        this.game = game;
    }

    public abstract void render (float deltaTime);
    public abstract void resize (int width, int height);
    public abstract void show ();
    public abstract void hide ();
    public abstract void pause ();

    public void resume () {
        Assets.instance.init(new AssetManager());
    }

    public void dispose () {
        Assets.instance.dispose();
    }
}
