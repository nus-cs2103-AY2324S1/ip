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

    private static String getUserTaskName() {
        Scanner getUserInput = new Scanner(System.in);
        String taskName = getUserInput.nextLine();
        if (taskName.isEmpty()) {
            System.out.println("OOPS!!! The name of a task cannot be empty.");
            return "";
        } else {
            return taskName;
        }

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

                case "todo":
                    System.out.println("Enter task name:");
                    String taskName = getUserTaskName();
                    if (!(taskName.isEmpty())) {
                        userList[listPointer] = new Task(taskName, 1, "");

                        System.out.println("Got it. I've added this task:");

                        System.out.println(userList[listPointer].display());

                        listPointer = listPointer + 1;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");
                    }

                    break;

                case "deadline":
                    System.out.println("Enter task name:");
                    String taskN = getUserTaskName();
                    if (!(taskN.isEmpty())) {
                        System.out.println("Enter deadline:");
                        String timePeriod = getUserInput.nextLine();
                        userList[listPointer] = new Task(taskN, 2, "by:" + timePeriod);

                        System.out.println("Got it. I've added this task:");

                        System.out.println(userList[listPointer].display());

                        listPointer = listPointer + 1;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");
                    }
                    break;

                case "event":
                    System.out.println("Enter task name:");
                    String tN = getUserTaskName();
                    if (!(tN.isEmpty())) {
                        System.out.println("Enter start time:");
                        String startTime = getUserInput.nextLine();
                        System.out.println("Enter end time:");
                        String endTime = getUserInput.nextLine();
                        userList[listPointer] = new Task(tN, 3, "from: " + startTime + " to: " + endTime);

                        System.out.println("Got it. I've added this task:");

                        System.out.println(userList[listPointer].display());

                        listPointer = listPointer + 1;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");
                    }
                    break;

                case "delete":
                    System.out.println("Enter index:");
                    int ind = getUserIndex.nextInt() - 1;
                    if (ind < 0 || ind >= listPointer) {
                        System.out.println("Invalid index.");
                    } else {

                        System.out.println("Noted. I've removed this task:");

                        System.out.println(userList[listPointer].display());

                        Task[] newUserList = new Task[100];

                        for (int a = 0, k = 0; a < listPointer; a++) {

                            // if the index is
                            // the removal element index
                            if (a == ind) {
                                continue;
                            }

                            // if the index is not
                            // the removal element index
                            newUserList[k++] = userList[a];
                        }

                        listPointer = listPointer - 1;

                        userList = newUserList;

                        System.out.println("Now you have " + listPointer + " tasks in the list.");

                    }

                    break;


                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");


            }
            printOneLine();

        }




    }
}
