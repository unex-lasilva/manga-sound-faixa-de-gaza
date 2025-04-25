package br.com.mangarosa.core;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private Clip clip;

    public void play(Music music) {
        stop();
        try {
            File file = new File(music.getFilePath());
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

            System.out.println("Tocando: " + music.getName());
            Thread.sleep(Math.min(clip.getMicrosecondLength() / 1000, 10000));
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            System.out.println("Erro ao tocar a música: " + e.getMessage());
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}

