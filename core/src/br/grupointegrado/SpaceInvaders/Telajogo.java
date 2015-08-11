package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sun.rmi.runtime.Log;

/**
 * Created by lucas on 03/08/2015.
 */
public class Telajogo extends Telabase{

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage palco;
    private BitmapFont fonte;
    private Label lbPontuacao;
    private Image jogador;
    private Texture texturajogador;
    private Texture texturajogadorDireita;
    private Texture texturajogadorEsquerda;
    private boolean indodireita;
    private boolean indoesquerda;

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
        initJogador();
    }

    private void initJogador() {
        texturajogador = new Texture("sprites/player.png");
        texturajogadorDireita = new Texture("sprites/player-right.png");
        texturajogadorEsquerda = new Texture("sprites/player-left.png");

        jogador = new Image(texturajogador);
        float x = camera.viewportWidth/2 - jogador.getWidth() / 2;
        float y = 15;
        jogador.setPosition(x, y);
        palco.addActor(jogador);
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
        capturaTeclas();
        atualizarJogador(delta);

        palco.act(delta);
        palco.draw();
    }

    private void atualizarJogador(Float delta) {
        float velocidade =200; //velocidade de movimento do jogador

        if (indodireita){
            if (jogador.getX() < camera.viewportWidth - jogador.getWidth()) {
                float x = jogador.getX() + velocidade * delta;
                float y = jogador.getY();
                jogador.setPosition(x, y);
            }
        }
        else if(indoesquerda){
            if (jogador.getX() > 0){
                float x = jogador.getX() - velocidade * delta;
                float y = jogador.getY();
                jogador.setPosition(x, y);
            }
        }

        if(indodireita){
            //trocar imagem direita
            jogador.setDrawable(new SpriteDrawable(new Sprite(texturajogadorDireita)));
        }else if (indoesquerda){
            //trocar imagem esquerda
            jogador.setDrawable(new SpriteDrawable(new Sprite(texturajogadorEsquerda)));
        }else{
            //trocar imagem do centro
            jogador.setDrawable(new SpriteDrawable(new Sprite(texturajogador)));
        }
    }


    /**
     * verifica se as teclas estão pressionadas
     */
    private void capturaTeclas() {
        indodireita = false;
        indoesquerda = false;

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            indoesquerda = true;
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            indodireita = true;
        }
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
        texturajogador.dispose();
        texturajogadorEsquerda.dispose();
        texturajogadorDireita.dispose();
    }
}
