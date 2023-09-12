package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ParserGUI {
    private final TaskList tasks;
    private final UiGUI UiGUI;
    private final Storage storage;
    private final Scanner myScanner;

    /**
     * Creates a Parser
     *
     * @param tasks     the TaskList
     * @param UiGUI        the ui object
     * @param storage   the storage object
     * @param myScanner the created Scanner object
     */
    ParserGUI(TaskList tasks, UiGUI UiGUI, Storage storage, Scanner myScanner) {
        this.tasks = tasks;
        this.UiGUI = UiGUI;
        this.storage = storage;
        this.myScanner = myScanner;
    }

    /**
     * Parses the input given by the user
     *
     * @param inValue the initials of the string given by user
     */
    public String parseInput(String inValue) {
        Task item;
        switch (inValue) {
        case "bye":
            storage.saveTasksToFile(tasks);
            //System.exit(0);
            return UiGUI.goodbye();

        case "list":
            return UiGUI.tasksInList(this.tasks);
            //break;

        case "mark":
            int number = myScanner.nextInt();
            item = tasks.get(number);
            item.set();
            storage.saveTasksToFile(tasks);
            return UiGUI.taskDone(item);
            //break;

        case "unmark":
            int numero = myScanner.nextInt();
            item = tasks.get(numero);
            item.unset();
            storage.saveTasksToFile(tasks);
            return UiGUI.taskUndone(item);
            //break;

        case "delete":
            int numbero = myScanner.nextInt();
            item = tasks.get(numbero);
            tasks.delete(numbero);
            storage.saveTasksToFile(tasks);
            return UiGUI.taskDelete(item, tasks);
            //break;

        case "todo":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0) {
                inValue = inValue.substring(1);
            } else {
                //throw new duke.DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                return UiGUI.showError("todo");
                //break;
            }
            ToDo t = new ToDo(inValue);
            tasks.add(t);
            storage.saveTasksToFile(tasks);
            return UiGUI.taskAdd(t, tasks);
            //break;
        case "deadline":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0) {
                inValue = inValue.substring(1);
            } else {
                return UiGUI.showError("deadline");
                //break;
                //throw new duke.DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

            }
            String[] toBeSplit = inValue.split(" /by ");
            Deadline d;
            if (toBeSplit[1].contains(" ")) {
                //date + time is present
                d = new Deadline(toBeSplit[0], LocalDateTime.parse(toBeSplit[1], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")));
            } else {
                d = new Deadline(toBeSplit[0], LocalDate.parse(toBeSplit[1], DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }
            tasks.add(d);
            storage.saveTasksToFile(tasks);
            return UiGUI.taskAdd(d, tasks);
            //break;
        case "event":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0) {
                inValue = inValue.substring(1);
            } else {
                return UiGUI.showError("event");
                //break;
                //throw new duke.DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] to_Split = inValue.split(" /from ");
            String[] second_Split = to_Split[1].split(" /to ");
            Event e = new Event(to_Split[0], second_Split[0], second_Split[1]);
            tasks.add(e);
            storage.saveTasksToFile(tasks);
            return UiGUI.taskAdd(e, tasks);
            //break;
        case "find":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0) {
                inValue = inValue.substring(1);
            } else {
                return UiGUI.showError("find");
                //break;
                //throw new duke.DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            return UiGUI.printMatchingTasks(tasks, inValue);
            //break;

        default:

            inValue += myScanner.nextLine();
            return UiGUI.unrecognisedCommand();
            //break;

        }
    }
}
