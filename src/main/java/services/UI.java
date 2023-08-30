package services;

import constants.Message;

import java.util.Scanner;

public class UI {
    private final Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void printMessage(String... args) {
        this.showLine();
        for (String arg : args) {
            System.out.printf(arg + "\n");
        }
        this.showLine();
    }

    public void shutDown() {
        this.printMessage(Message.BYE);
        scanner.close();
    }

    public void showWelcome() {
        this.printMessage(Message.WELCOME);
    }

    public void showError(String error) {
        this.printMessage(Message.ERROR + " " + error);
    }

}
