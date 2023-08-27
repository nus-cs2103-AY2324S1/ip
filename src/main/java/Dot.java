import errors.DotException;
import errors.TaskError;
import parser.Parser;
import storage.*;
import tasks.*;
import ui.Ui;
import validation.Validation;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Dot {
    private final String dataFilePathname;
    private final TaskList dotTaskList;

    public Dot(String dataFilePathname, int maxSize) {
        this.dataFilePathname = dataFilePathname;
        this.dotTaskList = TaskList.taskListFromArrayList(maxSize,
                Storage.getTasks(new File(dataFilePathname)));
    }

    // Inspired by tutorial sheet
    public void run() {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);

        boolean isOngoing = true;
        while (isOngoing) {
            try {
                String input = sc.nextLine();
                switch (input) {
                    case "bye":
                        isOngoing = false;
                        break;
                    case "list":
                        dotTaskList.list();
                        break;
                    default:
                        // Note: To support more than 3 kinds of tasks, we can code a robust function and follow
                        // a standardised format.
                        if (Validation.isValidCommand(input, "mark")) {
                            int position = Validation.intIfValidCommandSpaceNumber(input, TaskError.ERR_USING_MARK);
                            dotTaskList.markTask(position - 1, true);
                            dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
                            break;
                        } else if (Validation.isValidCommand(input, "unmark")) {
                            int position = Validation.intIfValidCommandSpaceNumber(input, TaskError.ERR_USING_UNMARK);
                            dotTaskList.markTask(position - 1, false);
                            dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
                            break;
                        } else if (Validation.isValidCommand(input, "todo")) {
                            String restOfString = Validation.descIfValidCommandSpaceDesc(input, "todo",
                                    "task description", TaskError.ERR_USING_TODO);
                            Task newTodoTask = new Todo(restOfString);
                            dotTaskList.addTask(newTodoTask);
                            dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
                            break;
                        } else if (Validation.isValidCommand(input, "deadline")) {
                            String[] args = Validation.argsIfValidDeadlineFormat(input);
                            Task newDeadlineTask = new Deadline(args[0], args[1]);
                            dotTaskList.addTask(newDeadlineTask);
                            dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
                            break;
                        } else if (Validation.isValidCommand(input, "event")) {
                            String[] args = Validation.argsIfValidEventFormat(input);
                            Task newEventTask = new Event(args[0], args[1], args[2]);
                            dotTaskList.addTask(newEventTask);
                            dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
                            break;
                        } else if (Validation.isValidCommand(input, "delete")) {
                            int position = Validation.intIfValidCommandSpaceNumber(input, TaskError.ERR_DELETING_TASK);
                            dotTaskList.deleteTask(position - 1);
                            dotTaskList.saveTaskListToStorage(new File(this.dataFilePathname));
                            break;
                        } else if (Validation.isValidCommand(input, "whatsgoingon")) {
                            String restOfString = Validation.descIfValidCommandSpaceDesc(input,
                                    "whatsgoingon", "date", TaskError.ERR_USING_WHATSGOINGON);
                            if (!(Validation.isValidDate(restOfString))) {
                                throw new DotException("Incorrect format for data, use dd/MM/yyyy",
                                        TaskError.ERR_USING_WHATSGOINGON);
                            }
                            LocalDateTime parsedLocalDateTime = Parser.parseDateInputIntoDateTime(restOfString);
                            dotTaskList.listAllTasksFallingOnDate(parsedLocalDateTime);
                            break;
                        } else if (input.equals("help")) {
                            Ui.displayHelpMessage();
                            break;
                        }
                        throw new DotException("Unknown command.", TaskError.ERR_READING_COMMAND);
                }
            } catch (DotException e) {
                // For checked exception
                e.handleError();
            }
        }
        Ui.goodbye();
    }
    public static void main(String[] args) {
        Dot dotInstance = new Dot("src/main/java/data/dot.txt", 100);
        dotInstance.run();
    }
}
