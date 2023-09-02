package functional;

import java.util.NoSuchElementException;
import java.util.Scanner;


public class Ui {
    String[] currentLine;
    String input;

    public Ui(String input) {
        this.input = input;
        this.currentLine = input.split(" ");
    }

    public Ui() {
    }

    public void showWelcome() {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Husky\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
    }

    public String readCommand() throws functional.DukeException {
        Scanner io = new Scanner(System.in);
        try {
            String s = io.nextLine();
            this.currentLine = s.split(" ");
            this.input = s;
            return s;
        } catch (NoSuchElementException e) {
            throw new functional.DukeException();
        }
    }

    public String showLine() {
        return "____________________________________________________________";
    }

    public String showError(String s) {
        return "Error";
    }

    public String get(int index) {
        return currentLine[index];
    }

    public int length() {
        return currentLine.length;
    }

    public String getInput() {
        return this.input;
    }

    public void showLoadingError() {
        System.out.println("No preexisting file\n" + "creating new file");
    }

    public static void main(String[] args) {

    }
}
