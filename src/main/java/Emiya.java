import java.util.Scanner;

public class Emiya {

    // Used to check whether a given string contains purely numeric values.
    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    // Checks if a given test String is part of the reserved keywords for the different tasks.
    public static boolean enumContainsKeyword(String test) {
        for (Keywords k : Keywords.values()) {
            if (k.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        // Pointer to determine where to add objects to array
        int arrayPointer = 0;

        // Represents the list as an array of Task objects
        Task[] taskArray = new Task[100];
        String welcomeMessage = "-----------------------------------------\n"
                + "Hello! I'm Emiya\n"
                + "What can I do for you?\n"
                + "-----------------------------------------\n";
        String exitMessage = "-----------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-----------------------------------------\n";
        Scanner myScannerObj = new Scanner(System.in);

        System.out.println(welcomeMessage);

        while (true) {
            try {
                // nextLine is blocking, so can have this here
                String input = myScannerObj.nextLine();
                // handles numbering for the list
                int listPointer = 1;
                String[] partsOfInput = input.split("\\s+", 2);

                // Terminates the program by exiting the while loop.
                if (input.equals("bye")) {
                    break;
                }

                if (input.equals("I am the bone of my sword")) {
                    System.out.println("-----------------------------------------\n"
                            + "Unknown to death nor known to life" + "\n"
                            + "-----------------------------------------\n");
                    continue;
                }

                if (input.equals("dead")) {
                    System.out.println("-----------------------------------------\n"
                            + "People die if they are killed!" + "\n"
                            + "-----------------------------------------\n");
                    continue;
                }

                // Method to list out all items in list. If the list is empty, throws exception
                // and informs user to add items to list.
                if (input.equals("list")) {
                    StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
                            "Lots of things to do! Get to it!:\n");
                    for (Task task : taskArray) {
                        if (task == null) {
                            if (listPointer == 1) {
                                throw new EmiyaException("List is empty! Please add items to list before trying to display list contents!");
                            }
                            break;
                        }
                        String listItem = listPointer + "." + task + "\n";
                        listPointer++;
                        listString.append(listItem);
                    }
                    listString.append("-----------------------------------------\n");
                    System.out.println(listString);
                    continue;
                }

                // If a given input is only one word long (partsOfInput < 2 ) and not a reserved keyword, will throw exception
                // to inform them that an unknown command was received.
                if (partsOfInput.length < 2 && !enumContainsKeyword(partsOfInput[0].toUpperCase())) {
                    throw new EmiyaException("Unknown command received! Please try again!");
                }

                // Used to handle the case of when mark and unmark commands are used.
                // If the commands are used, will parse String into Integer type.
                String typeOfTask = partsOfInput[0];
                String taskDetails = "";
                if (partsOfInput.length > 1) {
                    taskDetails = partsOfInput[1];
                }
                Integer position = null;

                if (isNumeric(taskDetails)) {
                    position = Integer.parseInt(taskDetails);
                }

                switch (typeOfTask) {
                    case "mark":
                        if (position != null) {
                            if (position < 0 || taskArray[position - 1] == null) {
                                throw new EmiyaException("Task does not exist! Please try with a different value");
                            }
                            taskArray[position - 1].setMarked();
                            System.out.println("-----------------------------------------\n" +
                                    "Nice job! I have marked this task as done:\n" + taskArray[position - 1] + "\n"
                                    + "-----------------------------------------\n");
                            break;
                        }
                        // if not a specific mark typeOfTask, continue
                    case "unmark":
                        if (position != null) {
                            if (position < 0 || taskArray[position - 1] == null) {
                                throw new EmiyaException("Task does not exist! Please try with a different value");
                            }
                            taskArray[position - 1].setUnmarked();
                            System.out.println("-----------------------------------------\n" +
                                    "Oof, alright I have set this task as unmarked:\n" + taskArray[position - 1] + "\n"
                                    + "-----------------------------------------\n");
                            break;
                        }
                        // if not a specific unmark typeOfTask, continue
                    case "todo":
                        // need to be able to go through the rest of the string and add it inside
                        if (taskDetails.length() < 1) {
                            throw new EmiyaException("Oh no! Todo tasks cannot be empty! Please try again!");
                        }
                        taskArray[arrayPointer] = new ToDo(taskDetails);
                        arrayPointer++;
                        String todoOutputMessage = "";
                        if (arrayPointer == 1) {
                            todoOutputMessage = "-----------------------------------------\n" +
                                    "Sure! I have added this task to the list:\n" + taskArray[arrayPointer - 1] + "\n"
                                    + "Now you have " + arrayPointer + " task in your list!\n"
                                    + "-----------------------------------------\n";
                        } else {
                            todoOutputMessage = "-----------------------------------------\n" +
                                    "Sure! I have added this task to the list:\n" + taskArray[arrayPointer - 1] + "\n"
                                    + "Now you have " + arrayPointer + " tasks in your list!\n"
                                    + "-----------------------------------------\n";
                        }
                        System.out.println(todoOutputMessage);
                        break;
                    case "deadline": // go through taskDetails and find /by
                        if (taskDetails.length() < 1) {
                            throw new EmiyaException("Oh no! Deadline tasks cannot be empty! Please try again!");
                        }
                        String[] deadlineDetails = taskDetails.split("/by", 2);
                        if (deadlineDetails.length <= 1) {
                            throw new EmiyaException("It seems like there's an error in your input! Did you remember to use /by in your input?");
                        }
                        taskArray[arrayPointer] = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        arrayPointer++;
                        String deadlineOutputMessage = "";
                        if (arrayPointer == 1) {
                            deadlineOutputMessage = "-----------------------------------------\n" +
                                    "Sure! I have added this task to the list:\n" + taskArray[arrayPointer - 1] + "\n"
                                    + "Now you have " + arrayPointer + " task in your list!\n"
                                    + "-----------------------------------------\n";
                        } else {
                            deadlineOutputMessage = "-----------------------------------------\n" +
                                    "Sure! I have added this task to the list:\n" + taskArray[arrayPointer - 1] + "\n"
                                    + "Now you have " + arrayPointer + " tasks in your list!\n"
                                    + "-----------------------------------------\n";
                        }
                        System.out.println(deadlineOutputMessage);
                        break;
                    case "event": // need to go through taskDetails and find /from and /to
                        if (taskDetails.length() <= 1) {
                            throw new EmiyaException("Oh no! Event tasks cannot be empty! Please try again!");
                        }
                        String[] eventDetails = taskDetails.split("/from ", 2);
                        if (eventDetails.length <= 1) {
                            throw new EmiyaException("It seems like there's an error in your input! Did you remember to use /from in your input?");
                        }
                        String[] eventDurationDetails = eventDetails[1].split("/to ", 2);
                        if (eventDurationDetails.length <= 1) {
                            throw new EmiyaException("It seems like there's an error in your input! Did you remember to use /to in your input?");
                        }
                        taskArray[arrayPointer] = new Event(eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]);
                        arrayPointer++;
                        String eventOutputMessage = "";
                        if (arrayPointer == 1) {
                            eventOutputMessage = "-----------------------------------------\n" +
                                    "Sure! I have added this task to the list:\n" + taskArray[arrayPointer - 1] + "\n"
                                    + "Now you have " + arrayPointer + " task in your list!\n"
                                    + "-----------------------------------------\n";
                        } else {
                            eventOutputMessage = "-----------------------------------------\n" +
                                    "Sure! I have added this task to the list:\n" + taskArray[arrayPointer - 1] + "\n"
                                    + "Now you have " + arrayPointer + " tasks in your list!\n"
                                    + "-----------------------------------------\n";
                        }

                        System.out.println(eventOutputMessage);
                        break;
                    default:
                        throw new EmiyaException("Unknown command received! Please try again!");
                }
            } catch (EmiyaException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(exitMessage);

        myScannerObj.close();
    }
}
