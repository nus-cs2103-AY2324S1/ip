package duke;

import java.time.LocalDateTime;

public class Deadline extends Task{

    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline){
        super(description);
        this.deadline = deadline;
        this.tag = Tag.D;

    }
    public String changeFormat(LocalDateTime deadline){
        return deadline.getMonth().toString() + " " + deadline.getDayOfMonth() + " " +
                deadline.getYear() + ", " + deadline.toLocalTime();
    }

    @Override
    public String toString(){
        return String.format("%s (by: %s)", description, changeFormat(deadline));
    }
}
