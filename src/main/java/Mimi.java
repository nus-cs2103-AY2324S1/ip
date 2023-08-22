import java.util.Scanner;
public class Mimi {
    public static void main(String[] args) {

        Storage PreviousCommands = new Storage();

        String LINE = "_________________________________________________\n";

        System.out.println(
                LINE
                + "Hello! I'm Mimi.\n"
                + "What can I do for you?\n"
                + LINE
        );

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            System.out.println(LINE);
            Task task = new Task(command);

            if (task.isExit()) {
                System.out.println(Task.EXIT_MESSAGE + LINE);
                break;
            }

            if (task.isList()) {
                PreviousCommands.listItems();
                System.out.println(LINE);
                continue;
            }

            if (task.isUnmark()) {
                int task_Number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                PreviousCommands.unmark(task_Number);
                System.out.println(LINE);
                continue;
            }

            if (task.isMark()) {
                int task_Number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                PreviousCommands.mark(task_Number);
                System.out.println(LINE);
                continue;
            }


            PreviousCommands.add(task);

            System.out.println(LINE);
        }
    }
}
