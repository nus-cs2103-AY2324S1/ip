import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    ArrayList<Task> tasksNN;
    Scanner sc;
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasksNN = new ArrayList<Task>();
    }
    private void line(String text) {
        System.out.println(text);
        System.out.println("------------------------------------------");
    }

    private void greet() {
        System.out.println("------------------------------------------");
        this.line("  Hello! I'm Jokey :) \n  What can I do for you?");
    }

    private boolean isExit(String reply) {
        return reply.startsWith("bye");
    }
    private void exit() {
        this.line("  Bye~ Hope to see you again soon! >w<");
    }

    private boolean isListTasks(String reply) {
        return reply.startsWith("list");
    }

    private void listOutTasks() {
        String tasksList = "";
        for(int i = 0; i < tasksNN.size(); i++) {
            tasksList += String.format("%d. %s \n", i + 1, tasksNN.get(i).toString().replace("  ", ""));
        }
        this.line(tasksList);
    }

    private boolean isMark(String reply) { return reply.startsWith("mark"); }
    private void mark(int index) {
        Task task = tasksNN.get(index);
        task.mark();
        this.line(String.format("  Nice! I've marked this task as done:\n    %s", task.toString()));
    }

    private boolean isUnmark(String reply) { return reply.startsWith("unmark"); }
    private void unmark(int index) {
        Task task = tasksNN.get(index);
        task.unmark();
        this.line(String.format("  Ok, I've marked this task as not done yet:\n    %s", task.toString()));
    }

    private boolean isToDo(String reply) {
        return reply.startsWith("todo");
    }

    private void addToDo(String reply) throws DukeException {
        if (reply.length() == "todo".length()) {
            throw new DukeEmptyToDoException();
        }

        Task task = new ToDo(reply);
        constructTaskMessage(task);
    }

    private boolean isDeadline(String reply) {
        return reply.startsWith("deadline");
    }

    private void addDeadline(String reply) throws DukeException {

        Task task = new Deadline(reply);

        constructTaskMessage(task);
    }

    private boolean isEvent(String reply) {
        return reply.startsWith("event");
    }

    private void addEvent(String reply) throws DukeException {
        Task task = new Event(reply);

        constructTaskMessage(task);
    }

    private boolean isDelete(String reply) {
        return reply.startsWith("delete");
    }

    private void delete(int index) {
        Task removedTask = tasksNN.remove(index);
        System.out.println(String.format("  Noted. I've removed this task:"));
        System.out.println(removedTask.toString());
        this.line(String.format("  Now you have %d task(s) in the list.", tasksNN.size()));
    }

    private void constructTaskMessage(Task task) {
        System.out.println(String.format("  Got it. I've added this task:"));
        tasksNN.add(task);
        System.out.println(task.toString());
        this.line(String.format("  Now you have %d task(s) in the list.", tasksNN.size()));
    }

    private void interact() {
        while(true) {
            try {
                String reply = sc.nextLine();
                if (isExit(reply)) {
                    exit();
                    break;
                } else if (isListTasks(reply)) {
                    listOutTasks();
                } else if (isMark(reply)) {
                    mark(Character.getNumericValue(reply.charAt(5) - 1));
                } else if (isUnmark(reply)) {
                    unmark(Character.getNumericValue(reply.charAt(7) - 1));
                } else if (isToDo(reply)) {
                    addToDo(reply);
                } else if (isDeadline(reply)) {
                    addDeadline(reply);
                } else if (isEvent(reply)) {
                    addEvent(reply);
                } else if (isDelete(reply)) {
                    delete(Character.getNumericValue(reply.charAt(7) - 1));
                }
                else {
                    throw new DukeUnknownCommandException();
                }
            } catch (DukeException e) {
                this.line(e.toString());
            }
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.interact();

    }
}
