import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________";
        String logo = " ___  __    __   __  ____   ____    ___\n"
                    + "|   | | |   \\ \\ / / |  __| |  __|  |   |\n"
                    + "|   | | |    \\   /   \\ \\    \\ \\    |   |\n"
                    + "|___| | |     | |     \\ \\    \\ \\   |___|\n"
                    + "|   | | |___  | |     _\\ \\   _\\ \\  |   |\n"
                    + "|   | |_____| |_|    |____| |____| |   |\n";
        System.out.println(logo);
        System.out.println(line);
        System.out.println("Hello! I'm Alyssa!");
        System.out.println("What can I do for you?");
        System.out.println(line);
        while (true) {
            String nextInput = sc.next();
            if (nextInput.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                return;
            } else {
                System.out.println(line);
                System.out.println(nextInput);
                System.out.println(line);
            }
        }

    }
}
