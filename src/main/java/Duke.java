import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        String lnspace = "____________________________________________________________";
        // Print greeting
        String greeting = lnspace + "\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + lnspace + "\n";

        System.out.println(greeting);

        // initialise scanner to detect user input
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        // initialise array to store user input
        String[] arr = new String[100];
        int counter = 0;

        // while loop to continuously take in inputs until user types bye
        while (!userInput.equals("bye")) {
            // if user asks for list
            if (userInput.equals("list")) {
                System.out.println(lnspace);
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. %s\n", i + 1, arr[i]);
                }
                System.out.println(lnspace);
                userInput = sc.nextLine();
                continue;
            }
            arr[counter] = userInput;
            counter += 1;
            System.out.println(lnspace);
            System.out.println("added: " + userInput);
            System.out.println(lnspace);
            userInput = sc.nextLine();
        }

        // terminate program: close scanner, print ending message
        sc.close();
        String ending = lnspace + "\n"
                + "Bye. Hope to see you again soon!\n"
                + lnspace;
        System.out.println(ending);
    }
}
