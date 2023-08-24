import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jo {

    static List<Task> taskList = new ArrayList<>();

    public static void markDone(Task task) {
        task.mark();
        System.out.println("> Nice! I've marked this task as done:");
        System.out.println("\t" + task.toString());
    }

    public static void markNotDone(Task task) {
        task.unmark();
        System.out.println("> OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.toString());
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println("> Got it. I've added this task:");
        System.out.println("\t" + task.toString());
        System.out.println(String.format("> Now you have %d tasks in the list.", taskList.size()));
    }

    public static void deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        System.out.println("> Noted. I've removed this task:");
        System.out.println("\t" + removedTask.toString());
        System.out.println(String.format("> Now you have %d tasks in the list.", taskList.size()));
    }

    public static void processInput(String input) throws JoException {
        if (input.trim().isEmpty()) {
            throw new JoException("OOPS!!! The command cannot be empty.");
        } else if (input.equals("list")) {
            System.out.println("> Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                System.out.println("\t" + (i+1) + ". " + t.toString());
            }
        } else if (input.equals("todo")) {
            throw new JoException("OOPS!!! The description of a todo cannot be empty.");
        } else if (input.equals("deadline")) {
            throw new JoException("OOPS!!! The description of a deadline cannot be empty.");
        } else if (input.equals("event")) {
            throw new JoException("OOPS!!! The description of an event cannot be empty.");
        } else if (input.equals("mark")) {
            throw new JoException("OOPS!!! Please specify a task number to mark as done.");
        } else if (input.equals("unmark")) {
            throw new JoException("> OOPS!!! Please specify a task number to mark as not done yet.");
        } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
            int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new JoException("OOPS!!! This task does not exist.");
            } else {
                markDone(taskList.get(taskIndex));
            }
        } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
            int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new JoException("OOPS!!! This task does not exist.");
            } else {
                markNotDone(taskList.get(taskIndex));
            }
        } else if (input.length() >= 4 && input.substring(0, 4).equals("todo")) {
            String description = input.substring(5, input.length());
            addTask(new Task(description));
        } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
            if (!input.contains("/by")) {
                throw new JoException("OOPS!!! Please specify a deadline.");
            } else {
                String[] description = input.substring(9, input.length()).split("/by", 2);
                String deadline = description[1].trim();
                String taskName = description[0].trim();
                addTask(new Deadline(taskName, deadline));
            }
        } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
            if (!input.contains("/from") || !input.contains("/to")) {
                throw new JoException("OOPS!!! Please specify a start AND end date.");
            } else {
                String[] description = input.substring(5, input.length()).split("/from", 2);
                String[] dates = description[1].split("/to", 2);
                String start = dates[0].trim();
                String end = dates[1].trim();
                String taskName = description[0].trim();
                addTask(new Event(taskName, start, end));
            }
        } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
            int taskIndex = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
            if (taskIndex < 0 || taskIndex >= taskList.size()) {
                throw new JoException("OOPS!!! This task does not exist.");
            } else {
                deleteTask(taskIndex);
            }
        } else {
            throw new JoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) throws JoException {

        System.out.println("> Hello! I'm Jo.\n> What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                processInput(input);
            } catch (JoException e) {
                System.out.println("> ☹ " + e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }

        System.out.println("> Bye. Hope to see you again soon!");
        scanner.close();

    }
}
