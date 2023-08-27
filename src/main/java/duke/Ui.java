package duke;

import java.util.ArrayList;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String errorMessage) {
        System.out.println("Error: " + errorMessage);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void print(ArrayList<String> message) {
        for (String s : message) {
            System.out.println(s);
        }
    }
}

