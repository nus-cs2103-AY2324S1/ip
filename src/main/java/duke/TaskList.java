package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

    public void showList(String[] input) {
        try {
            if (!(input.length == 1 || input[1].strip().equals(""))) {
                throw new DukeException("    Did you mean list?");
            }
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(String.format("    %d.%s",
                        i + 1, taskList.get(i).toString()));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Marks the indicated task as done.
     *
     * @param input User input.
     */

    public void markTask(String[] input) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = taskList.get(toMark - 1);
            task.markAsDone();
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println(String.format("      %s", task.toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., mark 1");
        }
    }

    /**
     * Marks the indicated task as not done.
     *
     * @param input User input.
     */
    public void unmarkTask(String[] input) {
        try {
            int toMark = Integer.parseInt(input[1]);
            Task task = taskList.get(toMark - 1);
            task.markAsUndone();
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println(String.format("      %s", task.toString()));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., unmark 1");
        }
    }

    /**
     * Deletes the indicated task.
     *
     * @param input User input.
     */
    public void deleteTask(String[] input) {
        try {
            int toDelete = Integer.parseInt(input[1]);
            Task task = taskList.get(toDelete - 1);
            System.out.println("    Noted. I've removed this task:");
            System.out.println(String.format("      %s", task.toString()));
            taskList.remove(toDelete - 1);
            System.out.println("    Number of tasks: " + taskList.size());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Task does not exist.");
        } catch (NumberFormatException e) {
            System.out.println("    Please enter a number e.g., delete 1");
        }
    }

    /**
     * Adds an Event to the task list.
     *
     * @param input User input.
     */
    public void addEvent(String[] input) {
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
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Format: event description /from yyyy-mm-dd /to yyyy-mm-dd");
        } catch (DateTimeParseException e) {
            System.out.println("    Enter valid date yyyy-mm-dd");
        }
    }

    /**
     * Adds a Deadline to the task list.
     *
     * @param input User input.
     */

    public void addDeadline(String[] input) {
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
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    Format: deadline description /by yyyy-mm-dd");
        } catch (DateTimeParseException e) {
            System.out.println("    Enter valid date yyyy-mm-dd");
        }
    }


    /**
     * Adds a ToDo to the task list.
     *
     * @param input User input.
     */
    public void addToDo(String[] input) {
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
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays all tasks in the list containing the keyword.
     *
     * @param input User input.
     */
    public void find(String input[]) {
        try {
            String keyword = input[1];
            if (keyword.strip().equals("")) {
                throw new DukeException("    What do you want me to find?");
            }

            System.out.println("    Here are the matching tasks in your list:");
            int counter = 1;
            for (int i = 0; i < taskList.size(); i++) {
                String s = taskList.get(i).toString();
                if (s.contains(keyword)) {
                    System.out.println(String.format("    %d.%s", counter, s));
                    counter++;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    What do you want me to find?");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
