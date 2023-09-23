package penguin;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * TaskList is a list of Tasks.
 *
 */
public class TaskList {
    protected List<Task> list;

    /**
     * Constructor of List.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }
    /**
     * Returns information about all tasks in the list for output to the user.
     *
     * @return Information about all tasks in the list in user-output format.
     */
    public String printList() {
        String output = "I'm remembering the following tasks...\n";
        for (int i=1; i<=this.list.size(); i++) {
            output += i + ".";
            output += this.list.get(i-1).getDisplay() + "\n";
        }
        return output;
    }

    /**
     * Returns information about all tasks in the list whose name contains the given string.
     *
     * @param match String to search for in the list of tasks.
     * @return Information about all matching tasks in the list in user-output format.
     */
    public String findInList(String match) {
        String output = "Honk, I found the following matching tasks...\n";
        for (int i=1; i<=this.list.size(); i++) {
            if (this.list.get(i-1).name.contains(match)) {
                output += i + ".";
                output += this.list.get(i-1).getDisplay() + "\n";
            }
        }
        return output;
    }

    /**
     * Returns information about all events occurring on the given date.
     *
     * @param todayString Date to search for, in YYYY-MM-DD format.
     * @return Information about all events occurring on the given date, in user-output format.
     */
    public String findSchedule(String todayString) {
        String output = "Honk, these are the events you have scheduled for this date...\n";
        LocalDate today = LocalDate.parse(todayString);
        for (int i=1; i<=this.list.size(); i++) {
            if (this.list.get(i-1) instanceof Event) {
                Event event = (Event) this.list.get(i-1);
                if (event.from.compareTo(today) <= 0 && event.to.compareTo(today) >= 0) {
                    output += i + ".";
                    output += this.list.get(i-1).getDisplay() + "\n";
                }

            }
        }
        return output;
    }
}
