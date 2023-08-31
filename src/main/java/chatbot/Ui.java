package chatbot;

import chatbot.exceptions.LocalFileException;

import java.util.Scanner;

public class Ui {
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);

    public void showLine() {
        System.out.println(line);
    }

    public void output(String s) {
        System.out.println(s.stripTrailing());
        showLine();
    }

    public void greet() {
        this.showLine();
        System.out.println("\tWelcome back, human!");
        System.out.println("\tI'm your personal chatBot, " + ChatBot.NAME + ".");
        System.out.println("\tWhat can I do for you today?");
        this.showLine();
    }

    public void farewell() {
        output("\tBye. Hope to see you again soon!");
    }

    public void showLoadingError(LocalFileException e) {
        System.out.println(e.toString());
    }

    public String nextCommand() {
        return scanner.nextLine();
    }
}
