import java.util.Scanner;
import java.util.regex.*;

import dukeExceptions.*;

public class Duke {

    enum Commands {
        list,
        mark,
        unmark,
        taskaddition,
        delete,
        unknown
    }

    public static void main(String[] args) throws DukeException {
        // Print greeting
        String lnspace = "____________________________________________________________";
        String greeting = lnspace + "\n"
                + "Hello! I'm Lorem\n"
                + "What can I do for you?\n"
                + lnspace + "\n";

        System.out.println(greeting);

        // initialise scanner to detect user input
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim();

        // initialise array to store user input
        TaskList arr = new TaskList();

        // while loop to continuously take in inputs until user types bye
        while (!userInput.equals("bye")) {
            System.out.println(lnspace);

            try {
                // basic user input processing
                boolean isList = userInput.equals("list");
                boolean isMark = Pattern.compile("^mark").matcher(userInput).find();
                boolean isUnmark = Pattern.compile("^unmark").matcher(userInput).find();
                boolean isTodo = Pattern.compile("^todo").matcher(userInput).find();
                boolean isDeadline = Pattern.compile("^deadline").matcher(userInput).find();
                boolean isEvent = Pattern.compile("^event").matcher(userInput).find();
                boolean isDelete = Pattern.compile("^delete").matcher(userInput).find();
                boolean isValidTask = isTodo || isDeadline || isEvent;

                Commands command = isList
                        ? Commands.list
                        : isMark
                                ? Commands.mark
                                : isUnmark
                                        ? Commands.unmark
                                        : isValidTask
                                                ? Commands.taskaddition
                                                : isDelete
                                                        ? Commands.delete
                                                        : Commands.unknown;

                switch (command) {
                    case list: {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < arr.length(); i++) {
                            System.out.printf("%d. %s\n", i + 1, arr.taskToString(i));
                        }
                        break;
                    }
                    case mark: {
                        int markTask = Integer.parseInt(userInput.split(" ")[1]);
                        try {
                            arr.markTaskAsDone(markTask - 1);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(arr.taskToString(markTask - 1));
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case unmark: {
                        int unmarkTask = Integer.parseInt(userInput.split(" ")[1]);
                        try {
                            arr.markTaskAsNotDone(unmarkTask - 1);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(arr.taskToString(unmarkTask - 1));
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case taskaddition: {
                        try {
                            if (isTodo) {
                                arr.addTask(Todo.of(userInput));
                            } else if (isDeadline) {
                                arr.addTask(Deadline.of(userInput));
                            } else if (isEvent) {
                                arr.addTask(Event.of(userInput));
                            }
                            System.out.println("Got it. I've added this task:");
                            System.out.println(arr.taskToString(arr.length() - 1));
                            System.out.println("Now you have " + arr.numTasksToString() + " in the list.");
                        } catch (MissingInformationException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case delete: {
                        try {
                            Matcher matcher = Pattern.compile("delete ").matcher(userInput);
                            if (!matcher.find()) {
                                // return error
                            }
                            int index = Integer.parseInt(userInput.substring(matcher.end()).trim());
                            String description = arr.getTask(index - 1).toString();
                            arr.delete(index - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(description);
                            System.out.println("Now you have " + arr.numTasksToString() + " in the list.");
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case unknown: {
                        throw new UnknownTaskTypeException();
                    }
                    default: {
                        System.out.println(lnspace);
                        userInput = sc.nextLine();
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // if not continue to listen for further user inputs
            System.out.println(lnspace);
            userInput = sc.nextLine();
        }

        // terminate program: close scanner, print ending message
        sc.close();
        String ending = lnspace + "\n"
                + "Bye. Hope to see you again soon!\n"
                + lnspace;
        System.out.println(ending);
    }
}
