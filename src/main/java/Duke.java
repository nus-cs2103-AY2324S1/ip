import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "_".repeat(60) + "\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String task = "";

        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
        while (true) {
            task = sc.nextLine();
            if (task.equals("bye")) {
                break;
            }
            if (task.equals("list")) {
                Task taskInstance = new Task("");
                taskInstance.printList();
                continue;
            }
            String[] elements = task.split((" "));
            if (elements.length >= 2) {
                String instruction = elements[0];
                if (instruction.equals("mark") || instruction.equals("unmark")) {
                    try {
                        int no = Integer.parseInt(elements[1]);
                        Task taskInstance = new Task("");
                        if (instruction.equals("mark")) {
                            taskInstance.mark(no);
                        }
                        else {
                            taskInstance.unmark(no);
                        }
                        continue;
                    } catch (NumberFormatException e) {
                        System.err.println(Duke.horizontalLine + "You did not enter a valid integer :(\n" + Duke.horizontalLine);
                    }
                }
                int firstSpaceIndex = task.indexOf(' ');
                String actualTask = task.substring(firstSpaceIndex + 1);
                if (instruction.equals("todo")) {
                    Todo todo = new Todo(actualTask);
                    todo.print();
                }
                if (instruction.equals("deadline")) {
                    String[] taskAndDeadline = actualTask.split(("/by"));
                    String onlyTask = taskAndDeadline[0];
                    String by = taskAndDeadline[1];
                    Deadline deadline = new Deadline(onlyTask, by);
                    deadline.print();
                }
                if (instruction.equals("event")) {
                    String[] taskAndToFrom = actualTask.split(("/from"));
                    String onlyTask = taskAndToFrom[0];
                    String[] ToFrom = taskAndToFrom[1].split("/to");
                    String from = ToFrom[0];
                    String to = ToFrom[1];
                    Event event = new Event(onlyTask, from, to);
                    event.print();
                }
            } else {
                System.out.println(Duke.horizontalLine+ "Invalid Instructions :(\n" + Duke.horizontalLine);
                continue;
            }
        }
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
