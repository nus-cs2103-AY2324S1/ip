package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Parser {
    private TaskList tasks;
    private Ui Ui;
    private Storage storage;
    private Scanner myScanner;
    Parser(TaskList tasks, Ui Ui, Storage storage, Scanner myScanner) {
        this.tasks = tasks;
        this.Ui = Ui;
        this.storage = storage;
        this.myScanner = myScanner;
    }

    public void parseInput(String inValue){
        Task item;
        switch(inValue) {
        case "bye":
            Ui.goodbye();
            storage.saveTasksToFile(tasks);
            System.exit(0);
            return;

        case "list":
            Ui.tasksInList(tasks);
            break;

        case "mark":
            int number = myScanner.nextInt();
            item = tasks.get(number);
            item.set();
            Ui.taskDone(item);
            storage.saveTasksToFile(tasks);
            break;

        case "unmark":
            int numero = myScanner.nextInt();
            item = tasks.get(numero);
            item.unset();
            Ui.taskUndone(item);
            storage.saveTasksToFile(tasks);
            break;

        case "delete":
            int numbero = myScanner.nextInt();
            item = tasks.get(numbero);
            tasks.delete(numbero);
            Ui.taskDelete(item, tasks);
            storage.saveTasksToFile(tasks);
            break;

        case "todo":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                //throw new duke.DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                Ui.showError("todo");
                break;
            }
            ToDo t =  new ToDo(inValue);
            tasks.add(t);
            Ui.taskAdd(t, tasks);
            storage.saveTasksToFile(tasks);
            break;
        case "deadline":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                Ui.showError("deadline");
                break;
                //throw new duke.DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

            }
            String[] toBeSplit = inValue.split(" /by ");
            Deadline d;
            if (toBeSplit[1].contains (" ")){
                //date + time is present
                d = new Deadline(toBeSplit[0], LocalDateTime.parse(toBeSplit[1], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")));
            }
            else{
                d = new Deadline(toBeSplit[0], LocalDate.parse(toBeSplit[1], DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            }
            tasks.add(d);
            Ui.taskAdd(d, tasks);
            storage.saveTasksToFile(tasks);
            break;
        case "event":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                Ui.showError("event");
                break;
                //throw new duke.DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] to_Split = inValue.split(" /from ");
            String[] second_Split = to_Split[1].split(" /to ");
            Event e = new Event(to_Split[0], second_Split[0], second_Split[1]);
            tasks.add(e);
            Ui.taskAdd(e, tasks);
            storage.saveTasksToFile(tasks);
            break;
        case "find":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                Ui.showError("find");
                break;
                //throw new duke.DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            Ui.printMatchingTasks(tasks, inValue);

        default:
            inValue += myScanner.nextLine();
            Ui.unrecognisedCommand();
            break;

        }
    }
}
