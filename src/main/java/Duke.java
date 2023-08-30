import java.util.Scanner;

public class Duke {
    private static void printOneLine() {
        System.out.println("---------------------------");
    }

    private static final String MyName = "Rio";
    public static void Greet() {
        printOneLine();
        System.out.println("Hello! I'm " + MyName);
        System.out.println("What can I do for you?");
        printOneLine();
    }

    public  static void Exit() {
        printOneLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printOneLine();
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        Greet();

        boolean wantToExit = false;
        Scanner getUserInput = new Scanner(System.in);
        while (!(wantToExit)) {
            String userInput = getUserInput.nextLine();
            if (userInput.equals("bye")) {
                wantToExit = true;
                Exit();
            } else {
                printOneLine();
                System.out.println(userInput);
                printOneLine();
            }
        }




    }
}
