import java.util.Scanner;

public class Duke {

    public String greet() {
        String logo = " ____                \n"
                + "|  _ \\  _____  _____ \n"
                + "| |/ / |  _  ||  _  |\n"
                + "| |\\ \\ | |_| || |_| |\n"
                + "|_| \\_\\|_____||_____|\n";
        String hello = "Hello! I am Roo!!\n" + "What can I do for you ah?\n";
        return "Hello from\n" + logo + hello;
    }

    public String bye() {
        return "Ciao! Hope to see you soon yorr!!";
    }

    public String echo(String s) {
        return s + "\n";
    }

    public static void main(String[] args) {
        Duke roo = new Duke();
        System.out.println(roo.greet());

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            if (!input.equals("bye")) {
                System.out.println(roo.echo(input));
                input = sc.next();
            } else {
                sc.close();
                System.out.println(roo.bye());
                break;
            }
        }

    }
}
