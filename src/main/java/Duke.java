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
            tasksList += String.format("%d. %s \n", i + 1, tasks[i].toString());
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
            }
            else {
                this.add(reply);
            }
        }
    }

    private void add(String reply) {
        Task task = new Task(reply);
        tasks[taskIndex] = task;
        this.line(String.format("  added: %s", task.showContent()));
        taskIndex += 1;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
