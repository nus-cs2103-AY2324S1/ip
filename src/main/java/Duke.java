import java.util.Scanner;
public class Duke {
    Task[] tasks;
    int taskIndex = 0;
    Scanner sc;
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new Task[100];
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
        for(int i = 0; i < taskIndex; i++) {
            tasksList += String.format("%d. %s \n", i + 1, tasks[i].toString().replace("  ", ""));
        }
        this.line(tasksList);
    }

    private boolean isMark(String reply) { return reply.startsWith("mark"); }
    private void mark(int index) {
        Task task = tasks[index];
        task.mark();
        this.line(String.format("  Nice! I've marked this task as done:\n    %s", task.toString()));
    }

    private boolean isUnmark(String reply) { return reply.startsWith("unmark"); }
    private void unmark(int index) {
        Task task = tasks[index];
        task.unmark();
        this.line(String.format("  Ok, I've marked this task as not done yet:\n    %s", task.toString()));
    }

    private boolean isToDo(String reply) {
        return reply.startsWith("todo");
    }

    private boolean isDeadline(String reply) {
        return reply.startsWith("deadline");
    }

    private boolean isEvent(String reply) {
        return reply.startsWith("event");
    }
    private void add(String reply) {
        Task task;
        System.out.println(String.format("  Got it. I've added this task:"));
        if (isToDo(reply)) {
            task = new ToDo(reply);
        } else if (isDeadline(reply)) {
            task = new Deadline(reply);
        } else {
            task = new Event(reply);
        }
        tasks[taskIndex] = task;
        taskIndex += 1;
        System.out.println(task.toString());
        this.line(String.format("  Now you have %d task(s) in the list.", taskIndex));
    }

    private void interact() {
        while(true) {
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
            } else if (isToDo(reply) || isEvent(reply) || isDeadline(reply)) {
                add(reply);
            }
            else {
                this.echo(reply);
            }
        }
    }

    private void echo(String reply) {
        this.line(reply);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
