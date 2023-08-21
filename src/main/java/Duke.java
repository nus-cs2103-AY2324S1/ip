import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static char horizontal_line = '\u2500';
    private static String name = "Chewbacca";

    private static List<Task> list = new ArrayList<>();

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        start();

    }

    private static String drawLine() {
        String line = "";
        for (int i = 0; i < 50; i++)
            line += horizontal_line;
        return line;
    }
    private static void start() {

        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, I am " + name + ", son of Attichitcuk");
        System.out.println("How can Chewie help?\n");
        System.out.println(drawLine());
        readInput();

    }

    private static void end() {
        System.out.println(drawLine());
        System.out.println("Chewie is going home now.\nBye bye.\n");
        System.out.println(drawLine());

    }

    private static void echo(String s) {
        System.out.println(drawLine());
        System.out.println(s + "\n");
        System.out.println(drawLine());
    }

    private static void readInput() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        switch (command) {
            case "bye":
                end();
                break;
            case "list":
                readList();
                readInput();
                break;
            default:
                createTask(command);
                readInput();
        }
    }

    private static void createTask(String task) {
        list.add(new Task(task));
        System.out.println(drawLine());
        System.out.println("added: " + task + "\n");
        System.out.println(drawLine());
    }

    private static void readList() {
        System.out.println(drawLine());
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            Task task = list.get(i);
            System.out.println(index + ". " + task.taskName());
        }
        System.out.println("\n" + drawLine());
    }
}
