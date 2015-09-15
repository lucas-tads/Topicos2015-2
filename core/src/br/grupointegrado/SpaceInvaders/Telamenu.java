package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;


/**
 * Created by lucas on 14/09/2015.
 */
public class Telamenu extends  Telabase {

    private OrthographicCamera camera;
    private Stage palco;
    private ImageTextButton btniniciar;
    private Label lbtitulo;
    private Label lbpontuacao;

    private BitmapFont fonteTitulo;
    private BitmapFont fontebotoes;

    private Texture texturabotao;
    private Texture texturabotaopessionado;

    public Telamenu(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        palco = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));

        Gdx.input.setInputProcessor(palco);
        initfontes();
        initlabels();
    }

    private void initlabels() {
        Label.LabelStyle estilo = new Label.LabelStyle();
        estilo.font = fonteTitulo;

        lbtitulo = new Label("Space Invaders", estilo);
        palco.addActor(lbtitulo);
    }

    private void initfontes() {
        FreeTypeFontGenerator gerador =
                new FreeTypeFontGenerator(Gdx.files.internal("fonts/roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params =
                new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 48;
        params.color = new Color(.25f , .25f ,.85f, 1);
        params.shadowOffsetX = 2;
        params.shadowOffsetY = 2;
        params.shadowColor = Color.BLACK;

        fonteTitulo = gerador.generateFont(params);
        gerador.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        atulizarlabels();

        palco.act(delta);
        palco.draw();


    }

    private void atulizarlabels() {
        float x = camera.viewportWidth /2 - lbtitulo.getPrefWidth() / 2;
        float y = camera.viewportHeight -100;

        lbtitulo.setPosition(x, y);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        palco.dispose();
        fonteTitulo.dispose();
    }
}
