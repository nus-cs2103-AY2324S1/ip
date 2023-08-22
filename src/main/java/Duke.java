import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private void start() {
        System.out.println("____________________________________________________________\n");
        String logo = "  __ _  ___ ___  _   _  _   _  \n" +
                " / _/ \\| o \\_ _|/ \\ | \\| | / \\ \n" +
                "( (( o )   /| || o || \\\\ || o |\n" +
                " \\__\\_/|_|\\\\|_||_n_||_|\\_||_n_|";
        System.out.println("Hello I'm Cortana, Microsoft killed me so now I'm here\n" + logo);
        System.out.println("____________________________________________________________\n");
    }

    private void exit() {
        System.out.println("Bye\n");
        System.out.println("____________________________________________________________\n");
    }

    private void run() {
        start();
        echo();
        exit();
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (Objects.equals(line, "bye")) break;
            System.out.println(line);
            System.out.println("____________________________________________________________\n");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
