import java.util.Scanner;

public class Duke {
    private static final String NAME = "404";
    private static final String SPACE = "     ";
    private static final int STORE_SIZE = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[STORE_SIZE];
        int task_pointer = 0;
        printLine();
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                        SPACE, NAME, SPACE);
        System.out.println(greeting);
        printLine();
        System.out.println();
        String text = sc.nextLine();
        String[] split = text.split(" ");
        String command = split[0];
        while (!command.equals("bye")) {
            printLine();
            switch(command) {
                case "list":
                    if (task_pointer == 0) {
                        System.out.printf("%sSorry, there is nothing to list!%n", SPACE);
                        break;
                    } else {
                        System.out.printf("%sHere are the tasks in your list:%n", SPACE);
                    }
                    for (int i = 0; i < task_pointer; i++) {
                        if (tasks[i] == null) {
                            break;
                        } else {
                            String out = String.format("%s%d.%s", SPACE, i + 1, tasks[i]);
                            System.out.println(out);
                        }
                    }
                    break;

                case "mark":
                    int indexMark = Integer.valueOf(split[1]) - 1;
                    System.out.printf("%sNice! I've marked this task as done:%n%s  %s%n",
                            SPACE, SPACE, tasks[indexMark].markAsDone());
                    break;

                case "unmark":
                    int indexUnmark = Integer.valueOf(split[1]) - 1;
                    System.out.printf("%sOK, I've marked this task as not done yet:%n%s  %s%n",
                            SPACE, SPACE, tasks[indexUnmark].markAsNotDone());
                    break;

                default:
                    System.out.printf("%sadded: %s%n", SPACE, text);
                    tasks[task_pointer] = new Task(text);
                    task_pointer++;
            }
            printLine();
            System.out.println();
            text = sc.nextLine();
            split = text.split(" ");
            command = split[0];
        }
        printLine();
        System.out.printf("%sBye. Hope to see you again soon!%n", SPACE);
        printLine();
        sc.close();
    }

    private static void printLine() {
        String line =  "    ____________________________________________________________";
        System.out.println(line);
    }
}
