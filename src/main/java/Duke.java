import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        printHoriLine();
        System.out.println("WEEWOOWEEWOO WELCOME! I'm Siren");
        System.out.println("What can I do for you?");
        printHoriLine();
        takeInput();
    }

    public static void printHoriLine() {
        System.out.println("____________________________________________________________");
    }

    public static void takeInput() {
        Task[] taskArray = new Task[100];
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            String userInput = sc.nextLine();
            String[] input = userInput.split(" ");
            printHoriLine();

            if (input[0].equals("list")) {
                listAction(count, taskArray);
            }
            else if (input[0].contains("mark")) {
                if ((input.length >= 2 && input[1].isBlank()) || input.length == 1) {
                    System.out.println("BEEPBEEP! You may have forgotten to give a task number!");
                }
                else {
                    int taskNum = Integer.parseInt(input[1]);
                    if (taskNum > count || taskNum < 1) {
                        System.out.println("WARBLE WARBLE! This task number does not exist!");
                    }
                    else {
                        markAction(input, taskArray, taskNum);
                    }
                }

            }
            else if (input[0].equals("bye")) {
                System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
                printHoriLine();
                break;
            }
            else {
                System.out.println("WAAAAAAOOOOO! Added: " + userInput);
                taskArray[count] = new Task(userInput);
                count++;
            }
            printHoriLine();
        }
    }

    public static void listAction(int count, Task[] taskArray) {
        if (count == 0) {
            System.out.println("HEYYYYYYYY! There's nothing to show here!");
        }
        else {
            System.out.println("WHEET WHEET WHEET! Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println(i + 1 + "." + taskArray[i]);
            }
        }
    }

    public static void markAction(String[] input, Task[] taskArray, int taskNum) {
        if (input[0].equals("mark")) {
            if (taskArray[taskNum - 1].isDone) {
                System.out.println("WEEYA! Task was already marked as done!");
            }
            else {
                System.out.println("GOTCHYA! I've marked this task as done!");
                taskArray[taskNum - 1].markDone();
            }
        }
        else {
            if (!taskArray[taskNum - 1].isDone) {
                System.out.println("OOPSIE! Task was already marked as not done!");
            }
            else {
                System.out.println("HONKHONK! I've marked this task as not done yet!");
                taskArray[taskNum - 1].markNotDone();
            }
        }
        System.out.println(taskArray[taskNum - 1].toString());
    }
}
