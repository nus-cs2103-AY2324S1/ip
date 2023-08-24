package Parser;

import TaskList.TaskList;

import java.util.Scanner;
import Exception.*;

public class Parser {

    private Scanner scanner;

    public Parser(java.io.InputStream in) {
        this.scanner = new Scanner(in);
    }

    public void wrapInHorizontalLines(String str) {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + str);
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static final String HORIZONTAL_LINE = "_".repeat(70);

    public static final String BOT_NAME = "\t \n" +
            "\t" + " _   __ _____ _   _ _____ _   _ \n" +
            "\t" +"| | / /|  ___| | | |_   _| \\ | |\n" +
            "\t" +"| |/ / | |__ | | | | | | |  \\| |\n" +
            "\t" +"|    \\ |  __|| | | | | | | . ` |\n" +
            "\t" +"| |\\  \\| |___\\ \\_/ /_| |_| |\\  |\n" +
            "\t" +"\\_| \\_/\\____/ \\___/ \\___/\\_| \\_/\n";

    public void hello() {
        String welcomeMessage = "Hello! I'm" + BOT_NAME + "\n\t" + "What can I do for you?";
        this.wrapInHorizontalLines(welcomeMessage);
    }

    public void bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        this.wrapInHorizontalLines(goodbyeMessage);
    }

    public TaskList getTaskList() {
        TaskList taskList = new TaskList();
        while (true) {
            String fullCommand = scanner.nextLine();
            String command = fullCommand.split(" ")[0];
            try {
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    wrapInHorizontalLines(taskList.list());
                } else if (command.equals("mark")) {
                    String[] splitted = fullCommand.split(" ");
                    if (splitted.length < 2) {
                        throw new TaskException("You need to specify which task is done!");
                    }
                    int toDoIndex = Integer.parseInt(splitted[1]);
                    wrapInHorizontalLines(taskList.mark(toDoIndex));
                } else if (command.equals("unmark")) {
                    String[] splitted = fullCommand.split(" ");
                    if (splitted.length < 2) {
                        throw new TaskException("You need to specify which task is not done!");
                    }
                    int toDoIndex = Integer.parseInt(splitted[1]);
                    wrapInHorizontalLines(taskList.unmark(toDoIndex));
                } else if (command.equals("todo")) {
                    String[] splitted = fullCommand.split(" ", 2);
                    if (splitted.length < 2) {
                        throw new ToDoException("The description of a todo cannot be empty.");
                    }
                    String name = splitted[1];
                    wrapInHorizontalLines(taskList.addToDo(name));
                } else if (command.equals("event")) {
                    String[] splitted = fullCommand.split(" ", 2);
                    if (splitted.length < 2) {
                        throw new EventException("The description of an event cannot be empty.");
                    }
                    String eventInfo = splitted[1];
                    String[] splitEventInfo = eventInfo.split(" /");
                    if (splitEventInfo.length < 3) {
                        throw new EventException("The description of the event should be complete.");
                    }
                    String name = splitEventInfo[0];
                    String startTime = splitEventInfo[1].replace("from ", "");
                    String endTime = splitEventInfo[2].replace("to ", "");
                    wrapInHorizontalLines(taskList.addEvent(name, startTime, endTime));
                } else if (command.equals("deadline")) {
                    String[] splitted = fullCommand.split(" ", 2);
                    if (splitted.length < 2) {
                        throw new DeadlineException("The description of a deadline cannot be empty.");
                    }
                    String deadlineInfo = splitted[1];
                    String[] splitDeadlineInfo = deadlineInfo.split(" /");
                    if (splitDeadlineInfo.length < 2) {
                        throw new DeadlineException("You should provide a deadline.");
                    }
                    String name = splitDeadlineInfo[0];
                    String deadline = splitDeadlineInfo[1].replace("by", "");
                    wrapInHorizontalLines(taskList.addDeadline(name, deadline));
                } else if (command.equals("delete")) {
                    String[] splitted = fullCommand.split(" ");
                    if (splitted.length < 2) {
                        throw new TaskException("You need to specify which task wants to be deleted!");
                    }
                    int deleteIndex = Integer.parseInt(splitted[1]);
                    wrapInHorizontalLines(taskList.delete(deleteIndex));
                } else {
                    throw new CommandException(" I'm sorry, but I don't know what that means :-(");
                }
            } catch (KevinException ke) {
                this.wrapInHorizontalLines(ke.getMessage());
            }

        }
        return taskList;
    }
}
