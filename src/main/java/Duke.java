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

    private CommandType parseText(String line) {
        String command = line.split(" ")[0];
        switch(command) {
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "bye":
                return CommandType.BYE;
            default:
                return CommandType.ADD;
        }
    }

    private void run() {
        start();
        performCommands();
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

    private void markDone(int index) {
        list[index].markDone();
        System.out.println("This task is marked as done");
        System.out.println(list[index]);
    }

    private void unmarkDone(int index) {
        list[index].unmarkDone();
        System.out.println("This task is marked as not done");
        System.out.println(list[index]);
    }

    private void addToList(String line) {
        list[size] = new Task(line);
        size++;
        System.out.println("Added " + line + " to list");
    }

    private void performCommands() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            CommandType command = parseText(line);
            String options = line.substring(line.indexOf(' ') + 1);
            switch (command) {
                case LIST:
                    list();
                    break;
                case MARK:
                    markDone(Integer.parseInt(options) - 1);
                    break;
                case UNMARK:
                    unmarkDone(Integer.parseInt(options) - 1);
                    break;
                case BYE:
                    exit();
                    return;
                case ADD:
                    addToList(options);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
