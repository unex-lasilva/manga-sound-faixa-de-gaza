package br.com.mangarosa;

import br.com.mangarosa.core.MusicRepository;
import br.com.mangarosa.playlists.Playlist;
import br.com.mangarosa.playlists.PlaylistManager;
import br.com.mangarosa.util.InputHelper;

import java.util.Scanner;

public class MangaSoundApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicRepository repository = new MusicRepository();
        PlaylistManager playlistManager = new PlaylistManager(repository);


        System.out.println("🎵 Welcome to Manga Sound Player 🎵");

        while(true){
            System.out.println("\n==== MangaSound ====");
            System.out.println("1. Adicionar Música ao Repositório");
            System.out.println("2. Criar Lista de Reprodução");
            System.out.println("3. Editar Lista de Reprodução");
            System.out.println("4. Executar Lista de Reprodução");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int option = InputHelper.readInt(scanner);
            switch (option){
                case 1 -> repository.addMusic(scanner);
                case 2 -> playlistManager.createPlaylist(scanner);
                case 3 -> playlistManager.editPlaylist(scanner);
                case 4 -> playlistManager.executePlaylist(scanner);
                case 5 -> {
                    System.out.println("Saindo do manga sound");
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
