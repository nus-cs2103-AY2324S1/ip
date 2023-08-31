import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    // used to handle type of tasks
    public static void handleCommand(String cmd, Ui ui) {
        if (cmd.equals("bye")) {
            ui.bye();
            //online = false;
        } else if (cmd.equals("list")) {
            // print list
            ui.printList();
        } else if (cmd.startsWith("mark")) {
            ui.mark(cmd);
        } else if (cmd.startsWith("unmark")) {
            ui.unmark(cmd);
        } else if (cmd.startsWith("delete")) {
            ui.delete(cmd);
        } else {
            // add task to list
            try {
                String type = cmd.split("\\s+")[0]; // grab the first word
                Task newTask;
                // get a new task
                switch (type) {
                    case "todo":
                        newTask = ToDo.interpret(cmd);
                        break;
                    case "deadline":
                        newTask = Deadline.interpret(cmd);
                        break;
                    case "event":
                        newTask = Event.interpret(cmd);
                        break;
                    default:
                        throw new InvalidCommand(cmd);
                }
                // add new task using ui
                ui.add(newTask);
            } catch(EmptyTaskException e) {
                e.printStackTrace();
            } catch(InvalidCommand e) {
                e.printStackTrace();
            }

        }
    }



    /*public static LocalDateTime parseTime(String input) {
        // formatting
        String formatPattern = "yyyy-MM-dd HHmm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatPattern);
        try {
            LocalDateTime parsedTime = LocalDateTime.parse(input, formatter);
            return parsedTime;
        } catch (DateTimeParseException e) {
            System.out.println("Date Time should be in yyyy-MM-dd HHmm format!\ndefault time used\n" + e.getMessage());
            return LocalDateTime.of(2000, 1, 1, 0, 0);
        }

    }

    public static String formatTime(LocalDateTime input) {
        // format time into a string object
        String year = String.valueOf(input.getYear());
        String month = input.getMonth().toString();
        String day = String.valueOf(input.getDayOfMonth());
        int hourAndTime = input.getHour() * 100 + input.getMinute();
        int  min = input.getMinute();
        return hourAndTime < 1000
                ? month + " " + day + " " + year + " 0" + (hourAndTime)
                : month + " " + day + " " + year + " " + (hourAndTime);

    }

    public static String getCmd(LocalDateTime savedTime) {
        int year = savedTime.getYear();
        String month = savedTime.getMonthValue() >= 10 ? String.valueOf(savedTime.getMonthValue())
                : "0" + savedTime.getMonthValue();
        int day = savedTime.getDayOfMonth();
        String hourAndTime = savedTime.getHour() >= 10 ? String.valueOf(savedTime.getHour() * 100 + savedTime.getMinute())
                : "0" + (savedTime.getHour() * 100 + savedTime.getMinute());
        return year + "-" + month + "-" + day + " " + hourAndTime;
    }*/

}
