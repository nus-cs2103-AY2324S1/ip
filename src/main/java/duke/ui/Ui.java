package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final String GREETING = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again soon!";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return this.sc.nextLine();
    }

    public void talk(String str) {
        String line = "_".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
    }

    public void greet() {
        talk(GREETING);
    }

    public void bye() {
        talk(GOODBYE);
        sc.close();
    }

    public void showLoadingError() {
        talk("I am unable to read your duke.txt data file. Starting with a blank task list...");
    }

    public void list(ArrayList<?> items) {
        int count = items.size();
        if (count == 0) {
            talk("Your list is currently empty.");
        } else {
            String list = "";
            for (int i = 0; i < count; i++) {
                list += "  " + (i + 1) + ". " + items.get(i) + "\n";
            }
            talk(list);
        }
    }

    public void listSearch(ArrayList<?> items) {
        int count = items.size();
        if (count == 0) {
            talk("Your keyword search returned no matching tasks.");
        } else {
            String list = "";
            for (int i = 0; i < count; i++) {
                list += "  " + (i + 1) + ". " + items.get(i) + "\n";
            }
            talk(list);
        }
    }

    public void markItem(String item) {
        talk("Nice! I've marked this task as done:\n  " + item);
    }

    public void unmarkItem(String item) {
        talk("OK, I've marked this task as not done yet:\n  " + item);
    }

    public void deleteItem(String item, int count) {
        talk("Noted. I've removed this task:\n " + item + "\n Now you have " + count + " tasks in your list");
    }

    public void addItem(String item, int count) {
        talk("Got it. I've added this task:\n  " + item + "\n Now you have "
                + count + " tasks in your list.");
    }
}
