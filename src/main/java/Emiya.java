import emiyaexception.*;

import logic.Logic;
import task.*;
import ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

import static logic.Logic.enumContainsKeyword;

public class Emiya {
    // must remove static at the end
    private String dirName;
    private String fileName;
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();
    // private static Ui Ui = new Ui();

    public static void main(String[] args) {

        // shift try-catch to Storage; maybe no need shift?
        try {
            storage.createDirectory("data");
            storage.createFileInDirectory("emiya.txt", "data");
        } catch (CreateDirectoryFailException e) {
            System.out.println(e.getMessage());
        }

        // shift to TaskList
        try {
            storage.fillListWithFileContent(taskList, storage.fileContents("emiya.txt", "data"));
        } catch (EmiyaException e) {
            System.out.println(e.getMessage());
        }

        // keep
        Scanner myScannerObj = new Scanner(System.in);

        System.out.println(ui.Ui.WELCOME_MESSAGE);

        while (true) {
            try {
                // nextLine is blocking, so can have this here
                String input = myScannerObj.nextLine();

                // Terminates the program by exiting the while loop.
                if (input.equals("bye")) {
                    break;
                }

                // shift to TaskList
                // handles numbering for the list
                int listPointer = 1;

                //shift to parser
                String[] partsOfInput = input.split("\\s+", 2);

                // kiv shift where
                if (input.equals("I am the bone of my sword")) {
                    System.out.println("-----------------------------------------\n"
                            + "Unknown to death nor known to life" + "\n"
                            + "-----------------------------------------\n");
                    continue;
                }

                // kiv shift where
                if (input.equals("dead")) {
                    System.out.println("-----------------------------------------\n"
                            + "People die if they are killed!" + "\n"
                            + "-----------------------------------------\n");
                    continue;
                }

                // shift to TaskList
                // Method to list out all items in list. If the list is empty, throws exception
                // and informs user to add items to list.
                if (input.equals("list")) {
                    StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
                            "Lots of things to do! Get to it!:\n");
                    for (Task task : taskList.getTaskArrayList()) {
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

                // shift to parser
                // If a given input is only one word long (partsOfInput < 2 ) and not a reserved keyword, will throw exception
                // to inform them that an unknown command was received.
                if (partsOfInput.length < 2 && !enumContainsKeyword(partsOfInput[0].toUpperCase())) {
                    throw new UnknownCommandException();
                }

                // shift to parser
                // Used to handle the case of when mark and unmark commands are used.
                // If the commands are used, will parse String into Integer type.
                String typeOfTask = partsOfInput[0];
                String taskDetails = "";
                if (partsOfInput.length > 1) {
                    taskDetails = partsOfInput[1];
                }
                Integer position = null;

                // shift to parser
                if (Logic.isNumeric(taskDetails)) {
                    position = Integer.parseInt(taskDetails);
                }

                switch (typeOfTask) {
                case "mark":
                    if (position != null) {
                        if (position <= 0 || position > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        taskList.get(position-1).setMarked();
                        System.out.println("-----------------------------------------\n" +
                                "Nice job! I have marked this task as done:\n" + taskList.get(position-1) + "\n"
                                + "-----------------------------------------\n");
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "unmark":
                    if (position != null) {
                        if (position <= 0 || position > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        taskList.get(position-1).setUnmarked();
                        System.out.println("-----------------------------------------\n" +
                                "Oof, alright I have set this task as unmarked:\n" + taskList.get(position-1) + "\n"
                                + "-----------------------------------------\n");
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "delete":
                    if (position != null) {
                        if (position <= 0 || position > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        Task task = taskList.get(position-1);
                        taskList.remove(task);
                        String deleteOutputMessage;
                        if (taskList.size() == 1) {
                            deleteOutputMessage = "-----------------------------------------\n" +
                                    "Sure, I shall now delete the following task:\n" + task + "\n"
                                    + "Now you have " + taskList.size() + " task in your list!\n"
                                    + "-----------------------------------------\n";
                        } else {
                            deleteOutputMessage = "-----------------------------------------\n" +
                                    "Sure, I shall now delete the following task:\n" + task + "\n"
                                    + "Now you have " + taskList.size() + " tasks in your list!\n"
                                    + "-----------------------------------------\n";
                        }
                        System.out.println(deleteOutputMessage);
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "todo":
                    // need to be able to go through the rest of the string and add it inside
                    if (taskDetails.length() < 1) {
                        throw new EmptyTodoException();
                    }
                    ToDo todo = new ToDo(false, taskDetails);
                    taskList.add(todo);
                    String todoOutputMessage;
                    if (taskList.size() == 1) {
                        todoOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + todo + "\n"
                                + "Now you have " + taskList.size() + " task in your list!\n"
                                + "-----------------------------------------\n";
                    } else {
                        todoOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + todo + "\n"
                                + "Now you have " + taskList.size() + " tasks in your list!\n"
                                + "-----------------------------------------\n";
                    }
                    System.out.println(todoOutputMessage);
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "deadline": // go through taskDetails and find /by
                    if (taskDetails.length() < 1) {
                        throw new EmptyDeadlineException();
                    }
                    String[] deadlineDetails = taskDetails.split(" /by ", 2);
                    if (deadlineDetails.length <= 1) {
                        throw new NoByException();
                    }
                    Deadline deadline = new Deadline(false, deadlineDetails[0], deadlineDetails[1]);
                    taskList.add(deadline);
                    String deadlineOutputMessage;
                    if (taskList.size() == 1) {
                        deadlineOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + deadline + "\n"
                                + "Now you have " + taskList.size() + " task in your list!\n"
                                + "-----------------------------------------\n";
                    } else {
                        deadlineOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + deadline + "\n"
                                + "Now you have " + taskList.size() + " tasks in your list!\n"
                                + "-----------------------------------------\n";
                    }
                    System.out.println(deadlineOutputMessage);
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "event": // need to go through taskDetails and find /from and /to
                    if (taskDetails.length() <= 1) {
                        throw new EmptyEventException();
                    }
                    String[] eventDetails = taskDetails.split(" /from ", 2);
                    if (eventDetails.length <= 1) {
                        throw new NoFromException();
                    }
                    String[] eventDurationDetails = eventDetails[1].split(" /to ", 2);
                    if (eventDurationDetails.length <= 1) {
                        throw new NoToException();
                    }
                    Event event = new Event(false, eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]);
                    taskList.add(event);
                    String eventOutputMessage;
                    if (taskList.size() == 1) {
                        eventOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + event + "\n"
                                + "Now you have " + taskList.size() + " task in your list!\n"
                                + "-----------------------------------------\n";
                    } else {
                        eventOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + event + "\n"
                                + "Now you have " + taskList.size() + " tasks in your list!\n"
                                + "-----------------------------------------\n";
                    }
                    System.out.println(eventOutputMessage);
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (EmiyaException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(ui.Ui.EXIT_MESSAGE);

        myScannerObj.close();
    }
}
