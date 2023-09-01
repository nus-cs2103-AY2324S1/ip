package deterministicparrot;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
abstract public class Task {
    private String name;
    private boolean isDone;
    Task(String description){
        this.name = description;
        this.isDone = false;
    }
    public String getName(){
        return this.name;
    }
    public boolean getIsDone(){
        return this.isDone;
    }
    public void markAsDone(){
        this.isDone = true;
    }
    public void markAsUndone(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}

