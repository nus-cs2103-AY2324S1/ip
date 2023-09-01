import exception.DukeException;
import exception.InvalidCommandException;
import exception.InvalidSyntaxException;
import java.util.ArrayList;

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
        ArrayList<Task> inputList =  new ArrayList<Task>();
        /** User input*/
        String input = "";
        Command currentCommand = Command.UNKNOWN;
        String title = "";
        String startDate = "";
        String endDate = "";

        System.out.println("Hello! I'm JonBird.\nWhat can I do for you?\n");
        while (listen) {
            input = jonBird.nextLine().trim();
            String[] inp = input.split("\\s+");
            int taskIndex = 0;
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
                        inputList.get(taskIndex-1).markAsUndone();
                    }
                    break;
                case MARK:
                    if (taskIndex > inputList.size() || taskIndex < 1) {
                        excep = new InvalidSyntaxException("The task does not exist.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    } else {
                        inputList.get(taskIndex - 1).markAsDone();
                    }
                    break;
                case DELETE:
                    if (taskIndex > inputList.size() || taskIndex < 1) {
                        excep = new InvalidSyntaxException("The task does not exist.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                    } else {
                        Task temp = inputList.get(taskIndex - 1);
                        inputList.remove(taskIndex - 1);
                        System.out.println("JonBird:\n\tNoted. I've removed this task:");
                        System.out.println("\t\t" + temp.printTask());
                        System.out.println("\tNow you have " + inputList.size() + " tasks in the list.");
                    }
                    break;
                default:
                    if (inp.length < 2) {
                        excep = new InvalidSyntaxException("The description of a " + inp[0] + " cannot be empty.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                        break;
                    }
                    if (inp[0].equals("todo")) {
                        inputList.add(new Todos(title));
                    }
                    if (inp[0].equals("deadline")) {
                        if (endDate.equals("")) {
                            excep = new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
                            System.out.println("JonBird:\n\t" + excep.toString());
                            break;
                        }
                        inputList.add(new Deadlines(title, endDate));
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
                        inputList.add(new Events(title, startDate, endDate));
                    }
                    System.out.println("JonBird:\n\tGot it. I've added this task:");
                    System.out.println("\t\t" + inputList.get(inputList.size()-1).printTask());
                    System.out.println("\tNow you have " + inputList.size() + " tasks in the list.");
            }
        }
        jonBird.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t\t"+ (i+1) + ". " + list.get(i).printTask());
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
