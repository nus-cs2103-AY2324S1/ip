package crackerpackage.tasks;

import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate deadline;
    public Deadline(String s) throws EmptyDescriptionException, IllegalFormatException {
        super(s.split("/by",0)[0].trim());
        if(!s.contains("/by")){
            throw new IllegalFormatException("Missing \"/by\" after task description");
        }
        try{
            this.deadline = LocalDate.parse(s.split("/by",0)[1].trim());
        } catch (Exception e){
            throw new IllegalFormatException("please enter date in yyyy-mm-dd format");
        }

    }
    public Deadline(String desc, String deadline) throws EmptyDescriptionException {
        super(desc);
        this.deadline = LocalDate.parse(deadline);
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + getDeadline() +")";
    }
    public String getDeadline(){ return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));}
}