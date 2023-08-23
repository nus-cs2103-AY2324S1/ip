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
        boolean loop = true;

        while (loop) {
            String userInput = sc.nextLine();
            String[] input = userInput.split(" ", 2);
            printHoriLine();

            switch (input[0]) {
                case "list":
                    listTasks(count, taskArray);
                    break;
                case "mark":
                case "unmark":
                    if ((input.length >= 2 && input[1].isBlank()) || input.length == 1) {
                        System.out.println("BEEPBEEP! You may have forgotten to give a task number!");
                    } else {
                        int taskNum = Integer.parseInt(input[1]);
                        if (taskNum > count || taskNum < 1) {
                            System.out.println("WARBLE WARBLE! This task number does not exist!");
                        } else {
                            markTasks(input, taskArray, taskNum);
                        }
                    }
                    break;
                case "bye":
                    System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
                    loop = false;
                    break;
                case "todo":
                    taskArray[count] = new Todo(input[1]);
                    printAdded(count, taskArray[count]);
                    count++;
                    break;
                case "deadline":
                case "event":
                    String[] remainLine = input[1].split("/", 2);
                    deadlineOrEventTask(input[0], remainLine, taskArray, count);
                    count++;
            }
            printHoriLine();
        }
        sc.close();
    }

    public static void listTasks(int count, Task[] taskArray) {
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

    public static void markTasks(String[] input, Task[] taskArray, int taskNum) {
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

    public static void printAdded(int count, Task action) {
        System.out.println("DINGDONG GOT IT! I've added this task:");
        System.out.println(action.toString());
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
    }

    public static void deadlineOrEventTask(String action, String[] remainLine, Task[] taskArray, int count) {
        if (action.equals("deadline")) {
            String dateTime = remainLine[1].substring(3);
            taskArray[count] = new Deadline(remainLine[0],dateTime);
        }
        else {
            String[] splitTo = remainLine[1].split("/to ", 2);
            String fromDateTime = splitTo[0].substring(5, splitTo[0].length() - 1);
            taskArray[count] = new Event(remainLine[0], fromDateTime, splitTo[1]);
        }
        printAdded(count, taskArray[count]);
    }
}
