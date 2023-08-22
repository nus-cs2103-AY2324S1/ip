import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private Task[] list;
    private int size = 0;

    public Duke() {
        this.list = new Task[100];
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

    private void list() {
        if (size == 0) System.out.println("No items are in the list");
        for (int i = 0; i < size; i++) {
            String item = String.format("%d. %s", i + 1, list[i]);
            System.out.println(item);
        }
    }

    private void addToList(String line) {
        list[size] = new Task(line);
        size++;
        System.out.println("Added " + line + " to list");
    }

    private void parseText() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            switch (line) {
                case "list":
                    list();
                    break;
                case "bye":
                    exit();
                    return;
                default:
                    addToList(line);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
