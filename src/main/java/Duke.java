import exception.DukeException;
import exception.InvalidCommandException;
import exception.InvalidSyntaxException;

import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String[] commands = new String[] {"bye", "list", "todo", "deadline", "event",
                "mark", "unmark"};
        boolean listen = true;
        int index = 0;
        /** Captures user input*/
        Scanner jonBird = new Scanner(System.in);
        /** Stores user input*/
        Task[] inputList =  new Task[100];
        /** User input*/
        String input = "";
        String title = "";
        String startDate = "";
        String endDate = "";

        System.out.println("Hello! I'm JonBird.\nWhat can I do for you?\n");
        while (listen) {
            input = jonBird.nextLine().trim();
            String[] inp = input.split("\\s+");
            int taskIndex = 0;
            if (!isValidCommand(inp[0], commands)) {
                input = "";
            }
            if (inp[0].equalsIgnoreCase("mark") || inp[0].equalsIgnoreCase("unmark")) {
                if (inp.length == 2) {
                    try {
                        taskIndex = Integer.parseInt(inp[1]);
                        input = inp[0];
                    } catch (NumberFormatException e) {
                    }
                    ;
                }
            } else if (inp[0].equalsIgnoreCase("todo") || inp[0].equalsIgnoreCase("deadline")) {
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
                    if (endDate.equals("")) {endDate = inp[j]; }
                    else {
                        endDate += " " + inp[j];
                    }
                }
            } else if (inp[0].equalsIgnoreCase("event")) {
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

            switch (input) {
                case "":
                    DukeException excep = new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                    System.out.println("JonBird:\n\t" + excep.toString());
                    break;
                case "bye":
                    listen = false;
                    break;
                case "list":
                    System.out.println("JonBird:");
                    printList(inputList, index);
                    break;
                case "unmark":
                    inputList[taskIndex-1].markAsUndone();
                    break;
                case "mark":
                    inputList[taskIndex-1].markAsDone();
                    break;
                default:
                    if (inp.length < 2) {
                        excep = new InvalidSyntaxException("The description of a " + inp[0] + " cannot be empty.");
                        System.out.println("JonBird:\n\t" + excep.toString());
                        break;
                    }
                    if (inp[0].equals("todo")) {
                        inputList[index] = new Todos(title);
                    }
                    if (inp[0].equals("deadline")) {
                        if (endDate.equals("")) {
                            excep = new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
                            System.out.println("JonBird:\n\t" + excep.toString());
                            break;
                        }
                        inputList[index] = new Deadlines(title, endDate);
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
                        inputList[index] = new Events(title, startDate, endDate);
                    }
                    index += 1;
                    System.out.println("JonBird:\n\tGot it. I've added this task:");
                    System.out.println("\t\t" + inputList[index-1].printTask());
                    System.out.println("\tNow you have " + index + " tasks in the list.");
            }
        }
        jonBird.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(Task[] list, int index) {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            System.out.println("\t\t"+ (i+1) + ". " + list[i].printTask());
        }
    }

    public static boolean isValidCommand(String value, String[] commands) {
        for (String str: commands) {
            if (str.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
