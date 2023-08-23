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

            if (task.isDelete()) {
                int task_Number = Integer.parseInt(command.replaceAll("[^0-9]", ""));

                PreviousCommands.delete(task_Number);
                System.out.println(LINE);
                continue;
            }

            try {
                if (task.isCompleteCommand()) {
                    int i = task.get().indexOf(' ');
                    String actual_task = task.get().substring(0, i);

                    switch (actual_task) {
                        case "todo":
                            Todo todo = new Todo(command);
                            PreviousCommands.add(todo);
                            System.out.println(LINE);
                            break;
                        case "deadline":
                            Deadline deadline = new Deadline(command);
                            PreviousCommands.add(deadline);
                            System.out.println(LINE);
                            break;
                        case "event":
                            Event event = new Event(command);
                            PreviousCommands.add(event);
                            System.out.println(LINE);
                            break;
                    }
                } else {
                    if (task.isValidCommand()) {
                        Task temp = new Task("");
                        switch(command) {
                            case "todo":
                                temp = new Todo(command);
                                break;
                            case "deadline":
                                temp = new Deadline(command);
                                break;
                            case "event":
                                temp = new Event(command);
                                break;
                        }
                        System.out.println(temp.missingDescription() + "\n" + LINE);
                    }
                }
            } catch (InvalidTask e) {
                System.out.println("☹ OOPS!!! I'm sorry but I don't know what that means :-(\n" + LINE);
            }


        }
    }
}
