public class Ui {

    public void greetUser() {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");
    }

    public void bidGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void unknownInput() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void addTaskMessage(Task task, int taskcount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskcount + " task(s) in the list.");
    }

    public void deleteTaskMessage(Task task, int taskcount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskcount + " tasks in the list.");
    }

    public void maskAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void unmarkAsDoneMessage(Task task) {

    }
}
