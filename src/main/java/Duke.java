import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    ArrayList<Task> list = new ArrayList<>();

    String divider = "------------------------------------\n";

    String greet = divider +
            "Hello! I'm Khaleelur!\n" +
            "What can I do for you?\n " +
            divider;

    String exit = divider +
            "Bye. Hope to see you again soon!\n" +
            divider;


    /**
     * Retrieves a formatted string showing the number of tasks left in the list.
     *
     * @return A string indicating the number of tasks remaining.
     */
    public String getTaskLeft() {
        return "Now you have " + list.size() + (list.size() == 1 ? " task" : " tasks") + " in the list.";
    }

    /**
     * Retrieves a formatted string containing all tasks in the list.
     *
     * @return A string listing all tasks in the task list.
     * @throws DukeException If there are no tasks in the list.
     */
    public String getAllToDo() throws DukeException {
        StringBuilder res = new StringBuilder();

        if (list.size() == 0) {
            throw new DukeException("Oh no! No tasks for now! Add more tasks :)\n");
        }

        res.append("Here are the tasks in your list:\n");

        for (int i = 0; i < list.size(); i++) {
            res.append(i + 1).append(".")
                    .append(list.get(i).toString());
            if (i != list.size() - 1) res.append("\n");
        }
        return res.toString();
    }

    /**
     * Retrieves a formatted string indicating the success of marking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task list or input.
     */
    public String markTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("Specify index to mark task!\n");
        }

        if (parts.length > 2 ) {
            throw new DukeException("Enter mark command properly!\n");
        }

        //No task to mark
        if (list.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to mark!\n");
        }

        if (parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);

                //index entered is more than totalTodos
                if (index > list.size() || index <= 0) {
                    throw new DukeException("Enter mark command with positive index lesser than " + (list.size() + 1) + "\n");
                }

                res = list.get(index - 1).setMarked();

            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your markcommand!\n");
            }
        }
        return res;
    }

    /**
     * Retrieves a formatted string indicating the success of unmarking a task.
     *
     * @param input  The input by the user.
     * @return A message confirming the action's success.
     * @throws DukeException If there's an issue with the task list or input.
     */
    public String unmarkTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";


        //No index to mark
        if (parts.length == 1) {
            throw new DukeException("Specify index to unmark task!\n");
        }

        if (parts.length > 2 ) {
            throw new DukeException("Enter unmark command properly!\n");
        }

        //No task to mark
        if (list.size() == 0) {
            throw new DukeException("No tasks! Add more tasks to unmark!\n");
        }

        if (parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);

                //index entered is more than totalTodos
                if (index > list.size() || index <= 0) {
                    throw new DukeException("Enter umark command with positive index lesser than " + (list.size() + 1) + "\n");
                }
                res = list.get(index - 1).setUnmarked();

            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your unmark command!\n");
            }
        }
        return res;
    }

    /**
     * Deletes a task from the task list based on the provided index.
     *
     * @param input The input of the task to be deleted.
     * @return A message indicating the success of the deletion.
     * @throws DukeException If there's an issue with the task list or input.
     */
    public String deleteTask(String input) throws DukeException {

        String[] parts = input.split(" ");

        String res = "";

        //No index to delete
        if (input.equals("delete") && parts.length == 1) {
            throw new DukeException("Specify index to delete task!\n");
        }

        //No task to delete
        if (list.size() == 0) {
            throw new DukeException("No tasks to delete! Add more tasks to delete!\n");
        }

        if ((parts[0].equals("delete")) && parts.length == 2) {
            String sec = parts[1];

            //index is not valid integer
            try {
                int index = Integer.parseInt(sec);
                String removedTask = list.get(index).toString();
                list.remove(index);
                res = "Noted. I've removed this task: \n " + "  " + removedTask + "\n" + getTaskLeft();
            } catch (NumberFormatException e) {
                throw new DukeException("Enter a valid positive integer after your mark/unmark command!\n");
            }
        }
        return res;
    }

    /**
     * Adds a ToDo task to the task list based on the provided input.
     *
     * @param input The user input containing the task description.
     * @return A message indicating the success of adding the ToDo task.
     * @throws DukeException If there's an issue with the input or task description.
     */
    public String todoTask(String input) throws DukeException {
        String task = "";

        String[] parts = input.split(" ");

        for (int i = 1; i < parts.length; i++) {
            task += parts[i] + " ";
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said?! Enter again!\n");
        }

        list.add(new ToDo(task, TaskType.TODO));
        String res = "Got it. I've added this task :\n" + list.get(list.size() - 1).toString() + "\n";
        res += getTaskLeft();

        return res;
    }

    /**
     * Adds a Deadline task to the task list based on the provided input.
     *
     * @param input The user input containing the task description and deadline.
     * @return A message indicating the success of adding the Deadline task.
     * @throws DukeException If there's an issue with the input, task description, or deadline.
     */
    public String deadlineTask(String input) throws DukeException {
        String task = "";
        String by = "";

        String[] parts = input.split(" ");

        boolean found = false;

        for (int i = 1; i < parts.length; i++) {
            if (parts[i].equals("/by")) {
                found = true;
            } else if (found) {
                by += parts[i] + " ";
            } else {
                task += parts[i] + " ";
            }
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! Enter again!\n");
        }

        if (by.isEmpty()) {
            throw new DukeException("deadline task must have /by time\n");
        }

        list.add(new Deadline(task, by, TaskType.DEADLINE));

        String res = "Got it. I've added this task :\n" + list.get(list.size() - 1).toString() + "\n";
        res += getTaskLeft();

        return res;
    }

    /**
     * Adds an Event task to the task list based on the provided input.
     *
     * @param input The user input containing the task description and event timings.
     * @return A message indicating the success of adding the Event task.
     * @throws DukeException If there's an issue with the input, task description, or event timings.
     */
    public String eventTask(String input) throws DukeException {
        String task = "";
        String from = "";
        String to = "";

        String[] parts = input.split(" ");

        boolean startFound = false;
        boolean endFound = false;

        for (int i = 1; i < parts.length; i++) {
            if (parts[i].equals("/from")) {
                startFound = true;
            } else if (parts[i].equals("/to")) {
                startFound = false;
                endFound = true;
            } else if (startFound) {
                from += parts[i] + " ";
            } else if (endFound) {
                to += parts[i] + " ";
            } else {
                task += parts[i] + " ";
            }
        }

        if (task.equals("")) {
            throw new DukeException("No description specified la dei!! How to do work when no work is said!! Enter again!\n");
        }

        if (from.isEmpty() || to.isEmpty()) {
            throw new DukeException("event task must have both /from and /to times\n");
        }

        list.add(new Event(task, from, to, TaskType.EVENT));

        String res = "Got it. I've added this task :\n" + list.get(list.size() - 1).toString() + "\n";
        res += getTaskLeft();

        return res;
    }

    /**
     * Handles user input and performs the relevant actions based on the commands.
     * Continuously listens for user input until the "bye" command is entered.
     */
    public void handleUserInput() {
        Scanner obj = new Scanner(System.in);
        while (true) {
            String userInput = obj.nextLine();
            try {
                if (userInput.equals("bye")) {
                    return;
                }

                if (userInput.startsWith("mark")) {
                    System.out.println(divider + markTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("unmark")) {
                    System.out.println(divider + unmarkTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("delete")) {
                    System.out.println(divider + deleteTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("list")) {
                    System.out.println(divider + getAllToDo() + "\n" + divider);
                } else if (userInput.startsWith("todo")) {
                    System.out.println(divider + todoTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("deadline")) {
                    System.out.println(divider + deadlineTask(userInput) + "\n" + divider);
                } else if (userInput.startsWith("event")) {
                    System.out.println(divider + eventTask(userInput) + "\n" + divider);
                } else {
                    throw new InvalidInputExpression("Invalid input!! Specify commands as list, mark, unmark, or deadline, event and todo followed by the task please la dei!\n");
                }
            } catch (DukeException | InvalidInputExpression e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        Duke duke = new Duke();

        System.out.println(duke.greet);
        duke.handleUserInput();
        System.out.println(duke.exit);
    }
}

