package emiya;

import emiya.emiyaexception.*;

import emiya.logic.Logic;
import emiya.parser.Parser;
import emiya.storage.Storage;
import emiya.task.*;
import emiya.ui.Ui;

import java.util.Scanner;

import static emiya.logic.Logic.enumContainsKeyword;

public class Emiya {
    // must remove static at the end
    private String dirName;
    private String fileName;
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();
    private static Ui Ui = new Ui();

    public static void main(String[] args) {

        // shift try-catch to emiya.storage.Storage; maybe no need shift?
        try {
            storage.createDirectory("data");
            storage.createFileInDirectory("emiya.txt", "data");
            storage.fillListWithFileContent(taskList, storage.fileContents("emiya.txt", "data"));
        } catch (EmiyaException e) {
            System.out.println(e.getMessage());
        }

        // keep
        Scanner myScannerObj = new Scanner(System.in);
        System.out.println(emiya.ui.Ui.WELCOME_MESSAGE);

        while (true) {
            try {
                // nextLine is blocking, so can have this here
                String input = myScannerObj.nextLine();

                // Terminates the program by exiting the while loop.
                if (input.equals("bye")) {
                    break;
                }

                //shift to parser : DONE
                // String[] partsOfInput = input.split("\\s+", 2);

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

                // shift to TaskList: DONE (includes ListPointer)
                // Method to list out all items in list. If the list is empty, throws exception
                // and informs user to add items to list.
                if (input.equals("list")) {
                    taskList.list();
                    continue;
                }

                // shift to parser : DONE
                // If a given input is only one word long (partsOfInput < 2 ) and not a reserved keyword, will throw exception
                // to inform them that an unknown command was received.
//                if (partsOfInput.length < 2 && !enumContainsKeyword(partsOfInput[0].toUpperCase())) {
//                    throw new UnknownCommandException();
//                }

                // shift to parser : DONE
                // Used to handle the case of when mark and unmark commands are used.
                // If the commands are used, will parse String into Integer type.
//                String typeOfTaskOld = partsOfInput[0];
//                String taskDetailsOld = "";
//                if (partsOfInput.length > 1) {
//                    taskDetailsOld = partsOfInput[1];
//                }
                Integer[] position = new Integer[] {null};

                // shift to parser : DONE
//                if (Logic.isNumeric(taskDetailsOld)) {
//                    position = Integer.parseInt(taskDetailsOld);
//                }

                // WIP CODE:
                String[] parsedInput = parser.parseToRemoveUnknownCommands(position, input);
                String typeOfTask = parsedInput[0];
                String taskDetails = "";
                if (parsedInput.length > 1) {
                    taskDetails = parsedInput[1];
                }

                switch (typeOfTask) {
                case "mark":
                    if (position[0] != null) {
                        if (position[0] <= 0 || position[0] > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        taskList.get(position[0]-1).setMarked();
                        System.out.println(Ui.markedMessage(position[0], taskList));
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "unmark":
                    if (position[0] != null) {
                        if (position[0] <= 0 || position[0] > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        taskList.get(position[0]-1).setUnmarked();
                        System.out.println(Ui.unmarkedMessage(position[0], taskList));
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "delete":
                    if (position[0] != null) {
                        if (position[0] <= 0 || position[0] > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        Task task = taskList.get(position[0]-1);
                        taskList.remove(task);                        // String deleteOutputMessage;
                        if (taskList.size() == 1) {
//                            deleteOutputMessage = "-----------------------------------------\n" +
//                                    "Sure, I shall now delete the following task:\n" + task + "\n"
//                                    + "Now you have " + taskList.size() + " task in your list!\n"
//                                    + "-----------------------------------------\n";
                            System.out.println(Ui.deletedSingularMessage(task, taskList));
                        } else {
//                            deleteOutputMessage = "-----------------------------------------\n" +
//                                    "Sure, I shall now delete the following task:\n" + task + "\n"
//                                    + "Now you have " + taskList.size() + " tasks in your list!\n"
//                                    + "-----------------------------------------\n";
                            System.out.println(Ui.deletedPluralMessage(task, taskList));
                        }
                        // System.out.println(deleteOutputMessage);
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "todo":
                    // need to be able to go through the rest of the string and add it inside
                    if (taskDetails.equals("")) {
                        throw new EmptyTodoException();
                    }
                    ToDo todo = new ToDo(false, taskDetails);
                    taskList.add(todo);
                    //String todoOutputMessage;
                    if (taskList.size() == 1) {
//                        todoOutputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this emiya.task to the list:\n" + todo + "\n"
//                                + "Now you have " + taskList.size() + " emiya.task in your list!\n"
//                                + "-----------------------------------------\n";
                        System.out.println(Ui.addedSingularMessage(todo, taskList));
                    } else {
//                        todoOutputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this emiya.task to the list:\n" + todo + "\n"
//                                + "Now you have " + taskList.size() + " tasks in your list!\n"
//                                + "-----------------------------------------\n";
                        System.out.println(Ui.addedPluralMessage(todo, taskList));
                    }
                    //System.out.println(todoOutputMessage);
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "deadline": // go through taskDetails and find /by
                    if (taskDetails.equals("")) {
                        throw new EmptyDeadlineException();
                    }
                    // needs to be shifted to Parser: DONE
//                    String[] deadlineDetailsOld = taskDetails.split(" /by ", 2);
//                    if (deadlineDetailsOld.length <= 1) {
//                        throw new NoByException();
//                    }
                    // WIP CODE:
                    String[] deadlineDetails = parser.parseForDeadline(taskDetails);
                    Deadline deadline = new Deadline(false, deadlineDetails[0], deadlineDetails[1]);
                    taskList.add(deadline);
                    // String deadlineOutputMessage;
                    if (taskList.size() == 1) {
//                        deadlineOutputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this emiya.task to the list:\n" + deadline + "\n"
//                                + "Now you have " + taskList.size() + " emiya.task in your list!\n"
//                                + "-----------------------------------------\n";
                        System.out.println(Ui.addedSingularMessage(deadline, taskList));
                    } else {
//                        deadlineOutputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this emiya.task to the list:\n" + deadline + "\n"
//                                + "Now you have " + taskList.size() + " tasks in your list!\n"
//                                + "-----------------------------------------\n";
                        System.out.println(Ui.addedPluralMessage(deadline, taskList));
                    }
                    // System.out.println(deadlineOutputMessage);
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                case "event": // need to go through taskDetails and find /from and /to
                    if (taskDetails.equals("")) {
                        throw new EmptyEventException();
                    }

                    // needs to be shifted to Parser:
//                    String[] eventDetails = taskDetails.split(" /from ", 2);
//                    if (eventDetails.length <= 1) {
//                        throw new NoFromException();
//                    }
//                    String[] eventDurationDetails = eventDetails[1].split(" /to ", 2);
//                    if (eventDurationDetails.length <= 1) {
//                        throw new NoToException();
//                    }

                    //WIP CODE:
                    String[] parsedEventDetails = parser.parseForEvent(taskDetails);
                    Event event = new Event(false, parsedEventDetails[0], parsedEventDetails[1], parsedEventDetails[2]);
                    taskList.add(event);
                    // String eventOutputMessage;
                    if (taskList.size() == 1) {
//                        eventOutputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this emiya.task to the list:\n" + event + "\n"
//                                + "Now you have " + taskList.size() + " emiya.task in your list!\n"
//                                + "-----------------------------------------\n";
                        System.out.println(Ui.addedSingularMessage(event, taskList));
                    } else {
//                        eventOutputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this emiya.task to the list:\n" + event + "\n"
//                                + "Now you have " + taskList.size() + " tasks in your list!\n"
//                                + "-----------------------------------------\n";
                        System.out.println(Ui.addedPluralMessage(event, taskList));
                    }
                    // System.out.println(eventOutputMessage);
                    storage.writeToFileFromTaskList(taskList, "emiya.txt", "data");
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (EmiyaException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(emiya.ui.Ui.EXIT_MESSAGE);

        myScannerObj.close();
    }
}
