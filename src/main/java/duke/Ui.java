package duke;

public class Ui {
    public Ui() {};

    public void textGenerator(String answer) {
        System.out.println(answer);
    }

    public void addTaskText(Task newT, int len) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newT.toString());
        System.out.println("Now you have " + len + " tasks in the list.");
    }

    public void displayTaskInList(int digit, Task newT) {
        System.out.println((digit + 1) + "." + newT.toString());
    }

    public void markTaskText(Task newT) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(newT.toString());
    }

    public void unmarkTaskText(Task newT) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(newT.toString());
    }

    public void deleteTaskText(Task newT, int digit) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(newT.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", digit));
    }
}
