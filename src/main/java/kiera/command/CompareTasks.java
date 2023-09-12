package kiera.command;

import java.time.LocalDate;
import java.util.Comparator;

import kiera.Kiera;
import kiera.exception.KieraException;
import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;

public class CompareTasks implements Comparator<Task> {

    private String comparable;

    public CompareTasks(String comparable) {
        this.comparable = comparable;
    }

    @Override
    public int compare(Task t1, Task t2) throws KieraException {
        if (comparable.equals("date")) {
            LocalDate d1;
            LocalDate d2;
            if (t1 instanceof Event && t2 instanceof Event) {
                d1 = t1.getStartDate();
                d2 = t1.getStartDate();
            } else {
                assert t1 instanceof Deadline && t2 instanceof Deadline;
                d1 = t1.getDeadline();
                d2 = t2.getDeadline();
            }
            return d1.compareTo(d2);
        } else if (comparable.equals("time")) {
            assert t1 instanceof Event && t2 instanceof Event;
            return t1.getStartTime().compareTo(t2.getStartTime());
        }
        throw new KieraException("i cannot sort these!");
    }
}
