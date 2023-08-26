import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "_".repeat(35) + "\n";
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
                if (elements[0].equals("mark") || elements[0].equals("unmark")) {
                    try {
                        int no = Integer.parseInt(elements[1]);
                        Task taskInstance = new Task("");
                        if (elements[0].equals("mark")) {
                            taskInstance.mark(no);
                        }
                        else {
                            taskInstance.unmark(no);
                        }
                        continue;
                    } catch (NumberFormatException e) {
                        System.err.println("You did not enter a valid integer :(");
                    }
                }
                if (elements[0].equals("todo")) {
                    int firstSpaceIndex = task.indexOf(' ');
                    String actualTask = task.substring(firstSpaceIndex + 1);
                    Todo todo = new Todo(actualTask);
                    todo.print();
                }
                if (elements[0].equals("deadline")) {
                    int firstSpaceIndex = task.indexOf(' ');
                    String actualTask = task.substring(firstSpaceIndex + 1);
                    String[] taskAndDeadline = actualTask.split(("/by"));
                    String onlyTask = taskAndDeadline[0];
                    String by = taskAndDeadline[1];
                    Deadline deadline = new Deadline(onlyTask, by);
                    deadline.print();
                }
                if (elements[0].equals("event")) {
                    int firstSpaceIndex = task.indexOf(' ');
                    String actualTask = task.substring(firstSpaceIndex + 1);
                    String[] taskAndToFrom = actualTask.split(("/from"));
                    String onlyTask = taskAndToFrom[0];
                    String[] ToFrom = taskAndToFrom[1].split("/to");
                    String from = ToFrom[0];
                    String to = ToFrom[1];
                    Event event = new Event(onlyTask, from, to);
                    event.print();
                }
            } else {
                System.out.println("Invalid Instructions :(\n" + Duke.horizontalLine);
                continue;
            }
        }
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
