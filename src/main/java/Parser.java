import java.util.Arrays;

public class Parser {

    public static Command parse(String cmd) {
        String[] words = cmd.split(" ");
        return new Command(words[0], Arrays.copyOfRange(words, 1, words.length));
    }

}