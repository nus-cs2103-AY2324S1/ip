import types.Deadlines;
import types.Party;
import types.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class Utils {
    protected static ArrayList<Task> getDateList(LocalDate date, ArrayList<Task> lastList) {
        ArrayList<Task> thisDatesList = new ArrayList<>();

        try {
            lastList.forEach(x -> {
                if (x instanceof Deadlines) {
                    Deadlines y = (Deadlines) x;
                    if (y.isToday(date)) { thisDatesList.add(y); }
                } else if (x instanceof Party) {
                    Party y = (Party) x;
                    if (y.isToday(date)) { thisDatesList.add(y); }
                }
            });

        } catch (Exception e) {
            System.out.println(e);
        }
        return thisDatesList;
    }
}
