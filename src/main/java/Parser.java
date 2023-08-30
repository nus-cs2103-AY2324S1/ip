import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.GBotException;
import exceptions.TodoException;

import java.util.ArrayList;

public class Parser {
    enum Keyword {
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    public static void parse(String message, TaskList tasks) {
        if (message.isBlank()) {
            return;
        } else if (message.equals("list")) {
            tasks.listTasks();
        }

        Keyword prefix = Keyword.valueOf(message.split(" ")[0].toUpperCase());
        String str = message.substring(prefix.toString().length() + 1);

        switch (prefix) {
        case MARK:
            if (str.isBlank()) {
                Ui.showTaskNumberError();
            }
            tasks.markTask(str);
            break;
        case UNMARK:
            if (str.isBlank()) {
                Ui.showTaskNumberError();
            }
            tasks.unmarkTask(str);
            break;
        case TODO:
            if (str.isBlank()) {
                throw new TodoException();
            }
            tasks.addTodo(str);
            break;
        case DEADLINE:
            if (str.isBlank()) {
                throw new DeadlineException();
            }
            tasks.addDeadline(str);
            break;
        case EVENT:
            if (str.isBlank()) {
                throw new EventException();
            }
            tasks.addEvent(str);
            break;
        case DELETE:
            if (str.isBlank()) {
                Ui.showTaskNumberError();
            }
            tasks.deleteTask(str);
            break;
        default:
            throw new GBotException();
        }
    }

    public static void loadTaskFromFile(String taskInFile, ArrayList<Task> tasks) {
        String[] taskArr = taskInFile.split(" \\| ");
        switch (taskArr[0]) {
            case "T":
                tasks.add(new Todo(taskArr[1], taskArr[2]));
                break;
            case "D":
                tasks.add(new Deadline(taskArr[1], taskArr[2], taskArr[3]));
                break;
            case "E":
                tasks.add(new Event(taskArr[1], taskArr[2], taskArr[3], taskArr[4]));
                break;
            default:
                break;
        }
    }
}
