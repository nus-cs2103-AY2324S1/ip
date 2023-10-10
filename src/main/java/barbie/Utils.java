package barbie;

import java.time.LocalDate;
import java.util.ArrayList;

import barbie.types.Deadlines;
import barbie.types.Party;
import barbie.types.Task;


/**
 * Abstracts any helper functions that will be used in the main function.
 */
public class Utils {

    /**
     * Retrieves the list of Tasks that should be done today.
     *
     * @param date date of the Tasks to retrieve
     * @param lastList latest list of Tasks
     * @return the list of Tasks that should be done today
     */
    public static ArrayList<Task> getDateList(LocalDate date, ArrayList<Task> lastList) {
        ArrayList<Task> thisDatesList = new ArrayList<>();
        lastList.forEach(x -> {
            if (x instanceof Deadlines) {
                Deadlines y = (Deadlines) x;
                if (y.isToday(date)) {
                    thisDatesList.add(y);
                }
            } else if (x instanceof Party) {
                Party y = (Party) x;
                if (y.isToday(date)) {
                    thisDatesList.add(y);
                }
            }
        });
        return thisDatesList;
    }
}
