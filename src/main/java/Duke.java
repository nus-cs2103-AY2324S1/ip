import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private String[] list;
    private int size = 0;

    public Duke() {
        this.list = new String[100];
    }

    private void start() {
        System.out.println("____________________________________________________________");
        String logo = "  __ _  ___ ___  _   _  _   _  \n" +
                " / _/ \\| o \\_ _|/ \\ | \\| | / \\ \n" +
                "( (( o )   /| || o || \\\\ || o |\n" +
                " \\__\\_/|_|\\\\|_||_n_||_|\\_||_n_|";
        System.out.println("Hello I'm Cortana, Microsoft killed me so now I'm here\n" + logo);
        System.out.println("____________________________________________________________");
    }

    private void exit() {
        System.out.println("Bye");
        System.out.println("____________________________________________________________");
    }

    private void run() {
        start();
        parse_text();
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (Objects.equals(line, "bye")) break;
            System.out.println(line);
            System.out.println("____________________________________________________________");
        }
    }

    private void parse_text() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case "list":
                    if (size == 0) System.out.println("No items are in the list");
                    for (int i = 0; i < size; i++) {
                        String item = String.format("%d. %s", i + 1, list[i]);
                        System.out.println(item);
                    }
                    break;
                case "bye":
                    exit();
                    return;
                default:
                    list[size] = line;
                    size++;
                    System.out.println("Added " + line + " to list");
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
