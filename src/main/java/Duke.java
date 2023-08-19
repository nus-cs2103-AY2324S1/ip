import java.util.Scanner;

public class Duke {

    public static Scanner scannerObj = new Scanner(System.in);
    public static String userInput;

    public static void main(String[] args) {
        printIntro();
        promptInput();
    }

    private static void printIntro() {
        printLine();
        System.out.println("Hello! I'm Roe!\n" + "What can I do for you?\n");
        printLine();
    }

    private static void printEnd() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void echoString(String input) {
        printLine();
        System.out.println(input + "\n");
        printLine();
    }

    private static String promptInput() {
        System.out.println();
        userInput = scannerObj.nextLine();
        System.out.println();

        try {
            do {
                String[] inputArray = userInput.split(" ", 2);
                String userCommand = inputArray[0];
                String inputInfo = "";
                if (inputArray.length == 2) {
                    inputInfo = inputArray[1];
                }

                switch (userCommand) {
                    case "list":
                        Task.listTasks();
                        break;
                    case "mark":
                        Task.markTask(Integer.parseInt(userInput.split(" ")[1]));
                        break;
                    case "unmark":
                        Task.unmarkTask(Integer.parseInt(userInput.split(" ")[1]));
                        break;
                    case "todo": {
                        String taskName = inputInfo;
                        Task newTask = new Todo(taskName);
                        Task.addTask(newTask);
                        break;
                    }
                    case "deadline": {
                        String[] taskInfo = inputInfo.split(" /by ");

                        if (taskInfo.length != 2) {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }

                        String taskName = taskInfo[0];
                        String deadline = taskInfo[1];
                        Task newTask = new Deadline(taskName, deadline);
                        Task.addTask(newTask);
                        break;
                    }
                    case "event": {
                        String[] taskInfo = inputInfo.split(" /from ");

                        if (taskInfo.length != 2 || taskInfo[1].split("/to").length != 2) {
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }

                        String taskName = taskInfo[0];
                        String startTime = taskInfo[1].split(" /to ")[0];
                        String endTime = taskInfo[1].split(" /to ")[1];
                        Task newTask = new Event(taskName, startTime, endTime);
                        Task.addTask(newTask);
                        break;
                    }
                    default: {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                }
                promptInput();
            } while (!userInput.equals("bye"));
            printEnd();
        } catch (DukeException e) {
            printLine();
            System.out.println(e.getMessage());
            printLine();
            promptInput();
        }

        return userInput;
    }

    public static void printLine() {
        int charCount = 50;
        for (int i = 0; i < charCount; i++) {
            System.out.print("─");
        }
        System.out.print("\n");
    }
}
