import java.util.Scanner;
import HelperClass.Task;

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
        Scanner getUserIndex = new Scanner(System.in);
        Task[] userList = new Task[100];

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
                            System.out.println(num + userList[i].display());


                        }
                    }

                    break;

                case "mark":
                    System.out.println("Enter index:");
                    int index = getUserIndex.nextInt() - 1;
                    if (index < 0 || index >= listPointer) {
                        System.out.println("Invalid index.");
                    } else {

                        userList[index].markDone();

                    }

                    break;

                case "unmark":
                    System.out.println("Enter index:");
                    int i = getUserIndex.nextInt() - 1;
                    if (i < 0 || i >= listPointer) {
                        System.out.println("Invalid index.");
                    } else {

                        userList[i].unmarkDone();

                    }

                    break;


                default:
                    userList[listPointer] = new Task(userInput);
                    listPointer = listPointer + 1;

                    System.out.println("added: " + userInput);


            }
            printOneLine();

        }




    }
}
