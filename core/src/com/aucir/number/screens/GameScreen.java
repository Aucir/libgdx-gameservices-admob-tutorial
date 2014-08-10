package com.aucir.number.screens;

import com.aucir.number.MyGdxGame;
import com.aucir.number.util.Constants;
import com.aucir.number.util.GamePreferences;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Random;


public class GameScreen extends BaseScreen {
    private static final String TAG = GameScreen.class.getName();


    private Stage stage;

    private Skin skinLibgdx;

    private TextButton btnEsay;
    private TextButton btnHard;
    private CustomLabel scoreLabel;
    private CustomLabel timerLabel;

    private CustomLabel label4;
    private CustomLabel label3;
    private CustomLabel label2;
    private CustomLabel label1;

    private CustomLabel labelCenter;

    private CustomLabel winLabelScore;
    private CustomLabel winLabelBestScore;

    private Window winStart;
    private Window winGameOver;


    private boolean gameEnd = true;

    private long lastUpdateTime = 0;

    private int correctLabelid = 1;

    private int tryTime;

    public GameScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float deltaTime) {
        // No updates when game is paused

        // TimeUtils.millis();

        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(deltaTime);
        stage.draw();

        long currentTime = TimeUtils.millis();
        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
            return;
        }
        long timeDelta = currentTime - lastUpdateTime;

        // Gdx.app.debug("","timeDelta ====:" + timeDelta);

        if (tryTime == 0 && !gameEnd) {
            gameOver();
            return;
        }

        if (timeDelta > GamePreferences.instance.getDeltaTime()) {
            changeView();
            lastUpdateTime = currentTime;
        }

        scoreLabel.updateText("SCORE:" + GamePreferences.instance.getScore());
        timerLabel.updateText("TIME:" + tryTime);

        winLabelScore.updateText("" + GamePreferences.instance.getScore());// = new CustomLabel(, skinLibgdx, "default-font", Color.ORANGE);
        winLabelBestScore.updateText("BEST SCORE:" + GamePreferences.instance.getBestScore());// = new CustomLabel(, skinLibgdx, "default-font", Color.ORANGE);

//        if (!game.actionResolver.getSignedInGPGS())
//            game.actionResolver.loginGPGS();

    }

    private void changeView() {
        int x = MathUtils.random(1, 9);
        int y = MathUtils.random(1, 9);
        if (x == 2 && y == 2) {
            x = MathUtils.random(3, 9);
        }

        int symbol = MathUtils.random(0, 3);

        correctLabelid = MathUtils.random(1, 4);

        int add = x + y;
        int minus = x - y;
        int times = x * y;
        double div = (double) x / (double) y;

        String divide = String.format("%.02f", div);

        switch (symbol) {
            case 0:
                labelCenter.updateText(x + "+" + y);
                switch (correctLabelid) {
                    case 1:
                        label1.updateText("" + add);
                        label2.updateText("" + times);
                        label3.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 2:
                        label2.updateText("" + add);
                        label3.updateText("" + times);
                        label1.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 3:
                        label3.updateText("" + add);
                        label2.updateText("" + times);
                        label1.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 4:
                        label4.updateText("" + add);
                        label3.updateText("" + times);
                        label2.updateText(divide);
                        label1.updateText("" + minus);
                        break;
                }


                break;
            case 1:
                labelCenter.updateText(x + "-" + y);
                switch (correctLabelid) {
                    case 1:
                        label4.updateText("" + add);
                        label2.updateText("" + times);
                        label3.updateText(divide);
                        label1.updateText("" + minus);
                        break;
                    case 2:
                        label1.updateText("" + add);
                        label3.updateText("" + times);
                        label4.updateText(divide);
                        label2.updateText("" + minus);
                        break;
                    case 3:
                        label1.updateText("" + add);
                        label4.updateText("" + times);
                        label2.updateText(divide);
                        label3.updateText("" + minus);
                        break;
                    case 4:
                        label2.updateText("" + add);
                        label1.updateText("" + times);
                        label3.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                }
                break;
            case 2:
                labelCenter.updateText(x + "*" + y);
                switch (correctLabelid) {
                    case 1:
                        label2.updateText("" + add);
                        label1.updateText("" + times);
                        label3.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 2:
                        label4.updateText("" + add);
                        label2.updateText("" + times);
                        label1.updateText(divide);
                        label3.updateText("" + minus);
                        break;
                    case 3:
                        label1.updateText("" + add);
                        label3.updateText("" + times);
                        label2.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 4:
                        label3.updateText("" + add);
                        label4.updateText("" + times);
                        label2.updateText(divide);
                        label1.updateText("" + minus);
                        break;
                }
                break;
            case 3:
                labelCenter.updateText(x + "/" + y);
                switch (correctLabelid) {
                    case 1:
                        label3.updateText("" + add);
                        label2.updateText("" + times);
                        label1.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 2:
                        label3.updateText("" + add);
                        label1.updateText("" + times);
                        label2.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 3:
                        label2.updateText("" + add);
                        label1.updateText("" + times);
                        label3.updateText(divide);
                        label4.updateText("" + minus);
                        break;
                    case 4:
                        label2.updateText("" + add);
                        label3.updateText("" + times);
                        label4.updateText(divide);
                        label1.updateText("" + minus);
                        break;
                }
                break;
        }

        //labelCenter.getStyle().fontColor = getRandColor();
        labelCenter.updateColor(getRandColor());
        label1.updateColor(getRandColor());
        label2.updateColor(getRandColor());
        label3.updateColor(getRandColor());
        label4.updateColor(getRandColor());

        if (!gameEnd)
            tryTime--;

    }

    private Color getRandColor() {
        Color color = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);
        return color;
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

        //stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        rebuildStage();

    }

    private void rebuildStage() {

        skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));


        Table layerObjects = buildResult();
        Table centreObjects = buildCenterLabel();

        stage.clear();


        scoreLabel = new CustomLabel("SCORE:0", Assets.instance.fonts.smallStyle);
        scoreLabel.setPosition(20, stage.getHeight() - 40);
        stage.addActor(scoreLabel);


        timerLabel = new CustomLabel("TIME:0", Assets.instance.fonts.smallStyle);
        timerLabel.setPosition(stage.getWidth() - timerLabel.getWidth() - 40, stage.getHeight() - 40);
        stage.addActor(timerLabel);

        stage.addActor(layerObjects);

        stage.addActor(centreObjects);


        stage.addActor(buildStartWindowLayer());

        stage.addActor(buildGameOverWindowLayer());

    }

    private Table buildCenterLabel() {
        Table tbl = new Table();
        tbl.debug();
        tbl.setFillParent(true);

        labelCenter = new CustomLabel("4+5", Assets.instance.fonts.mediumStyle);
        // labelCenter.setFontScale(2.5f);
        // labelCenter.getStyle().fontColor = Color.BLUE;
        //labelCenter.setColor(Color.BLUE);
        //labelCenter.getStyle().font = Assets.instance.fonts.defaultNormal;  //.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        tbl.add(labelCenter).expand();

        return tbl;
    }

    private Table buildButtons() {
        Table tbl = new Table();
        tbl.debug();
        // tbl.setFillParent(true);


        btnEsay = new TextButton("EASY", skinLibgdx);
        //btnEsay.setSize(100,40);
        btnEsay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GamePreferences.instance.setGameMode(GamePreferences.EASY_MODE);
                startGame(true);
            }
        });
        tbl.add(btnEsay).size(100, 40).bottom().expand().padRight(20);


        btnHard = new TextButton("HARD", skinLibgdx);
        btnHard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GamePreferences.instance.setGameMode(GamePreferences.HARD_MODE);

                startGame(true);
            }
        });

        tbl.add(btnHard).size(100, 40).bottom().expand();


        return tbl;
    }

    private void startGame(boolean resetScore) {
        if (resetScore) {
            GamePreferences.instance.setScore(0);
        }
        winStart.setVisible(false);
        winGameOver.setVisible(false);
        gameEnd = false;
        tryTime = GamePreferences.instance.getTryTime();

        changeView();
        lastUpdateTime = TimeUtils.millis();


    }


    private Table buildResult() {
        Table tbl = new Table();
        //tbl.debug();
        tbl.setFillParent(true);

        label1 = new CustomLabel("Label 1", Assets.instance.fonts.largeStyle);
        // label1.setFontScale(4);

        label1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if (gameEnd)
                    return;

                if (correctLabelid == 1) {
                    GamePreferences.instance.addScore();
                    startGame(false);
                } else {
                    gameOver();
                }
            }
        });

        label2 = new CustomLabel("Label 2", Assets.instance.fonts.largeStyle);
        label2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gameEnd)
                    return;
                if (correctLabelid == 2) {
                    GamePreferences.instance.addScore();
                    startGame(false);
                } else {
                    gameOver();
                }
            }
        });
        label3 = new CustomLabel("Label 3", Assets.instance.fonts.largeStyle);
        label3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gameEnd)
                    return;
                if (correctLabelid == 3) {
                    GamePreferences.instance.addScore();
                    startGame(false);
                } else {
                    gameOver();
                }
            }
        });
        label4 = new CustomLabel("Label 4", Assets.instance.fonts.largeStyle);

        label4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (gameEnd)
                    return;
                if (correctLabelid == 4) {
                    GamePreferences.instance.addScore();
                    startGame(false);
                } else {
                    gameOver();
                }
            }
        });

        tbl.add(label1).expand();
        tbl.add(label2).expand();

        tbl.row();


        tbl.add(label3).expand();
        tbl.add(label4).expand();

        return tbl;
    }

    private void gameOver() {
        gameEnd = true;
        winGameOver.setVisible(true);

        if (!game.actionResolver.getSignedInGPGS())
            game.actionResolver.loginGPGS();
        else {
            int bestscore = GamePreferences.instance.getBestScore();
            game.actionResolver.submitScoreGPGS(bestscore, GamePreferences.instance.getLeaderboardid());

            String achid = null;
            //
            if (bestscore > 10) {
                achid = "CgkIobeF2I8XEAIQCA";
            } else if (bestscore > 50) {
                achid = "CgkIobeF2I8XEAIQCQ";

            } else if (bestscore > 100) {
                achid = "CgkIobeF2I8XEAIQCg";

            } else if (bestscore > 150) {
                achid = "CgkIobeF2I8XEAIQCw";

            } else if (bestscore > 200) {
                achid = "CgkIobeF2I8XEAIQDA";
            }
            if (achid != null)
                game.actionResolver.unlockAchievementGPGS(achid);

        }
    }

    private Table buildGameOverWindowLayer() {
        winGameOver = new Window("GAME OVER", skinLibgdx);

        Table tbl = new Table();
        //tbl.pad(10,10,0,10);

        winLabelScore = new CustomLabel("", Assets.instance.fonts.smallStyle);
        winLabelBestScore = new CustomLabel("BEST SCORE:", Assets.instance.fonts.smallStyle);

        tbl.add(winLabelScore).pad(10, 10, 10, 10);
        tbl.row();
        tbl.add(winLabelBestScore).pad(10, 10, 20, 10);
        tbl.row();
        TextButton retry = new TextButton("RETRY", skinLibgdx);
        tbl.add(retry).size(190, 40).padBottom(20);

        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                winStart.setVisible(true);
                winGameOver.setVisible(false);
            }
        });

        tbl.row();

        TextButton lb = new TextButton("LEADERBOARD", skinLibgdx);
        lb.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.actionResolver.getLeaderboardGPGS(GamePreferences.instance.getLeaderboardid());

            }
        });
        tbl.add(lb).size(190, 40).padBottom(20);

        tbl.row();
        TextButton achievements = new TextButton("ACHIEVEMENTS", skinLibgdx);
        achievements.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.actionResolver.getAchievementsGPGS();
            }
        });
        tbl.add(achievements).size(190, 40).padBottom(20);


        winGameOver.add(tbl).pad(10, 10, 10, 10);

        winGameOver.setColor(1, 1, 1, 0.8f);
        //winStart.setVisible(false);
        winGameOver.debug();
        winGameOver.pack();
        winGameOver.setPosition(stage.getWidth() / 2 - winGameOver.getWidth() / 2, stage.getHeight() / 2 - winGameOver.getHeight() / 2);

        winGameOver.setVisible(false);
        return winGameOver;
    }

    private Table buildStartWindowLayer() {
        winStart = new Window("PLAY GAME", skinLibgdx);


        winStart.add(new Label("Choose game mode to play.", skinLibgdx, "default-font", Color.ORANGE)).pad(10, 10, 10, 10).row();
        winStart.add(buildButtons()).pad(10, 10, 10, 10);
        //winOptions.add(buildOptWinDebug()).row();
        //winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);

        winStart.setColor(1, 1, 1, 0.8f);
        //winStart.setVisible(false);
        winStart.debug();
        winStart.pack();
        winStart.setPosition(stage.getWidth() / 2 - winStart.getWidth() / 2, 80);

        return winStart;
    }


    @Override
    public void hide() {
        stage.dispose();
        skinLibgdx.dispose();
    }


    @Override
    public void pause() {
    }

    @Override
    public void resume() {
        super.resume();
    }
}
