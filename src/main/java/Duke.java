import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private static int index = 0;
    private static Task[] tasks = new Task[100];

    public static void listTasks() {
        for (int i = 0; i < index; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks[i].toString());
        }
    }

    public static void addTask(String input) {
        tasks[index++] = new Task(input);
        System.out.println("\tadded: " + input);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String LOGO = "   /\\_/\\  \n" +
                "  ( o.o ) \n" +
                "   > ^ <\n";
        String GREETING = "Hello(@.@), I'm NiHao \nWhat can I do for you?";
        String EXIT = "Bye(T.T), Hope to see you again soon!";


        System.out.println(LOGO + GREETING);

        while (true) {
            String input = sc.nextLine();
            if (Objects.equals(input, "exit")) {
                break;
            } else if (Objects.equals(input, "list")) {
                listTasks();
            } else if (Objects.equals(input.split(" ")[0], "mark")) {
                int i = Integer.parseInt(input.split(" ")[1]);
                tasks[i - 1].mark();
                System.out.println("\tNice! I've marked this task as done: \n" +
                        "\t\t" + tasks[i - 1].toString());
            } else if (Objects.equals(input.split(" ")[0], "unmark")){
                int i = Integer.parseInt(input.split(" ")[1]);
                tasks[i - 1].unmark();
                System.out.println("\tOK, I've marked this task as not done yet: \n" +
                        "\t\t" + tasks[i - 1].toString());
            } else {
                addTask(input);
            }
        }
        System.out.println(EXIT);
    }
}
