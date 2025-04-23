package br.com.mangarosa;

import br.com.mangarosa.playlists.Playlist;

public class MangaSoundApplication {
    public static void main(String[] args) {
        System.out.println("🎵 Welcome to Manga Sound Player 🎵");

        Playlist playlist = new Playlist();

        playlist.adicionarMusica(new Music("", ""));


        System.out.println("▶ Playlist original:");
        playlist.listarMusicas();

        playlist.moverMusica(2, 0);

        System.out.println("\n🔀 Playlist após mover música da posição 2 para a 0:");
        playlist.listarMusicas();
    }
}
