package chatbot.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String date;
    protected LocalDateTime formattedDate;

    public Deadline(String description, String date){
        super(description);
        this.date = date;

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "dd-MM-yyyy HH:mm:ss");
            formattedDate = LocalDateTime.parse(this.date, formatter);
        } catch (DateTimeException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String store(){
        return String.format("D | %s | %s | %s", this.isDone, this.description,
                formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
    @Override
    public String toString(){
        return String.format("[D] %s (by: %s)", super.toString(),
                formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }
}
