package emiya;

import java.util.Scanner;

import emiya.emiyaexception.EmiyaException;
import emiya.emiyaexception.EmptyDeadlineException;
import emiya.emiyaexception.EmptyEventException;
import emiya.emiyaexception.EmptyTodoException;
import emiya.emiyaexception.OutOfListBoundsException;
import emiya.emiyaexception.UnknownCommandException;
import emiya.parser.Parser;
import emiya.storage.Storage;
import emiya.task.Deadline;
import emiya.task.Event;
import emiya.task.Task;
import emiya.task.TaskList;
import emiya.task.ToDo;
import emiya.ui.Ui;

public class Emiya {
    // must remove static at the end
    private String dirName;
    private String fileName;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;
    private Ui ui;

    public Emiya(String dirName, String fileName) {
        this.dirName = dirName;
        this.fileName = fileName;
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
        ui = new Ui();

    }

    public void run() {
        // shift try-catch to emiya.storage.Storage; maybe no need shift?
        try {
            storage.createDirectory(dirName);
            storage.createFileInDirectory(fileName, dirName);
            storage.fillListWithFileContent(taskList, storage.fileContents(fileName, dirName));
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

                // kiv shift where
                if (input.equals("I am the bone of my sword")) {
                    System.out.println(Ui.UBW);
                    continue;
                }

                // kiv shift where
                if (input.equals("dead")) {
                    System.out.println(Ui.DEAD);
                    continue;
                }

                // shift to TaskList: DONE (includes ListPointer)
                // Method to list out all items in list. If the list is empty, throws exception
                // and informs user to add items to list.
                if (input.equals("list")) {
                    taskList.list();
                    continue;
                }

                Integer[] position = new Integer[] {null};
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
                        taskList.get(position[0] - 1).setMarked();
                        System.out.println(ui.markedMessage(position[0], taskList));
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, fileName, dirName);
                    break;
                case "unmark":
                    if (position[0] != null) {
                        if (position[0] <= 0 || position[0] > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        taskList.get(position[0] - 1).setUnmarked();
                        System.out.println(ui.unmarkedMessage(position[0], taskList));
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, fileName, dirName);
                    break;
                case "delete":
                    if (position[0] != null) {
                        if (position[0] <= 0 || position[0] > taskList.size()) {
                            throw new OutOfListBoundsException();
                        }
                        Task task = taskList.get(position[0] - 1);
                        taskList.remove(task);
                        if (taskList.size() == 1) {
                            System.out.println(ui.deletedSingularMessage(task, taskList));
                        } else {
                            System.out.println(ui.deletedPluralMessage(task, taskList));
                        }
                    } else {
                        throw new UnknownCommandException();
                    }
                    storage.writeToFileFromTaskList(taskList, fileName, dirName);
                    break;
                case "todo":
                    // need to be able to go through the rest of the string and add it inside
                    if (taskDetails.equals("")) {
                        throw new EmptyTodoException();
                    }
                    ToDo todo = new ToDo(false, taskDetails);
                    taskList.add(todo);
                    if (taskList.size() == 1) {
                        System.out.println(ui.addedSingularMessage(todo, taskList));
                    } else {
                        System.out.println(ui.addedPluralMessage(todo, taskList));
                    }
                    storage.writeToFileFromTaskList(taskList, fileName, dirName);
                    break;
                case "deadline": // go through taskDetails and find /by
                    if (taskDetails.equals("")) {
                        throw new EmptyDeadlineException();
                    }
                    String[] deadlineDetails = parser.parseForDeadline(taskDetails);
                    Deadline deadline = new Deadline(false, deadlineDetails[0], deadlineDetails[1]);
                    taskList.add(deadline);
                    if (taskList.size() == 1) {
                        System.out.println(ui.addedSingularMessage(deadline, taskList));
                    } else {
                        System.out.println(ui.addedPluralMessage(deadline, taskList));
                    }
                    storage.writeToFileFromTaskList(taskList, fileName, dirName);
                    break;
                case "event": // need to go through taskDetails and find /from and /to
                    if (taskDetails.equals("")) {
                        throw new EmptyEventException();
                    }
                    String[] parsedEventDetails = parser.parseForEvent(taskDetails);
                    Event event = new Event(false, parsedEventDetails[0], parsedEventDetails[1], parsedEventDetails[2]);
                    taskList.add(event);
                    if (taskList.size() == 1) {
                        System.out.println(ui.addedSingularMessage(event, taskList));
                    } else {
                        System.out.println(ui.addedPluralMessage(event, taskList));
                    }
                    storage.writeToFileFromTaskList(taskList, fileName, dirName);
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

    public static void main(String[] args) {
        new Emiya("data", "emiya.txt").run();
    }
}
