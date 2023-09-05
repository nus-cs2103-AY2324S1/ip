package chad.io;

import java.util.Scanner;


public class ConsoleIO implements IOHandler{

    private Scanner sc;

    public ConsoleIO() {
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm Chad!\nWhat do you want?");
        System.out.println("______________________________________");
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public String readInput() {
        return sc.nextLine();
    }

    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }
}
