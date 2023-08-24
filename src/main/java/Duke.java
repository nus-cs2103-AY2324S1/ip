import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private Scanner sc = new Scanner(System.in);
    private String[] tasks = new String[100];
    private int countTasks = 0;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void printWithLines(String s) {
        System.out.println(HORIZONTAL_LINE + "\n" + s + "\n" + HORIZONTAL_LINE);
    }

    public void addTask(String s) {
        tasks[countTasks] = s;
        countTasks++;
        printWithLines("added: " + s);
    }

    public void printList() {
        System.out.println(HORIZONTAL_LINE);
        for (int i = 0; i < countTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void run() {
        printWithLines("Hello, I'm Je-O" + "\n"
                + "What can I do for you?");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printWithLines("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printList();
            } else {
                addTask(input);
            }
        }
    }
}
