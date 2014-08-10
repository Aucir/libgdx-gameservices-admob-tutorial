package com.aucir.number.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Disposable;

import org.jrenner.smartfont.SmartFontGenerator;


public class Assets implements Disposable, AssetErrorListener {
    public static final String TAG = Assets.class.getName();
    public static final Assets instance = new Assets();
    private AssetManager assetManager;



    public AssetFonts fonts;

    private Assets () {}

    public void init (AssetManager assetManager) {
        this.assetManager = assetManager;
        assetManager.setErrorListener(this);

        // Load our sprite sheet
//        assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
//        assetManager.finishLoading();
//        Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
//        for (String a : assetManager.getAssetNames())
//            Gdx.app.debug(TAG, "asset: " + a);

//        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);
//
//        // Enable texture filtering for pixel smoothing
//        for (Texture t : atlas.getTextures())
//            t.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        // Create game resource objects
        fonts = new AssetFonts();

    }

    @Override
    public void dispose () {
        assetManager.dispose();
        fonts.defaultSmall.dispose();
        fonts.defaultNormal.dispose();
        fonts.defaultBig.dispose();
    }

    @Override
    public void error (AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset '" + asset.fileName + "'", (Exception)throwable);
    }




    public class AssetFonts {
        public final BitmapFont defaultSmall;
        public final BitmapFont defaultNormal;
        public final BitmapFont defaultBig;

        public final Label.LabelStyle smallStyle;
        public final Label.LabelStyle mediumStyle;
        public final Label.LabelStyle largeStyle;
        public final Window.WindowStyle winStyle;




        public AssetFonts () {

            smallStyle = new Label.LabelStyle();
            mediumStyle = new Label.LabelStyle();
            largeStyle = new Label.LabelStyle();

            winStyle = new Window.WindowStyle();

            SmartFontGenerator fontGen = new SmartFontGenerator();
            FileHandle exoFile = Gdx.files.internal("LiberationMono-Regular.ttf");
            defaultSmall = fontGen.createFont(exoFile, "exo-small", 24);
            defaultNormal = fontGen.createFont(exoFile, "exo-medium", 48);
            defaultBig = fontGen.createFont(exoFile, "exo-large", 64);


            //defaultBig.setScale(.3f);

            smallStyle.font = defaultSmall;
            mediumStyle.font = defaultNormal;
            largeStyle.font = defaultBig;

            winStyle.titleFont = defaultNormal;
            //winStyle.background = setColor(1, 1, 1, 0.8f);

//            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/DroidSerif-Regular.ttf"));
//            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//            parameter.size = 20;
//            parameter.flip = true;
//            defaultNormal = generator.generateFont(parameter); // font size 12 pixels
//            generator.dispose(); // don't forget to dispose to avoid memory leaks!
//
//
//            defaultSmall = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"), true);
//            //defaultNormal = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"), true);
//            defaultBig = new BitmapFont(Gdx.files.internal("fonts/arial-15.fnt"), true);
//
//            defaultSmall.setScale(0.75f);
//            //defaultNormal.setScale(1.0f);
//            defaultBig.setScale(2.0f);

            // Linear texture filtering for a smoother font face
            defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
            defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
            defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }
    }
}
