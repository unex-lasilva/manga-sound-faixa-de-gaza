package br.com.mangarosa.playlists;

import br.com.mangarosa.core.Music;
import br.com.mangarosa.core.MusicRepository;
import br.com.mangarosa.util.InputHelper;
import br.com.mangarosa.core.AudioPlayer;

import java.util.*;

public class PlaylistManager {
    private final Map<Integer, Playlist> playlists = new HashMap<>();
    private final MusicRepository repository;
    private int counter = 0;

    public PlaylistManager(MusicRepository repositorio) {
        this.repository = repositorio;
    }

    public void createPlaylist(Scanner scanner) {
        System.out.print("Nome da nova playlist: ");
        String nome = scanner.nextLine();
        playlists.put(++counter, new Playlist(nome));
        System.out.println("Playlist criada com sucesso!");
    }

    public void editPlaylist(Scanner scanner) {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }

        listPlaylists();
        System.out.print("Informe o número da playlist para editar: ");
        int id = InputHelper.readInt(scanner);

        Playlist playlist = playlists.get(id);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }

        while (true) {
            System.out.println("\n-- Editando playlist: " + playlist.getNome());
            System.out.println("1. Adicionar música");
            System.out.println("2. Reordenar músicas");
            System.out.println("3. Voltar");
            int op = InputHelper.readInt(scanner);

            switch (op) {
                case 1 -> {
                    System.out.println("Músicas disponíveis:");
                    for (Music m : repository.listMusics()) {
                        System.out.println("- " + m.getName());
                    }
                    System.out.print("Nome da música a adicionar: ");
                    String nome = scanner.nextLine();
                    if (repository.excuteMusic(nome)) {
                        playlist.adicionarMusica(repository.getMusic(nome));
                        System.out.println("Música adicionada!");
                    } else {
                        System.out.println("Música não encontrada.");
                    }
                }
                case 2 -> {
                    List<PlaylistNode> nodes = playlist.listarMusicas();
                    for (int i = 0; i < nodes.size(); i++) {
                        System.out.println(i + " - " + nodes.get(i).getMusica().getName());
                    }
                    System.out.print("Índice da música a mover: ");
                    int atual = InputHelper.readInt(scanner);
                    System.out.print("Nova posição: ");
                    int nova = InputHelper.readInt(scanner);
                    playlist.moverMusica(atual, nova);
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public void executePlaylist(Scanner scanner) {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
            return;
        }

        listPlaylists();
        System.out.print("Número da playlist: ");
        int id = InputHelper.readInt(scanner);
        Playlist playlist = playlists.get(id);

        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }

        PlaylistNode atual = playlist.getInicio();
        AudioPlayer player = new AudioPlayer();

        while (atual != null) {
            player.play(atual.getMusica());
            System.out.println("\n[Música: " + atual.getMusica().getName() + "]");
            System.out.println("1. Próxima | 2. Anterior | 3. Repetir | 4. Parar");

            int escolha = InputHelper.readInt(scanner);
            switch (escolha) {
                case 1 -> atual = atual.getProximo();
                case 2 -> atual = atual.getAnterior();
                case 3 -> {} // repete a mesma
                case 4 -> {
                    player.stop();
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
        System.out.println("Fim da playlist.");
    }

    private void listPlaylists() {
        for (Map.Entry<Integer, Playlist> entry : playlists.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
        }
    }
}
