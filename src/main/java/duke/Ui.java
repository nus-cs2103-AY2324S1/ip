package duke;

public class Ui {

    public Ui() {};

    public void textGenerator(String answer) {
        System.out.println(answer);
    }

    public void taskAdder(Task newT, int len) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newT.toString());
        System.out.println("Now you have " + len + " tasks in the list.");
    }

    public void taskInList(int digit, Task newT) {
        System.out.println((digit + 1) + "." + newT.toString());
    }

    public void afterMT(Task newT) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(newT.toString());
    }

    public void afterUMT(Task newT) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(newT.toString());
    }

    public void afterDT(Task newT, int digit) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(newT.toString());
        System.out.println(String.format("Now you have %d tasks in the list.", digit));
    }
}
