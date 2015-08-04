package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.Color;
import java.awt.image.ColorConvertOp;

/**
 * Created by lucas on 03/08/2015.
 */
public class Telajogo extends Telabase{

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage palco;
    private BitmapFont fonte;
    private Label lbPontuacao;

    /**
     * Construtor padão da tela de jogo,
     * @param game referencia para classe principal
     */
    public Telajogo(MyGdxGame game) {
        super(game);
    }

    /**
     * metodo chamado quando a tela é chamada
     */
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        palco = new Stage(new FillViewport(camera.viewportWidth, camera.viewportHeight, camera));

        initFonte();
        initInformacoes();
    }

    private void initInformacoes() {
        Label.LabelStyle lbestilo = new Label.LabelStyle();
        lbestilo.font = fonte;
        lbestilo.fontColor = com.badlogic.gdx.graphics.Color.WHITE;

        lbPontuacao = new Label("0 pontos", lbestilo);
        palco.addActor(lbPontuacao);
    }

    private void initFonte() {
        fonte= new BitmapFont();
    }

    /**
     * chamado a todo quadro de atulização do jogo
     * @param delta tempo entre um quadro e outro por segundo
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, .15f, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        lbPontuacao.setPosition(10, camera.viewportHeight - 20);
        palco.act(delta);
        palco.draw();
    }

    /**
     * é chamado sempre que há uma alteraçao no tamanho da tela
     * @param width novo valor de largura da tela
     * @param height novo valor de altura da tela
     */
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    /**
     * é chamado sempre que o jogo for minimizado
     */
    @Override
    public void pause() {

    }

    /**
     * é chamado sempre que o jogo voltar para o primeiro plano
     */
    @Override
    public void resume() {

    }

    /**
     * é chamado quando a nossa tela for destruida
     */
    @Override
    public void dispose() {
        batch.dispose();
        palco.dispose();
        fonte.dispose();
    }
}
