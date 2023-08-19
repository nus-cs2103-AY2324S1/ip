import java.util.Scanner;
public class Duke {
    String[] tasks;
    int taskIndex = 0;
    Scanner sc;
    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new String[100];
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
            tasksList += String.format("%d. %s \n", i + 1, tasks[i]);
        }
        this.line(tasksList);
    }

    private void interact() {
        while(true) {
            String reply = sc.nextLine();
            if (isExit(reply)) {
                exit();
                break;
            } else if (isListTasks(reply)) {
                listOutTasks();
            } else {
                this.add(reply);
            }
        }
    }

    private void add(String reply) {
        tasks[taskIndex] = reply;
        this.line(String.format("  added: ", taskIndex + 1) + reply);
        taskIndex += 1;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
