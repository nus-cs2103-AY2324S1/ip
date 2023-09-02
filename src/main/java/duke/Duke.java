package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidSyntaxException;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.Todos;
import duke.task.TaskList;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Scanner;
public class Duke {

    public enum Command {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, UNKNOWN
    };

    public static void main(String[] args) {
        boolean listen = true;
        /** Captures user input*/
        Scanner jonBird = new Scanner(System.in);
        /** Stores user input*/
        TaskList inputList =  new TaskList();
        /** User input*/
        String input = "";
        Command currentCommand = Command.UNKNOWN;
        String title = "";
        String startDate = "";
        String endDate = "";
        Storage storage = new Storage();
        inputList = storage.loadData();

        System.out.println("Hello! I'm JonBird.\nWhat can I do for you?\n");
        while (listen) {
            input = jonBird.nextLine().trim();
            String[] inp = input.split("\\s+");
            int taskIndex = 0;
            boolean isValid = false;
            if (!isValidCommand(inp[0])) {
                currentCommand = Command.UNKNOWN;
            } else {
                currentCommand = Command.valueOf(inp[0].toUpperCase());
                if (currentCommand == Command.MARK || currentCommand == Command.UNMARK ||
                        currentCommand == Command.DELETE) {
                    if (inp.length == 2) {
                        try {
                            taskIndex = Integer.parseInt(inp[1]);
                        } catch (NumberFormatException e) {
                            currentCommand = Command.UNKNOWN;
                        }
                        ;
                    } else {
                        currentCommand = Command.UNKNOWN;
                    }
                } else if (currentCommand == Command.TODO || currentCommand == Command.DEADLINE) {
                    int i = 1;
                    title = "";
                    endDate = "";
                    for (; i < inp.length; i++) {
                        if (inp[i].equals("/by")) break;
                        if (i == 1) {
                            isValid = true;
                        }
                        if (title.equals("")) {
                            title = inp[i];
                        } else {
                            title += " " + inp[i];
                        }
                    }
                    for (int j = i + 1; j < inp.length; j++) {
                        if (endDate.equals("")) {
                            endDate = inp[j];
                        } else {
                            endDate += " " + inp[j];
                        }
                    }
                } else if (currentCommand == Command.EVENT) {
                    int start = 1;
                    int end = 0;
                    title = "";
                    startDate = "";
                    endDate = "";
                    for (; start < inp.length; start++) {
                        if (inp[start].equals("/from")) break;
                        if (start == 1) {
                            isValid = true;
                        }
                        if (title.equals("")) {
                            title = inp[start];
                        } else {
                            title += " " + inp[start];
                        }
                    }

                    for (end = start + 1; end < inp.length; end++) {
                        if (inp[end].equals("/to")) break;
                        if (startDate.equals("")) {
                            startDate = inp[end];
                        } else {
                            startDate += " " + inp[end];
                        }
                    }

                    for (int j = end + 1; j < inp.length; j++) {
                        if (endDate.equals("")) {
                            endDate = inp[j];
                        } else {
                            endDate += " " + inp[j];
                        }
                    }
                }
            }

            switch (currentCommand) {
                case UNKNOWN:
                    DukeException excep;
                    if (isValidCommand(inp[0])) {
                        excep = new InvalidSyntaxException("The format of the command is invalid.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    }
                    else {
                        excep = new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    }
                    break;
                case BYE:
                    listen = false;
                    break;
                case LIST:
                    System.out.println("JonBird:");
                    printList(inputList);
                    break;
                case UNMARK:
                    if (taskIndex > inputList.size() || taskIndex < 1) {
                        excep = new InvalidSyntaxException("The task does not exist.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    } else {
                        inputList.getTask(taskIndex-1).markAsUndone();
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        System.out.println("\t\t" + inputList.getTask(taskIndex-1).printTask());
                        storage.writeData(inputList.convertToFileContent());
                    }
                    break;
                case MARK:
                    if (taskIndex > inputList.size() || taskIndex < 1) {
                        excep = new InvalidSyntaxException("The task does not exist.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    } else {
                        inputList.getTask(taskIndex - 1).markAsDone();
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t\t" +inputList.getTask(taskIndex - 1).printTask());
                        storage.writeData(inputList.convertToFileContent());
                    }
                    break;
                case DELETE:
                    if (taskIndex > inputList.size() || taskIndex < 1) {
                        excep = new InvalidSyntaxException("The task does not exist.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    } else {
                        Task temp = inputList.getTask(taskIndex - 1);
                        inputList.removeTask(taskIndex - 1);
                        storage.writeData(inputList.convertToFileContent());
                        System.out.println("JonBird:\n\tNoted. I've removed this task:");
                        System.out.println("\t\t" + temp.printTask());
                        System.out.println("\tNow you have " + inputList.size() + " tasks in the list.");
                    }
                    break;
                default:
                    if (inp.length < 2 || !isValid) {
                        excep = new InvalidSyntaxException("The description of a " + inp[0] + " cannot be empty.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                        break;
                    }
                    if (inp[0].equals("todo")) {
                        inputList.addTask(new Todos(title));
                        storage.writeData(inputList.convertToFileContent());
                        System.out.println("JonBird:\n\tGot it. I've added this task:");
                        System.out.println("\t\t" + inputList.getTask(inputList.size()-1).printTask());
                        System.out.println("\tNow you have " + inputList.size() + " tasks in the list.");
                    }
                    if (inp[0].equals("deadline")) {
                        if (endDate.equals("")) {
                            excep = new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
                            System.out.println("JonBird:\n\t" + excep.toString());
                            break;
                        }
                        try {
                            inputList.addTask(new Deadlines(title, endDate));
                            storage.writeData(inputList.convertToFileContent());
                            System.out.println("JonBird:\n\tGot it. I've added this task:");
                            System.out.println("\t\t" + inputList.getTask(inputList.size()-1).printTask());
                            System.out.println("\tNow you have " + inputList.size() + " tasks in the list.");
                        } catch (DateTimeException e) {
                            System.out.println("JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\"" +
                                    " format. Put 00:00 if time does not matter.");
                        }
                    }
                    if (inp[0].equals("event")) {
                        if (startDate.equals("")) {
                            excep = new InvalidSyntaxException("The start date of a " + inp[0] + " cannot be empty.");
                            System.out.println("JonBird:\n\t" + excep.toString());
                            break;
                        }
                        if (endDate.equals("")) {
                            excep = new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
                            System.out.println("JonBird:\n\t" + excep.toString());
                            break;
                        }
                        try {
                            inputList.addTask(new Events(title, startDate, endDate));
                            storage.writeData(inputList.convertToFileContent());
                            System.out.println("JonBird:\n\tGot it. I've added this task:");
                            System.out.println("\t\t" + inputList.getTask(inputList.size()-1).printTask());
                            System.out.println("\tNow you have " + inputList.size() + " tasks in the list.");
                        } catch (DateTimeException e) {
                            System.out.println("JonBird:\n\t" + "Please ensure that your date is in \"yyyy-MM-dd HH:mm\"" +
                                    " format. Put 00:00 if time does not matter.");
                        }
                    }
            }
        }
        jonBird.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(TaskList taskList) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t\t"+ (i+1) + ". " + taskList.getTask(i).printTask());
        }
    }

    public static boolean isValidCommand(String value) {
        try {
            Command val = Command.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
