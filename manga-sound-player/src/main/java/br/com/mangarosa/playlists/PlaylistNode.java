package br.com.mangarosa.playlists;


import br.com.mangarosa.core.Music;

public class PlaylistNode {
    private Music musica;
    private PlaylistNode anterior;
    private PlaylistNode proximo;

    public PlaylistNode(Music musica) {
        this.musica = musica;
    }

    public Music getMusica() {
        return musica;
    }

    public PlaylistNode getAnterior() {
        return anterior;
    }

    public void setAnterior(PlaylistNode anterior) {
        this.anterior = anterior;
    }

    public PlaylistNode getProximo() {
        return proximo;
    }

    public void setProximo(PlaylistNode proximo) {
        this.proximo = proximo;
    }
}
