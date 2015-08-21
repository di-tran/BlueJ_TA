import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SandBox {

    public static void main(String[] args) throws Exception {
        playSound("Chirp.wav");
    }

    public static void playSound(String fileName) throws IOException {
        InputStream in = new FileInputStream(fileName);
        // create an audiostream from the inputstream
        AudioStream audioStream = new AudioStream(in);
        // play the audio clip with the audioplayer class
        AudioPlayer.player.start(audioStream);
    }
}