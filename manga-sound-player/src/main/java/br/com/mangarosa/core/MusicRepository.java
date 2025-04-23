package br.com.mangarosa.core;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class MusicRepository {
    private static final String REPOSITORY_FOLDER = "repositorio";
    private List<Music> musicList;

    public MusicRepository() {
        musicList = new ArrayList<>();
        File folder = new File(REPOSITORY_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        loadMusicList();
    }

    private void loadMusicList() {
        musicList.clear();
        File folder = new File(REPOSITORY_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".wav"));
        if (files != null) {
            for (File file : files) {
                musicList.add(new Music(file.getName(), file.getAbsolutePath()));
            }
        }
    }

    public boolean addMusic(String sourcePath) {
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists() || !sourceFile.getName().toLowerCase().endsWith(".wav")) {
            System.out.println("Arquivo inválido ou inexistente.");
            return false;
        }

        File destFile = new File(REPOSITORY_FOLDER, sourceFile.getName());
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            musicList.add(new Music(destFile.getName(), destFile.getAbsolutePath()));
            System.out.println("Música adicionada com sucesso.");
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao copiar o arquivo: " + e.getMessage());
            return false;
        }
    }

    public void listMusics() {
        if (musicList.isEmpty()) {
            System.out.println("Nenhuma música disponível.");
            return;
        }
        for (int i = 0; i < musicList.size(); i++) {
            System.out.println((i + 1) + ". " + musicList.get(i));
        }
    }

    public List<Music> getMusicList() {
        return musicList;
    }
}
