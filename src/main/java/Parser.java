import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Parser {
    private ArrayList<Task> myList;
    private Ui Ui;
    private Storage storage;
    private Scanner myScanner;
    Parser(ArrayList<Task> myList, Ui Ui, Storage storage, Scanner myScanner) {
        this.myList = myList;
        this.Ui = Ui;
        this.storage = storage;
        this.myScanner = myScanner;
    }

    public void parseInput(String inValue){
        Task item;
        switch(inValue) {
        case "bye":
            Ui.goodbye();
            storage.saveTasksToFile(myList);
            System.exit(0);
            return;

        case "list":
            Ui.tasksInList(myList);
            break;

        case "mark":
            int number = myScanner.nextInt();
            item = myList.get(number-1);
            item.set();
            Ui.taskDone(item);
            storage.saveTasksToFile(myList);
            break;

        case "unmark":
            int numero = myScanner.nextInt();
            item = myList.get(numero-1);
            item.unset();
            Ui.taskUndone(item);
            storage.saveTasksToFile(myList);
            break;

        case "delete":
            int numbero = myScanner.nextInt();
            item = myList.get(numbero - 1);
            myList.remove(numbero-1);
            Ui.taskDelete(item, myList);
            storage.saveTasksToFile(myList);
            break;

        case "todo":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                //throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                Ui.showError("todo");
                break;
            }
            ToDo t =  new ToDo(inValue);
            myList.add(t);
            Ui.taskAdd(t, myList);
            storage.saveTasksToFile(myList);
            break;
        case "deadline":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                Ui.showError("deadline");
                break;
                //throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

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
            myList.add(d);
            Ui.taskAdd(d, myList);
            storage.saveTasksToFile(myList);
            break;
        case "event":
            inValue = myScanner.nextLine();
            if (inValue.length() != 0){
                inValue = inValue.substring(1);
            } else {
                Ui.showError("event");
                break;
                //throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            String[] to_Split = inValue.split(" /from ");
            String[] second_Split = to_Split[1].split(" /to ");
            Event e = new Event(to_Split[0], second_Split[0], second_Split[1]);
            myList.add(e);
            Ui.taskAdd(e, myList);
            storage.saveTasksToFile(myList);
            break;

        default:
            inValue += myScanner.nextLine();
            Ui.unrecognisedCommand();
            break;

        }
    }
}
