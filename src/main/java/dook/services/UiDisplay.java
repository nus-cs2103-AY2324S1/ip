package dook.services;

import java.util.Scanner;

import dook.command.CommandInfo;

public class UiDisplay {
    private final Scanner sc;

    public UiDisplay() {
        this.sc = new Scanner(System.in);
    }
    public String readCommand() {
        return sc.nextLine();
    }
    public void greetUser() {
        printMessage("Dook here.\nWhat can I do for you?");
    }
    public void printMessage(String msg) {
        printDivider();
        System.out.println(msg);
        printDivider();
    }
    public void printDivider() {
        System.out.println("_______________________________________");
    }
    public void bidFarewell() {
        printMessage("Goodbye.");
    }
    public void displayHelp() {
        StringBuilder result = new StringBuilder();
        result.append("Available commands:\n");
        for (CommandInfo c : CommandInfo.values()) {
            result.append(c.toString()).append("\n");
        }
        printMessage(result.toString());
    }
}
