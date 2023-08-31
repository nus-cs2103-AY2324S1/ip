import exception.*;
import taskClasses.*;

import java.util.Scanner;

/**
 * Parser class for handling various task-related commands.
 */
public class Parser {

    /**
     * Enum to represent different types of commands.
     */
    enum Command {
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        MARK,
        UNMARK,
        LIST,
        DELETE,
        CLEAR
    }


    /**
     * Handles the actions of different commands, like adding tasks, deleting them, or marking them as done.
     */
    public static void run(Ui ui, Storage storage, TaskList taskList) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            ui.newDashedLine();
            String[] parts = input.split(" ");
            String[] details = input.split("/");
            Command command = null;

            try{
                try {
                    command = Command.valueOf(parts[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new InvalidInputException();
                }

                if (command != null) {
                    int number = 0;
                    if (command == Command.MARK
                            || command == Command.UNMARK
                            || command == Command.DELETE){
                        try {
                            number = Integer.parseInt(parts[1]);
                            if (number < 0 || number > taskList.getTasksCount()) {
                                throw new InvalidListNumberException();
                            }
                        } catch (Exception e) {
                            throw new InvalidListNumberException();
                        }
                    }
                    switch (command) {
                        case BYE:
                            ui.printBye();
                            break;
                        case DELETE:
                            String content = taskList.deleteTask(number);
                            ui.deleteTask(content);
                            ui.printTaskCount(taskList.getTasksCount());
                            break;
                        case LIST:
                            ui.printList(taskList);
                            break;

                        case UNMARK:
                            taskList.markTaskAsNotDone(number);
                            ui.printTaskMarkAsNotDone(taskList.getStatusAndDescription(number));
                            break;

                        case MARK:
                            taskList.markTaskAsDone(number);
                            ui.printTaskMarkAsDone(taskList.getStatusAndDescription(number));
                            break;

                        case TODO:
                            if (details[0].trim().length() == 4) {
                                throw new InvalidToDoException();
                            } else {
                                taskList.addToDoToList(false,
                                        details[0].substring(5).trim());
                            }
                            break;

                        case DEADLINE:
                            if (details[0].trim().length() == 8) {
                                throw new InvalidDeadlineException();
                            } else {
                                taskList.addDeadlineToList(
                                        false,
                                        details[0].split("/")[0].substring(9),
                                        input.split("by")[1].trim());
                            }
                            break;

                        case EVENT:
                            if (details[0].trim().length() == 5) {
                                throw new InvalidEventException();
                            } else {
                                taskList.addEventToList(
                                       false,
                                        details[0].split("/")[0].substring(6),
                                        input.split("from")[1].split("/to")[0].trim(),
                                        input.split("to")[1].trim());
                            }
                            break;
                        case CLEAR:
                            taskList.clear();
                            break;

                        default:
                            break;
                    }
                }
                if (command == Command.BYE) {
                    storage.writeToDB(taskList);
                    break;
                }
            } catch (DukeException e){
                System.out.println(e);
            }
            ui.newDashedLine();

        }

        scanner.close();
    }
}
