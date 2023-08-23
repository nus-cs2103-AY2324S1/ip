import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        printHorizontalLine();
        System.out.println("WEEWOOWEEWOO WELCOME! I'm Siren");
        System.out.println("What can I do for you?");
        printHorizontalLine();
        takeInput();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void takeInput() {
        Task[] taskArray = new Task[100];
        Scanner sc = new Scanner(System.in);
        int count = 0;
        boolean isLoop = true;

        while (isLoop) {
            String userInput = sc.nextLine();
            String[] input = userInput.split(" ", 2);
            printHorizontalLine();
            try {
                switch (input[0]) {
                case "list":
                    listTasks(count, taskArray);
                    break;
                case "mark":
                case "unmark":
                    try {
                        int taskNum = Integer.parseInt(input[1]);
                        markTasks(input, taskArray, taskNum);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("BEEPBEEP! You forgot to give a task number!");
                    }
                    break;
                case "bye":
                    System.out.println("WEEEWOOWEEWOO GOODBYE! Hope to see you again soon!");
                    isLoop = false;
                    break;
                case "todo":
                    try {
                        taskArray[count] = new Todo(input[1]);
                        printAdded(count, taskArray[count]);
                        count++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("BEEPBEEP! You forgot to give a description!");
                    }
                    break;
                case "deadline":
                case "event":
                    try {
                        String[] remainLine = input[1].split(" /", 2);
                        deadlineOrEventTask(input[0], remainLine, taskArray, count);
                        count++;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("BEEPBEEP! You forgot to give a description or date/time!");
                    }
                    break;
                default:
                    throw new DukeException("Can you hear the siren? " +
                            "Because I don't know what that means!");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            printHorizontalLine();
        }
        sc.close();
    }

    public static void listTasks(int count, Task[] taskArray) {
        if (count == 0) {
            System.out.println("HEYYYYYYYY! There's nothing to show here!");
        } else {
            System.out.println("WHEET WHEET WHEET! Here are the tasks in your list:");
            for (int i = 0; i < count; i++) {
                System.out.println(i + 1 + "." + taskArray[i]);
            }
        }
    }

    public static void markTasks(String[] input, Task[] taskArray, int taskNum) throws DukeException {
        try {
            if (input[0].equals("mark")) {
                if (taskArray[taskNum - 1].isDone) {
                    System.out.println("WEEYA! Task was already marked as done!");
                } else {
                    System.out.println("GOTCHYA! I've marked this task as done!");
                    taskArray[taskNum - 1].markDone();
                }
            } else {
                if (!taskArray[taskNum - 1].isDone) {
                    System.out.println("OOPSIE! Task was already marked as not done!");
                } else {
                    System.out.println("HONKHONK! I've marked this task as not done yet!");
                    taskArray[taskNum - 1].markNotDone();
                }
            }
            System.out.println(taskArray[taskNum - 1].toString());
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("WARBLE WARBLE! This task number does not exist!");
        }
    }

    public static void printAdded(int count, Task action) {
        System.out.println("DINGDONG GOT IT! I've added this task:");
        System.out.println(action.toString());
        System.out.println("Now you have " + (count + 1) + " tasks in the list.");
    }

    public static void deadlineOrEventTask(String action, String[] remainLine, Task[] taskArray, int count)
            throws DukeException {
        if (action.equals("deadline")) {
            String dateTime = remainLine[1].substring(3);
            taskArray[count] = new Deadline(remainLine[0],dateTime);
        } else {
            try {
                String[] splitTo = remainLine[1].split("/to ", 2);
                String fromDateTime = splitTo[0].substring(5, splitTo[0].length() - 1);
                taskArray[count] = new Event(remainLine[0], fromDateTime, splitTo[1]);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("BEEPBEEP! You forget to give a ending date/time for the event!");
            }
        }
        printAdded(count, taskArray[count]);
    }
}
