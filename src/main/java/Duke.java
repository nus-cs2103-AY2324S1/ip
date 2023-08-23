import java.util.ArrayList;
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
        ArrayList<Task> taskArray = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean isLoop = true;

        while (isLoop) {
            String userInput = sc.nextLine();
            String[] input = userInput.split(" ", 2);
            printHorizontalLine();
            try {
                switch (input[0]) {
                case "list":
                    listTasks(taskArray);
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
                        taskArray.add(new Todo(input[1]));
                        printAdded(taskArray.size(), taskArray.get(taskArray.size() - 1));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("BEEPBEEP! You forgot to give a description!");
                    }
                    break;
                case "deadline":
                case "event":
                    try {
                        String[] remainLine = input[1].split(" /", 2);
                        deadlineOrEventTask(input[0], remainLine, taskArray);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("BEEPBEEP! You forgot to give a description or date/time!");
                    }
                    break;
                case "delete":
                    try {
                        int taskNum = Integer.parseInt(input[1]);
                        deleteTasks(taskArray, taskNum);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeException("BEEPBEEP! You forgot to give a task number!");
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

    public static void listTasks(ArrayList<Task> taskArray) {
        if (taskArray.isEmpty()) {
            System.out.println("HEYYYYYYYY! There's nothing to show here!");
        } else {
            System.out.println("WHEET WHEET WHEET! Here are the tasks in your list:");
            for (int i = 0; i < taskArray.size(); i++) {
                System.out.println(i + 1 + "." + taskArray.get(i));
            }
        }
    }

    public static void markTasks(String[] input, ArrayList<Task> taskArray, int taskNum) throws DukeException {
        try {
            if (input[0].equals("mark")) {
                if (taskArray.get(taskNum - 1).isDone) {
                    System.out.println("WEEYA! Task was already marked as done!");
                } else {
                    System.out.println("GOTCHYA! I've marked this task as done!");
                    taskArray.get(taskNum - 1).markDone();
                }
            } else {
                if (!taskArray.get(taskNum - 1).isDone) {
                    System.out.println("OOPSIE! Task was already marked as not done!");
                } else {
                    System.out.println("HONKHONK! I've marked this task as not done yet!");
                    taskArray.get(taskNum - 1).markNotDone();
                }
            }
            System.out.println(taskArray.get(taskNum - 1).toString());
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new DukeException("WARBLE WARBLE! This task number does not exist!");
        }
    }

    public static void printAdded(int numOfTasks, Task action) {
        System.out.println("DINGDONG GOT IT! I've added this task:");
        System.out.println(action.toString());
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public static void deadlineOrEventTask(String action, String[] remainLine, ArrayList<Task> taskArray)
            throws DukeException {
        if (action.equals("deadline")) {
            if (!remainLine[1].contains("by")) {
                System.out.println("BEEPBEEP! You forgot to give a \"/by date/time\" for the deadline!");
                return;
            }
            String dateTime = remainLine[1].substring(3);
            taskArray.add(new Deadline(remainLine[0],dateTime));
        } else {
            if (!remainLine[1].contains("from ")) {
                System.out.println("BEEPBEEP! You forgot to give a \"/from date/time\" for the event");
                return;
            }
            try {
                String[] splitTo = remainLine[1].split("/to ", 2);
                String fromDateTime = splitTo[0].substring(5, splitTo[0].length() - 1);
                taskArray.add(new Event(remainLine[0], fromDateTime, splitTo[1]));
            } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("BEEPBEEP! You forget to give a \"/to date/time\" for the event!");
            }
        }
        printAdded(taskArray.size(), taskArray.get(taskArray.size() - 1));
    }
    public static void deleteTasks(ArrayList<Task> taskArray, int taskNum) {
        try {
            Task taskDeleted = taskArray.get(taskNum - 1);
            taskArray.remove(taskNum - 1);
            System.out.println("ALRIGHTY! I've removed this task:");
            System.out.println(taskDeleted.toString());
            System.out.println("Now you have " + taskArray.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("WARBLE WARBLE, this task number does not exist!");
        }
    }
}
