package com.example.demo7;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

// có nhiệm vụ phát ra âm thanh từ 1 file âm thanh đã chuẩn bị sẵn.
public class Sound {
    private Clip clip;

    public Sound(String filename) {
        try {
            File soundFile = new File(filename);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip.isRunning()) {
            clip.stop();
        }
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        clip.stop();
    }
}
