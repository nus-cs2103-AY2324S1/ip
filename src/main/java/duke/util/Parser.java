package duke.util;

import duke.Duke;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.IOException;

public class Parser {
    private static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    private TaskList taskList;
    private Ui ui;
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, FIND, DEADLINE, TODO, EVENT, UNKNOWN
    }

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    private void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    private String getCommand (String line) {
        return line.trim().split("\\s+")[0].toUpperCase();
    }

    public void parseInput(String userInput)
            throws IOException, EmptyDescriptionException, InvalidCommandException {
        try {
            String firstWord = getCommand(userInput);
            String[] words = userInput.split("\\s+"); // Split input by space, put into array
            Command command; //Use enum
            try {
                command = Command.valueOf(firstWord);
            } catch (IllegalArgumentException e) {
                command = Command.UNKNOWN;
            }

            //A-Enum: Use switch-case instead of if-else for neater code
            switch (command) {
            case BYE:
                ui.farewell();
                Duke.isFinished = true;
                break;
            case LIST:
                taskList.listAllTasks();
                break;
            case MARK:
                int taskIndex = Integer.parseInt(words[1]) - 1;
                taskList.markTask(taskIndex);
                break;
            case UNMARK:
                taskIndex = Integer.parseInt(words[1]) - 1; //Same variable name taskIndex as above
                taskList.unmarkTask(taskIndex);
                break;
            case DELETE:
                taskList.deleteTask(userInput);
                break;
            case FIND:
                String keyword = userInput.trim().replaceFirst("find", "").trim();
                taskList.findTask(keyword);
                break;
            case DEADLINE:
                Deadline.handleDeadlineTask(userInput);
                break;
            case TODO:
                Todo.handleTodoTask(userInput);
                break;
            case EVENT:
                Event.handleEventTask(userInput);
                break;
            case UNKNOWN:
                throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (IOException e) {
            System.err.println(HORIZONTAL_LINE + "\n" + e.toString() + HORIZONTAL_LINE);
        } catch (EmptyDescriptionException e) {
            e.printExceptionMessage();
        } catch (InvalidCommandException e) {
            e.printExceptionMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("     Please enter valid Integer index!");
            System.out.printf("     You currently have %d tasks", taskList.taskList.size());
        } catch (Exception e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("     Very Invalid command! Please enter valid commands");
            System.out.println(HORIZONTAL_LINE);
        }
    }

}
