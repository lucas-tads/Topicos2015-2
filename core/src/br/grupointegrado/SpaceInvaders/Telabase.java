package br.grupointegrado.SpaceInvaders;

import com.badlogic.gdx.Screen;

import br.grupointegrado.SpaceInvaders.MyGdxGame;

/**
 * Created by lucas on 03/08/2015.
 */
public abstract class Telabase implements Screen {

    protected MyGdxGame game;

    public Telabase(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void hide() {
        dispose();
    }
}
