package br.com.mangarosa.playlists;


import br.com.mangarosa.core.Music;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String name;
    private PlaylistNode start;
    private int size;

    public Playlist(String nome) {
        this.name = nome;
    }

    public void adicionarMusica(Music musica) {
        PlaylistNode nova = new PlaylistNode(musica);
        if (start == null) {
            start = nova;
        } else {
            PlaylistNode atual = start;
            while (atual.getProximo() != null) {
                atual = atual.getProximo();
            }
            atual.setProximo(nova);
            nova.setAnterior(atual);
        }
        size++;
    }

    public List<PlaylistNode> listarMusicas() {
        List<PlaylistNode> lista = new ArrayList<>();
        PlaylistNode atual = start;
        while (atual != null) {
            lista.add(atual);
            atual = atual.getProximo();
        }
        return lista;
    }

    public void moverMusica(int atual, int novaPos) {
        if (atual < 0 || atual >= size || novaPos < 0 || novaPos >= size || atual == novaPos) return;

        List<PlaylistNode> nodes = listarMusicas();
        PlaylistNode removida = nodes.remove(atual);
        nodes.add(novaPos, removida);

        start = null;
        size = 0;
        for (PlaylistNode node : nodes) {
            node.setAnterior(null);
            node.setProximo(null);
            adicionarMusica(node.getMusica());
        }
    }

    public PlaylistNode getInicio() {
        return start;
    }

    public String getNome() {
        return name;
    }
}
