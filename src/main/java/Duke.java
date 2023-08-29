import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    Scanner userInput = new Scanner(System.in);
    ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();

    }

    private void line() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    private void start() {
        line();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        line();

        while (true) {
            String input = userInput.nextLine();

            try {
                if (input.equals("bye")) {
                    exit();
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("mark")) {
                    testMarkAndDelete(input);

                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    Task currTask = taskList.get(taskIndex);
                    currTask.taskDone(true);
                } else if (input.startsWith("unmark")) {
                    testMarkAndDelete(input);

                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    Task currTask = taskList.get(taskIndex);
                    currTask.taskDone(false);
                } else {
                    if (input.startsWith("todo")) {

                        String description = input.substring(4).trim();
                        // test whether the todo is valid
                        testToDo(description);

                        System.out.println("Got it. I've added this task:");
                        ToDo toDo = new ToDo(description);
                        System.out.println(toDo.toString());
                        taskList.add(toDo);

                    } else if (input.startsWith("event")) {

                        testEvent(input);

                        System.out.println("Got it. I've added this task:");

                        String[] list = input.split("/");
                        String title = list[0].substring(6);
                        String start = list[1].substring(5);
                        String end = list[2].substring(3);

                        Event event = new Event(title, start, end);
                        System.out.println(event.toString());
                        taskList.add(event);

                    } else if (input.startsWith("deadline")) {

                        testDeadline(input);

                        System.out.println("Got it. I've added this task:");

                        String[] list = input.split("/");
                        String title = list[0].substring(9);
                        String time = list[1].substring(3);

                        Deadline deadline = new Deadline(title, time);
                        System.out.println(deadline.toString());
                        taskList.add(deadline);
                    } else if (input.startsWith("delete")) {

                        testMarkAndDelete(input);

                        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskList.get(taskIndex));
                        taskList.remove(taskIndex);

                    } else {
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    line();
                }
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void list() {
        line();
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                Task t = taskList.get(i);
                System.out.println(index + "." + t.toString());
            }
        }
        line();
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    private void testToDo(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private void testMarkAndDelete(String description) throws DukeException {
        String[] words = description.split(" ");
        String index = String.join(" ", Arrays.copyOfRange(words, 1, words.length));

        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new DukeException("Following \"mark\" or \"unmark\" or \"delete\", an integer value is expected. Blanks or" +
                    " non-integer values are invalid.");
        }

        int intIndex = Integer.parseInt(index);

        // Passing the first case means the index is an integer
        if (intIndex < 1) {
            throw new DukeException("The index following \"mark\" or \"unmark\" should start from 1.");
        } else if (intIndex > taskList.size()) {
            throw new DukeException("The index following \"mark\" or \"unmark\" should not exceed the total number of "
                    + "tasks in the list");
        }
    }

    private void testEvent(String description) throws DukeException {
        String[] list = description.split("/");

        if (list.length != 3) {
            throw new DukeException("Invalid input. Fill up all fields. Do not forget the \"/\" symbol before your" +
                    " start and end time.");
        }

        String title = list[0].substring(6);
        String start = list[1].substring(5);
        String end = list[2].substring(3);

        if (start.isEmpty()) {
            throw new DukeException("\"from\" time missing!");
        } else if (end.isEmpty()) {
            throw new DukeException("\"to\" time missing!");
        }
    }

    private void testDeadline(String description) throws DukeException {
        String[] list = description.split("/");

        if (list.length != 2) {
            throw new DukeException("Invalid input. Fill up all fields. Do not forget the \"/\" symbol before your" +
                    " end time.");
        }

        String time = list[1];

        if (!time.startsWith("by")) {
            throw new DukeException("Invalid input. Start with \"by\".");
        } else if (time.substring(2).equals(" ") || time.substring(2).isEmpty()) {
            throw new DukeException("Invalid input. Field Empty.");
        }
    }
}
