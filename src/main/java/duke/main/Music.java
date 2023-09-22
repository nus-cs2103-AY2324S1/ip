package duke.main;

import java.io.File;
import java.util.Objects;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/** Setup Music to play */
public class Music {
    private static final Media media = new Media(Objects.requireNonNull(Music.class.getClassLoader()
            .getResource("audio/test2.mp3")).toString());
    private static final MediaPlayer mediaPlayer = new MediaPlayer(media);

    /**
     * Play music.
     */
    public static void playMusic() {
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
    }

    /**
     * Stop music.
     */
    public static void stopMusic() {
        mediaPlayer.stop();
    }

    /**
     * Pause music.
     */
    public static void pauseMusic() {
        mediaPlayer.pause();
    }
}
