package com.aucir.number.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class GamePreferences {
    public static final String TAG = GamePreferences.class.getName();

    public static final GamePreferences instance = new GamePreferences();


    public static int EASY_MODE = 1;
    public static int HARD_MODE = 2;

    private int gameMode = EASY_MODE;


    private Preferences prefs;

    private int score;


    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getScore() {
        return score;
    }

    public void addScore()
    {
        int score = this.score;
        score++;
        setScore(score);
    }

    public void setScore(int score) {

        if (gameMode == EASY_MODE)
        {
            int scoreEasy = prefs.getInteger("scoreEasy", 0);

            if (scoreEasy < score)
            {
                prefs.putInteger("scoreEasy", score);
                prefs.flush();
            }
        }
        else
        {
            int scoreHard = prefs.getInteger("scoreHard", 0);
            if (scoreHard < score)
            {
                prefs.putInteger("scoreHard", score);
                prefs.flush();
            }
        }
        this.score = score;
    }

    public int getBestScore()
    {
        if (gameMode == EASY_MODE) {
            return prefs.getInteger("scoreEasy", 0);
        }
        else
        {
            return prefs.getInteger("scoreHard", 0);

        }
    }


    public long getDeltaTime()
    {
        if (gameMode == EASY_MODE) {
            return 2000;
        }
        else
        {
            return 1500;

        }
    }

    public int getTryTime()
    {
        if (gameMode == EASY_MODE) {
            return 5;
        }
        else
        {
            return 3;
        }
    }

    public String getLeaderboardid()
    {
        if (gameMode == EASY_MODE) {
            return "CgkIobeF2I8XEAIQAA";
        }
        else
        {
            return "CgkIobeF2I8XEAIQAg";
        }
    }


    private GamePreferences () {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
    }
    

}
