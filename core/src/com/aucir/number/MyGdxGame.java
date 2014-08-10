package com.aucir.number;

import com.aucir.number.screens.Assets;
import com.aucir.number.screens.GameScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;


public class MyGdxGame extends Game {
    public 	ActionResolver actionResolver;

    public MyGdxGame(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    @Override
    public void create () {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Assets.instance.init(new AssetManager());
        setScreen(new GameScreen(this));
        actionResolver.loginGPGS();
    }
}
