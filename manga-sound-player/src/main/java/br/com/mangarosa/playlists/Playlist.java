package br.com.mangarosa.playlists;

public class Playlist {
    private PlaylistNode head;
    private PlaylistNode tail;

    public void adicionarMusica(Music music) {
        PlaylistNode novo = new PlaylistNode(music);
        if (head == null) {
            head = tail = novo;
        } else {
            tail.setNext(novo);
            novo.setPrev(tail);
            tail = novo;
        }
    }

    public void listarMusicas() {
        PlaylistNode atual = head;
        while (atual != null) {
            System.out.println(atual.getMusic());
            atual = atual.getNext();
        }
    }

    public void moverMusica(int de, int para) {
        if (de == para || head == null) return;

        PlaylistNode atual = head;
        int pos = 0;

        // Encontrar o nó a ser movido
        while (atual != null && pos < de) {
            atual = atual.getNext();
            pos++;
        }

        if (atual == null) return;

        // Remover o nó
        PlaylistNode anterior = atual.getPrev();
        PlaylistNode proximo = atual.getNext();

        if (anterior != null) anterior.setNext(proximo);
        else head = proximo;

        if (proximo != null) proximo.setPrev(anterior);
        else tail = anterior;

        // Reposicionar
        PlaylistNode destino = head;
        int i = 0;
        while (destino != null && i < para) {
            destino = destino.getNext();
            i++;
        }

        if (destino == null) {
            // Insere no final
            tail.setNext(atual);
            atual.setPrev(tail);
            atual.setNext(null);
            tail = atual;
        } else {
            PlaylistNode destinoAnterior = destino.getPrev();
            atual.setNext(destino);
            atual.setPrev(destinoAnterior);

            destino.setPrev(atual);
            if (destinoAnterior != null) destinoAnterior.setNext(atual);
            else head = atual;
        }
    }
}
