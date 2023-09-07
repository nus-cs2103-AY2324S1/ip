package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Used to add, delete, mark, show and find tasks in the list.
 *
 * @author Teo Kai Sheng
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor to create a TaskList.
     *
     * @param taskList The previously saved task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays the current list to the user.
     *
     * @param input User input.
     */
    public String showList(String[] input) {
        try {
            if (!(input.length == 1 || input[1].strip().equals(""))) {
                throw new DukeException("    Did you mean list?");
            }
            System.out.println("    Here are the tasks in your list:");
            String output = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(String.format("    %d.%s", i + 1, taskList.get(i).toString()));
                output = output + String.format("    %d.%s", i + 1, taskList.get(i).toString()) + "\n";
            }
            return output;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return "Did you mean list?";
        }
    }

    /**
     * Marks the indicated task as done.
     *
     * @param input User input.
     */
    public String markTask(String[] input) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = taskList.get(toMark - 1);
            task.markAsDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println(String.format("      %s", task.toString()));
            return "Nice! I've marked this task as done:\n" + task.toString();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
            return "Task does not exist.";
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., mark 1");
            return "Please enter a number e.g., mark 1";
        }
    }

    /**
     * Marks the indicated task as not done.
     *
     * @param input User input.
     */
    public String unmarkTask(String[] input) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = taskList.get(toMark - 1);
            task.markAsUndone();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println(String.format("      %s", task.toString()));
            return "OK, I've marked this task as not done yet:\n" + task.toString();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
            return "Task does not exist.";
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., unmark 1");
            return "Please enter a number e.g., unmark 1";
        }
    }

    /**
     * Deletes the indicated task.
     *
     * @param input User input.
     */
    public String deleteTask(String[] input) {
        try {
            int toDelete = Integer.parseInt(input[1]);
            Task task = taskList.get(toDelete - 1);
            String output = task.toString();
            System.out.println("    Noted. I've removed this task:");
            System.out.println(String.format("      %s", output));
            taskList.remove(toDelete - 1);
            System.out.println("    Number of tasks: " + taskList.size());
            return "Noted. I've removed this task:\n" + output + "\n" + "Number of tasks: " + taskList.size();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
            return "Task does not exist.";
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., delete 1");
            return "Please enter a number e.g., delete 1";
        }
    }

    /**
     * Adds an Event to the task list.
     *
     * @param input User input.
     */
    public String addEvent(String[] input) {
        try {
            String[] s1 = input[1].split("/from", 2);
            String[] s2 = s1[1].split("/to", 2);
            String desc = s1[0].strip();
            String from = s2[0].strip();
            String to = s2[1].strip();
            if (desc.equals("") || from.equals("") || to.equals("")) {
                throw new DukeException("    Format: event description /from yyyy-mm-dd /to yyyy-mm-dd");
            }
            Event e = new Event(desc, LocalDate.parse(from), LocalDate.parse(to));
            taskList.add(e);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + e.toString());
            System.out.println("    Number of tasks: " + taskList.size());
            return "Got it. I've added this task:\n" + e.toString() + "\n" + "Number of tasks: " + taskList.size();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Format: event description /from yyyy-mm-dd /to yyyy-mm-dd");
            return "Format: event description /from yyyy-mm-dd /to yyyy-mm-dd";
        } catch (DateTimeParseException e) {
            System.out.println("    Enter valid date yyyy-mm-dd");
            return "Enter valid date yyyy-mm-dd";
        }
    }

    /**
     * Adds a Deadline to the task list.
     *
     * @param input User input.
     */
    public String addDeadline(String[] input) {
        try {
            String[] s = input[1].split("/by", 2);
            String desc = s[0].strip();
            String deadline = s[1].strip();
            if (desc.equals("") || deadline.equals("")) {
                throw new DukeException("    Format: deadline description /by yyyy-mm-dd");
            }
            Deadline d = new Deadline(desc, LocalDate.parse(deadline));
            taskList.add(d);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + d.toString());
            System.out.println("    Number of tasks: " + taskList.size());
            return "Got it. I've added this task:\n" + d.toString() + "\n" + "Number of tasks: " + taskList.size();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Format: deadline description /by yyyy-mm-dd");
            return "Format: deadline description /by yyyy-mm-dd";
        } catch (DateTimeParseException e) {
            System.out.println("    Enter valid date yyyy-mm-dd");
            return "Enter valid date yyyy-mm-dd";
        }
    }

    /**
     * Adds a ToDo to the task list.
     *
     * @param input User input.
     */
    public String addToDo(String[] input) {
        try {
            String desc = input[1];
            if (desc.strip().equals("")) {
                throw new DukeException("    ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo t = new ToDo(desc);
            taskList.add(t);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + t.toString());
            System.out.println("    Number of tasks: " + taskList.size());
            return "Got it. I've added this task:\n" + t.toString() + "\n" + "Number of tasks: " + taskList.size();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
            return "OOPS!!! The description of a todo cannot be empty.";
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return "OOPS!!! The description of a todo cannot be empty.";
        }
    }

    /**
     * Displays all tasks in the list containing the keyword.
     *
     * @param input User input.
     */
    public String findTasks(String[] input) {
        try {
            String keyword = input[1];
            if (keyword.strip().equals("")) {
                throw new DukeException("    What do you want me to find?");
            }
            String output = "Here are the matching tasks in your list:\n";
            System.out.println("    Here are the matching tasks in your list:");
            int counter = 1;
            for (int i = 0; i < taskList.size(); i++) {
                String s = taskList.get(i).toString();
                if (s.contains(keyword)) {
                    System.out.println(String.format("    %d.%s", counter, s));
                    output = output + String.format("    %d.%s", counter, s) + "\n";
                    counter++;
                }
            }
            return output;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    What do you want me to find?");
            return "What do you want me to find?";
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return "What do you want me to find?";
        }
    }
}
