package br.com.mangarosa.core;

import br.com.mangarosa.util.InputHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class MusicRepository {
    private final Map<String, Music> musics = new HashMap<>();
    private final String repositoryPath = "repository";

    public MusicRepository() {
        File pasta = new File(repositoryPath);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }

    public void addMusic(Scanner scanner) {
        System.out.print("Informe o caminho do arquivo .wav: ");
        String path = scanner.nextLine().trim();
        File file = new File(path);

        if (!file.exists() || !file.getName().endsWith(".wav")) {
            System.out.println("Arquivo inválido.");
            return;
        }

        Path destino = Paths.get(repositoryPath, file.getName());
        try {
            Files.copy(file.toPath(), destino, StandardCopyOption.REPLACE_EXISTING);
            Music novaMusica = new Music(file.getName(), destino.toString());
            musics.put(novaMusica.getName(), novaMusica);
            System.out.println("Música adicionada com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao copiar o arquivo: " + e.getMessage());
        }
    }

    public Collection<Music> listMusics() {
        return musics.values();
    }

    public Music getMusic(String nome) {
        return musics.get(nome);
    }

    public boolean excuteMusic(String nome) {
        return musics.containsKey(nome);
    }
}
