import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final ArrayList<String> data = new ArrayList<>();

    public void greet() {
        String logo = " ____                \n"
                + "|  _ \\  _____  _____ \n"
                + "| |/ / |  _  ||  _  |\n"
                + "| |\\ \\ | |_| || |_| |\n"
                + "|_| \\_\\|_____||_____|\n";
        String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
        System.out.println("Hello from\n" + logo + hello);
    }

    public void bye() {
        System.out.println("Ciao! Hope to see you soon yorr!!");
    }

    public void add(String s) {
        data.add(s);
        System.out.println("Added \"" + s + "\" lerr :)\n");
    }

    public void list() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println((i + 1) + ". " + data.get(i));
        }
        System.out.println(" ");
    }

    public static void main(String[] args) {
        Duke roo = new Duke();
        roo.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            if (input.equals("list")) {
                roo.list();
                input = sc.nextLine();
            } else if (!input.equals("bye")) {
                roo.add(input);
                input = sc.nextLine();
            } else {
                sc.close();
                roo.bye();
                break;
            }
        }

    }
}
