import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What's your name?");

        // Take in inputs from keyboard and show them on screen
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        // Bot reply
        System.out.println("Hi" + answer);
    }
}
