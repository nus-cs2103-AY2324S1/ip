import java.util.Scanner;

public class Duke {
    public static void echo() {
        Scanner myInput = new Scanner(System.in);
        String reply = myInput.nextLine();
        while (!reply.equals("bye")) {
            System.out.println(reply + "... ok...");
            System.out.println("--------------------------------");
            reply = myInput.nextLine();
        }
        System.out.println("Bye..");
        System.out.println("--------------------------------");
    }
    public static void greeting() {
        System.out.println("Hello.. I'm ekuD..");
        System.out.println("I probably won't be much of a help.. But ask me something..");
        System.out.println("--------------------------------");
        echo();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greeting();
    }
}
