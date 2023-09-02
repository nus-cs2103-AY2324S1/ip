import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {

    }

    public boolean inputs(String input, TaskList tasks, Ui iu) {
        String[] listOfWords = input.split(" ");
        String prefix = listOfWords[0];
        boolean future = true;
        try {
            if (input.equals("bye")) {
                Storage.saveTasks(tasks);
                Ui.printBye();
                future = false;
            } else if (input.equals("list")) {
                iu.printList(tasks);
            } else if (prefix.equals("mark")) {
                int index = Integer.parseInt(listOfWords[1]);

                if (index < 1 || index > tasks.numOfItems()) {
                    throw new InvalidInput("False Index");
                }

                tasks.checkItem(index);
                iu.printMarked(tasks, index);
            } else if (prefix.equals("unmark")) {
                int index = Integer.parseInt(listOfWords[1]);

                if (index < 1 || index > tasks.numOfItems()) {
                    throw new InvalidInput("False Index");
                }

                tasks.notDoneItem(index);
                iu.printUnmarked(tasks, index);
            } else if (prefix.equals("delete")) {
                int index = Integer.parseInt(listOfWords[1]);


                if (index < 1 || index > tasks.numOfItems()) {
                    throw new InvalidInput("False Index");
                }

                iu.printDelete(tasks, index);
                tasks.deleteIndex(index);
            } else {
                boolean exceptionOccured = false;
                try {
                    tasks.input(input);
                } catch (IncompleteInput e) {
                    exceptionOccured = true;
                    iu.printTaskWithoutDescription();
                } catch (InvalidInput e) {
                    exceptionOccured = true;
                    iu.printNonsense();
                } finally {
                    if (!exceptionOccured) {
                        iu.printAddedToList(tasks);
                    }
                }
            }
        } catch (InvalidInput e) {
            iu.printWrongIndex();
        } finally {
            return future;
        }
    }

    public String formatTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");
        String formattedStringOutput = dateTime.format(outputFormatter);
        return formattedStringOutput;
    }

    public static String dateToString(String time) {
        try {
            Parser dud = new Parser();
            String formattedStringOutput = dud.formatTime(time);
            return formattedStringOutput;
        } catch (DateTimeParseException e) {
            return time;
        }
    }

}
