import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String date;
    protected LocalDateTime formattedDate;

    public Deadline(String description, String date){
        super(description);
        this.date = date;

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    "[yyyy-MM-dd][dd/MM/yyyy][MM/dd/yyyy HH:mm:ss]");
            formattedDate = LocalDateTime.parse(this.date, formatter);

        } catch (DateTimeException e){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                        "[yyyy-MM-dd][dd/MM/yyyy][MM/dd/yyyy HH:mm:ss]");
                formattedDate = LocalDate.parse(this.date, formatter).atStartOfDay();
            } catch (DateTimeParseException err){
                System.out.println("Error: " + err.getMessage());
            }
        }
    }


    @Override
    public String store(){
        return String.format("D | %s | %s | %s", this.isDone, this.description,
                formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    @Override
    public String toString(){
        return String.format("[D] %s (by: %s)", super.toString(),
                formattedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
