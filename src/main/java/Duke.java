import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main (String[] args) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Ari.");
        System.out.println("\tWhat can I do for you?");
        System.out.println(horizontalLine);
        echo();
        System.out.println(horizontalLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    /**
     * Runs the Ari chatbot.
     * or is not one of 'events', 'todos', or 'deadline'
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void echo() throws DukeException {
        ArrayList<Task> lst = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        String commands = input.nextLine();

        while (true) {
            if (commands.equals("bye")) {
                return;
            } else if (commands.equals("list")) {
                showList(lst);
            } else if (commands.indexOf("mark") == 0) {
                mark(lst, commands);
            } else if (commands.indexOf("unmark") == 0) {
                unmark(lst, commands);
            } else if (commands.indexOf("deadline") == 0) {
                addDeadline(lst, commands);
            } else if (commands.indexOf("todo") == 0) {
                addToDo(lst, commands);
            } else if (commands.indexOf("event") == 0) {
                addEvent(lst, commands);
            } else if (commands.indexOf("delete") == 0) {
                deleteTask(lst, commands);
            } else {
                throw new DukeException("\t  OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            commands = input.nextLine();
        }
    }

    /**
     * Prints out all the tasks stored in the ArrayList.
     * @param lst an ArrayList of tasks storing all the tasks for the user
     */
    public static void showList(ArrayList<Task> lst) {
        String horizontalLine = "\t_______________________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("\tHere are the task in your list:");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + lst.get(i).toString());
        }
        System.out.println(horizontalLine);
    }

    /**
     * Deleted a specified task in the commands
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command indicating which task to delete
     */
    public static void deleteTask(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t_______________________________________________________________________";
        int index = java.lang.Integer.parseInt(commands.substring("delete".length() + 1)) - 1;
        Task removedTask = lst.remove(index);
        System.out.println(horizontalLine);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + removedTask.toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
    }

    /**
     * Adds a task with a deadline to the ArrayList of tasks
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command with descriptions about a deadline and a date
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void addDeadline(ArrayList<Task> lst, String commands) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";

        int byIndex = commands.indexOf("/by");
        int commandIndex = commands.indexOf(" ");

        if (byIndex == -1 || commandIndex == -1) {
            throw new DukeException("\t  OOPS!!! The description of a deadline and time cannot be empty.");
        }

        String by = commands.substring(byIndex + "/by".length() + 1);
        String command = commands.substring(commandIndex + 1, byIndex - 1);

        lst.add(new Deadline(command, by));
        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Adds a to-do to the ArrayList of tasks
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command with descriptions about a to-do
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void addToDo(ArrayList<Task> lst, String commands) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";

        int commandIndex = commands.indexOf(" ");
        String command = commands.substring(commandIndex + 1);

        if (commandIndex == -1 || command.equals("")) {
            throw new DukeException("\t  OOPS!!! The description of a todo cannot be empty.");
        }
        lst.add(new ToDo(command));
        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    /**
     * Adds an event to the ArrayList of tasks
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command with descriptions about an event and its duration
     * @throws DukeException throws DukeException if input is not formatted correctly
     */
    public static void addEvent(ArrayList<Task> lst, String commands) throws DukeException {
        String horizontalLine = "\t_______________________________________________________________________";

        int fromIndex = commands.indexOf("/from");
        int toIndex = commands.indexOf("/to");
        int commandIndex = commands.indexOf(" ");

        if (fromIndex == -1 || toIndex == -1 || commandIndex == -1) {
            throw new DukeException("\t  OOPS!!! The description of an event and time cannot be empty.");
        }

        String from = commands.substring(fromIndex + "/from".length() + 1, toIndex - 1);
        String to = commands.substring(toIndex + "/to".length() + 1);
        String command = commands.substring(commandIndex + 1, fromIndex - 1);

        lst.add(new Event(command, from, to));
        System.out.println(horizontalLine);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + lst.get(lst.size() - 1).toString());
        System.out.println("\tNow you have " + lst.size() + " tasks in the list.");
        System.out.println(horizontalLine);

    }

    /**
     * Marks a specified task as done
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command indicating which task to mark as done
     */
    public static void mark(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t_______________________________________________________________________";

        int index = java.lang.Integer.parseInt(commands.substring("mark".length() + 1)) - 1;
        lst.get(index).changeStatus();
        System.out.println(horizontalLine);
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + lst.get(index).toString());
        System.out.println(horizontalLine);
    }

    /**
     * Unmarks a specified task from 'done'
     * @param lst an ArrayList of tasks storing all the tasks for the user
     * @param commands a command indicating which task to unmark
     */
    public static void unmark(ArrayList<Task> lst, String commands) {
        String horizontalLine = "\t_______________________________________________________________________";

        int index = java.lang.Integer.parseInt(commands.substring("unmark".length() + 1)) - 1;
        lst.get(index).changeStatus();
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t  " + lst.get(index).toString());
        System.out.println(horizontalLine);
    }
}
