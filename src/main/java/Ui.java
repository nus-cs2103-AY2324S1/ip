import taskClasses.TaskList;

public class Ui {
    public Ui () {
        String logo = " ██▄   ████▄    ▄     ▄▀  \n" +
                "█  █  █   █     █  ▄▀    \n" +
                "█   █ █   █ ██   █ █ ▀▄  \n" +
                "█  █  ▀████ █ █  █ █   █ \n" +
                "███▀        █  █ █  ███  \n" +
                "            █   ██       \n" +
                "                         ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }

    public void printTaskMarkAsDone(String taskContent) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskContent);
    }

    public void printTaskMarkAsNotDone(String taskContent) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskContent);
    }

    public void showLoadingError() {
        System.out.println("ERROR reading the file, might be corrupted");
    }

    public void printList(TaskList tasks){
        System.out.println("Here are the tasks in your list:");
        tasks.printAllStatusAndDescription();
    }

    public void newDashedLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Deletes the task, decrements the task count, and prints a confirmation message.
     */
    public void deleteTask(String content) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(content);
    }

    public void printTaskCount(int count) {
        System.out.println(String.format("Now you have %s tasks in the list.", count));
    }

    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
