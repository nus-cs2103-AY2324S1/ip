package tasks;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The deadline class represents a deadline. It has a name and a deadline
 */
@JsonTypeName("tasks.Deadline")
public class Deadline extends Task {

    public static final String TASK_TYPE = "D";

    private LocalDate deadDate;

    /**
     * Creates a new Deadline Task
     * @param desc users description of the task
     * @param deaddate the task deadline
     */
    public Deadline(String desc, LocalDate deaddate) {
        super(desc);
        this.deadDate = deaddate;
    }

    public Deadline() {
        super("");
    }

    public String getDeadDate() {
        return this.deadDate.toString();
    }

}
