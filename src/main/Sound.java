package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30]; // salvam calea fiecarui sunet

    public Sound(){
        soundURL[0] = getClass().getResource("/sound/MainTheme.wav");
        soundURL[1] = getClass().getResource("/sound/Background.wav");
        soundURL[2] = getClass().getResource("/sound/eat.wav");
        soundURL[3] = getClass().getResource("/sound/coin.wav");
        soundURL[4] = getClass().getResource("/sound/door.wav");
        soundURL[5] = getClass().getResource("/sound/TomScream.wav");
        soundURL[6] = getClass().getResource("/sound/hitmonster.wav");
        soundURL[7] = getClass().getResource("/sound/slide.wav");
        soundURL[8] = getClass().getResource("/sound/win.wav");
        soundURL[9] = getClass().getResource("/sound/loading.wav");
        soundURL[10] = getClass().getResource("/sound/explozie.wav");
        soundURL[11] = getClass().getResource("/sound/boss.wav");
        soundURL[12] = getClass().getResource("/sound/gameover.wav");
        soundURL[13] = getClass().getResource("/sound/valve.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e){

        }
    }

    public void play() {
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }



}
