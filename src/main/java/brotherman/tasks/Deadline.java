package brotherman.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String time;
    private LocalDateTime timeInDateTime;

    public Deadline(String description, LocalDateTime timeInDateTime) {

        super(description);
        this.timeInDateTime = timeInDateTime;
    }

    public String type() {
        return "D";
    }

    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String text = timeInDateTime.format(formatter);
        return text;
    }


    @Override
    public String storeText() {
        return String.format("%s|%s|%s/by%s", this.type(), this.isDone, this.description, getTime());
    }

    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s(by:%s)", type, super.toString(), getTime() );
    }

}
