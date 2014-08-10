package com.aucir.number.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Created by danielq on 8/6/14.
 */
public class CustomLabel extends com.badlogic.gdx.scenes.scene2d.ui.Label {
    private String text;
    private Color fontColor = Color.WHITE;

    public CustomLabel (CharSequence text, Skin skin) {

        super(text, skin);
        this.text = text.toString();

    }

    public CustomLabel(final CharSequence text, final LabelStyle style) {
        super(text, style);
        this.text = text.toString();
    }

    @Override
    public void act(final float delta) {
        this.setText(text);
        this.setColor(fontColor);
        super.act(delta);
    }

    public void updateText(final String text) {
        this.text = text;
    }

    public void updateColor(Color color)
    {
        fontColor = color;
    }
}
