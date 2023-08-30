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

        System.out.println(" Bye. Hope to see you again soon!");

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
        String[] userList = new String[100];
        int listPointer = 0;

        while (!(wantToExit)) {
            String userInput = getUserInput.nextLine();

            printOneLine();
            switch (userInput) {

                case "bye":
                    wantToExit = true;
                    Exit();
                    break;

                case "list":

                    if (listPointer < 1) {
                        System.out.println("No items in the list yet");
                    } else {
                        for (int i = 0; i < listPointer; i++) {
                            int num = i + 1;
                            System.out.println(num + ". " + userList[i]);
                        }
                    }

                    break;

                default:
                    userList[listPointer] = userInput;
                    listPointer = listPointer + 1;
                    printOneLine();
                    System.out.println("added: " + userInput);
                    printOneLine();

            }
            printOneLine();

        }




    }
}
