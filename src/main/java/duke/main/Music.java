package duke.main;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Music {
    private static final Media media = new Media(new File("src/main/resources/audio/test2.mp3").toURI().toString());
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);

    public static void playMusic() {
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }

    public static void stopMusic() {
        mediaPlayer.stop();
    }

    public static void pauseMusic() {
        mediaPlayer.pause();
    }
}
