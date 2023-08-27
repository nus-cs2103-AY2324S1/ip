import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskList obj = new TaskList();
        Scanner sc = new Scanner(System.in);
        try {
            obj.loadFile();
            UI.display_lines();
            UI.displayGreeting();
            UI.display_lines();
            outer:
            do {
                String s = sc.nextLine();
                UI.display_lines();
                TaskType type = TaskType.valueOf(s.split(" ")[0].toUpperCase());
                switch (type) {
                    case BYE:
                        UI.displayBye();
                        UI.display_lines();
                        break outer;
                    case LIST:
                        obj.displayList();
                        break;
                    case MARK:
                        System.out.println(obj.markTask(s));
                        break;
                    case UNMARK:
                        System.out.println(obj.unmarkTask(s));
                        break;
                    case DELETE:
                        System.out.println(obj.deleteTask(s));
                        System.out.println(obj.displayListSum());
                        break;
                    case TODO:
                        System.out.println(obj.addToDo(s));
                        System.out.println(obj.displayListSum());
                        break;
                    case DEADLINE:
                        System.out.println(obj.addDeadline(s));
                        System.out.println(obj.displayListSum());
                        break;
                    case EVENT:
                        System.out.println(obj.addEvent(s));
                        System.out.println(obj.displayListSum());
                        break;
                    case SCHEDULE:
                        System.out.println(obj.showSchedule(s));
                        break;
                    default:
                        throw new DukeUnknownTaskException();
                }
                UI.display_lines();
                obj.writeToFile();
            } while (true);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            UI.display_lines();
        } catch (DateTimeException e) {
            System.out.println("\tDate and Time is not in correct format.");
            UI.display_lines();
        }
    }
}
