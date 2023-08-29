package seedu.duke;

import java.util.Scanner;

public class Ui {
    public static final String i4 = "    ";
    public static final String i5 = Ui.i4 + " ";
    public static final String i7 = Ui.i5 + "  ";
    public static final String line = Ui.i4 + "——————————————————————————————————————————————————————————————————";

    private String name;
    private Scanner scanner;
    private String input;
    private Parser parser;

    public Ui(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
        this.parser = new Parser();
    }

    public static void line() {
        System.out.println(Ui.line);
    }

    public void greet() {
        Ui.line();
        System.out.println(Ui.i5 + "Hello! I'm " + this.name);
        System.out.println(Ui.i5 + "What can I do for you?");
        Ui.line();
    }

    public void exit() {
        System.out.println(Ui.i5 + "Bye. Hope to see you again soon!");
    }
    public void bye() {
        this.scanner.close();
        this.exit();
        Ui.line();
    }

    public Commands getNextUserInput() {
        this.input = this.scanner.nextLine();
        return this.parser.parse(this.input);
    }

    public int mark() throws DukeException {
        return this.parser.mark(this.input);
    }

    public int unmark() throws DukeException {
        return this.parser.unmark(this.input);
    }

    public int delete() throws DukeException {
        return this.parser.delete(this.input);
    }

    public Task todo() throws DukeException {
        return this.parser.todo(this.input);
    }

    public Task deadline() throws DukeException {
        return this.parser.deadline(this.input);
    }

    public Task event() throws DukeException {
        return this.parser.event(this.input);
    }
}
