import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static String ANSI_RESET = "\u001B[0m";
    private static String ANSI_PURPLE = "\u001B[35m";

    private void parseCommand(String command) {
        this.speak(command);
    }

    private void speak(String text) {
        String msg = String.format("\n    %s", text);
        System.out.println(msg + "\n");
    }

    private void speak(String[] text) {
        String msg = "\n";
        for (String stub : text) {
            msg += String.format("    %s\n", stub);
        }
        System.out.println(msg);
    }

    public static void main(String[] args) throws IOException {
        Duke chatbot = new Duke();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in)
        );

        chatbot.speak(new String[] {
            "Hi. I'm " + ANSI_PURPLE + "Bryan." + ANSI_RESET,
            "What can I do for you?"
        });

        String command = "";
        while (true) {
            System.out.print("> ");
            command = reader.readLine();
            if (command.equals("bye")) break;
            chatbot.parseCommand(command);
        }

        chatbot.speak("Bye~ Come back soon :)");
    }
}
