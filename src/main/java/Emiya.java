import emiyaexception.*;

import logic.Logic;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Emiya {

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
        Storage storage = new Storage();
        try {
            storage.createDirectory("data");
            storage.createFileInDirectory("emiya.txt", "data");
        } catch (CreateDirectoryFailException e) {
            System.out.println(e.getMessage());
        }

        // Represents the list as an ArrayList of task.Task objects
        ArrayList<Task> taskArrayList = new ArrayList<>();
        try {
            storage.fillListWithFileContent(taskArrayList, storage.fileContents("emiya.txt", "data"));
        } catch (EmiyaException e) {
            System.out.println(e.getMessage());
        }
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
                    for (Task task : taskArrayList) {
                        if (task == null) {
                            if (listPointer == 1) {
                                // throw new EmiyaException("List is empty! Please add items to list before trying to display list contents!");
                                throw new ListEmptyException();
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
                    // throw new EmiyaException("Unknown command received! Please try again!");
                    throw new UnknownCommandException();
                }

                // Used to handle the case of when mark and unmark commands are used.
                // If the commands are used, will parse String into Integer type.
                String typeOfTask = partsOfInput[0];
                String taskDetails = "";
                if (partsOfInput.length > 1) {
                    taskDetails = partsOfInput[1];
                }
                Integer position = null;

                if (Logic.isNumeric(taskDetails)) {
                    position = Integer.parseInt(taskDetails);
                }

                switch (typeOfTask) {
                case "mark":
                    if (position != null) {
                        if (position <= 0 || position > taskArrayList.size()) {
                            // throw new EmiyaException("task.Task does not exist! Please try with a different value");
                            throw new OutOfListBoundsException();
                        }
                        taskArrayList.get(position-1).setMarked();
                        System.out.println("-----------------------------------------\n" +
                                "Nice job! I have marked this task as done:\n" + taskArrayList.get(position-1) + "\n"
                                + "-----------------------------------------\n");
                    } else {
                        // throw new EmiyaException("Unknown command received! Please try again!");
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskArrayList, "emiya.txt", "data");
                    break;
                case "unmark":
                    if (position != null) {
                        if (position <= 0 || position > taskArrayList.size()) {
                            // throw new EmiyaException("task.Task does not exist! Please try with a different value");
                            throw new OutOfListBoundsException();
                        }
                        taskArrayList.get(position-1).setUnmarked();
                        System.out.println("-----------------------------------------\n" +
                                "Oof, alright I have set this task as unmarked:\n" + taskArrayList.get(position-1) + "\n"
                                + "-----------------------------------------\n");
                    } else {
                        // throw new EmiyaException("Unknown command received! Please try again!");
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskArrayList, "emiya.txt", "data");
                    break;
                case "delete":
                    if (position != null) {
                        if (position <= 0 || position > taskArrayList.size()) {
                            // throw new EmiyaException("task.Task does not exist! Please try with a different value");
                            throw new OutOfListBoundsException();
                        }
                        Task task = taskArrayList.get(position-1);
                        taskArrayList.remove(task);
                        String deleteOutputMessage;
                        if (taskArrayList.size() == 1) {
                            deleteOutputMessage = "-----------------------------------------\n" +
                                    "Sure, I shall now delete the following task:\n" + task + "\n"
                                    + "Now you have " + taskArrayList.size() + " task in your list!\n"
                                    + "-----------------------------------------\n";
                        } else {
                            deleteOutputMessage = "-----------------------------------------\n" +
                                    "Sure, I shall now delete the following task:\n" + task + "\n"
                                    + "Now you have " + taskArrayList.size() + " tasks in your list!\n"
                                    + "-----------------------------------------\n";
                        }
                        System.out.println(deleteOutputMessage);
                    } else {
                        // throw new EmiyaException("Unknown command received! Please try again!");
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskArrayList, "emiya.txt", "data");
                    break;
                case "todo":
                    // need to be able to go through the rest of the string and add it inside
                    if (taskDetails.length() < 1) {
                        // throw new EmiyaException("Oh no! Tod0 tasks cannot be empty! Please try again!");
                        throw new EmptyTodoException();
                    }
                    ToDo todo = new ToDo(false, taskDetails);
                    taskArrayList.add(todo);
                    String todoOutputMessage;
                    if (taskArrayList.size() == 1) {
                        todoOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + todo + "\n"
                                + "Now you have " + taskArrayList.size() + " task in your list!\n"
                                + "-----------------------------------------\n";
                    } else {
                        todoOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + todo + "\n"
                                + "Now you have " + taskArrayList.size() + " tasks in your list!\n"
                                + "-----------------------------------------\n";
                    }
                    System.out.println(todoOutputMessage);
                    storage.writeToFileFromTaskList(taskArrayList, "emiya.txt", "data");
                    break;
                case "deadline": // go through taskDetails and find /by
                    if (taskDetails.length() < 1) {
                        // throw new EmiyaException("Oh no! task.Deadline tasks cannot be empty! Please try again!");
                        throw new EmptyDeadlineException();
                    }
                    String[] deadlineDetails = taskDetails.split(" /by ", 2);
                    if (deadlineDetails.length <= 1) {
                        // throw new EmiyaException("It seems like there's an error in your input! Did you remember to use /by in your input?");
                        throw new NoByException();
                    }
                    Deadline deadline = new Deadline(false, deadlineDetails[0], deadlineDetails[1]);
                    taskArrayList.add(deadline);
                    String deadlineOutputMessage;
                    if (taskArrayList.size() == 1) {
                        deadlineOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + deadline + "\n"
                                + "Now you have " + taskArrayList.size() + " task in your list!\n"
                                + "-----------------------------------------\n";
                    } else {
                        deadlineOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + deadline + "\n"
                                + "Now you have " + taskArrayList.size() + " tasks in your list!\n"
                                + "-----------------------------------------\n";
                    }
                    System.out.println(deadlineOutputMessage);
                    storage.writeToFileFromTaskList(taskArrayList, "emiya.txt", "data");
                    break;
                case "event": // need to go through taskDetails and find /from and /to
                    if (taskDetails.length() <= 1) {
                        // throw new EmiyaException("Oh no! task.Event tasks cannot be empty! Please try again!");
                        throw new EmptyEventException();
                    }
                    String[] eventDetails = taskDetails.split(" /from ", 2);
                    if (eventDetails.length <= 1) {
                        // throw new EmiyaException("It seems like there's an error in your input! Did you remember to use /from in your input?");
                        throw new NoFromException();
                    }
                    String[] eventDurationDetails = eventDetails[1].split(" /to ", 2);
                    if (eventDurationDetails.length <= 1) {
                        // throw new EmiyaException("It seems like there's an error in your input! Did you remember to use /to in your input?");
                        throw new NoToException();
                    }
                    Event event = new Event(false, eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]);
                    taskArrayList.add(event);
                    String eventOutputMessage;
                    if (taskArrayList.size() == 1) {
                        eventOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + event + "\n"
                                + "Now you have " + taskArrayList.size() + " task in your list!\n"
                                + "-----------------------------------------\n";
                    } else {
                        eventOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + event + "\n"
                                + "Now you have " + taskArrayList.size() + " tasks in your list!\n"
                                + "-----------------------------------------\n";
                    }
                    System.out.println(eventOutputMessage);
                    storage.writeToFileFromTaskList(taskArrayList, "emiya.txt", "data");
                    break;
                default:
                    // throw new EmiyaException("Unknown command received! Please try again!");
                    throw new UnknownCommandException();
                }
            } catch (EmiyaException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(exitMessage);

        myScannerObj.close();
    }
}
