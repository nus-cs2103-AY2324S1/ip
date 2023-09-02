import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Parser {
    public final static DateTimeFormatter DTFORMAT  = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
    Parser() {}

    /*public static Task parseInput(String input) {
        String[] details = input.split(" ");
        String command = details[0];


    }*/
    public static Task parseFile(String saveString) {
        String[] details = saveString.split(" \\| ");
        Task newTask;
        try {
            switch (details[0]) {
                case "T":
                    newTask = new Todo(details[2], getBoolean(details[1]));
                    break;
                case "D":
                    newTask = new Deadline(details[2], getBoolean(details[1]),
                            LocalDateTime.parse(details[3], DTFORMAT));
                    break;
                case "E":
                    newTask = new Event(details[2], getBoolean(details[1]),
                            LocalDateTime.parse(details[3],DTFORMAT) , LocalDateTime.parse(details[4],DTFORMAT));
                    break;
                default:
                    // Should throw an error here, maybe a can't read error?
                    newTask = new Todo("Oops, no details!", true);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Yikes, your savefile had some issues!");
            newTask = new Todo("Oops, no details!", true);
        }
        return newTask;
    }

    /*
     * Gets a boolean value from a String-represented 1 or 0.
     *
     * @params String Done state as represented by 1 or 0
     * @return True or False
     */
    public static boolean getBoolean(String value)
    {
        return (value.equals("1"));
    }
}
