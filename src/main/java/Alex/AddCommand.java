package Alex;

import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddCommand extends Command{
    private String command;
    private Add addType;

    public AddCommand(String command, Add addType) {
        this.command = command;
        this.addType = addType;
    }

    @Override
    public void execute() {
        switch(this.addType) {
            case TODO:
                try {
                    if (!command.substring(4, 5).equals(" ")) {
                        String message = "OOPS!!! Please enter a todo task in the following format:\n"
                                + "   " + "todo (description)";
                        throw new AlexException(message);
                    }
                    Task todo = new ToDos(command.substring(5));
                    TaskList.store(todo);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printAlertForTodo();
                } catch (AlexException e) {
                    System.out.println(Ui.horizontalLine
                            + e.getMessage() + "\n"
                            + Ui.horizontalLine
                    );
                } finally {
                    break;
                }

            case DEADLINE:
                try {
                    String regex = "\\b /by \\b";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(command);
                    if (!matcher.find()) {
                        String message = "Please enter a deadline task in the format: " +
                                "deadline (description) /by yyyy-MM-dd HHmm";
                        throw new AlexException(message);
                    }
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    String description = startIndex > 9 ? command.substring(9, startIndex) : "";
                    String by = command.substring(endIndex);
                    Task deadline = new Deadline(description, by);
                    TaskList.store(deadline);
                } catch (AlexException e) {
                    System.out.println(Ui.horizontalLine
                            + e.getMessage() + "\n"
                            + Ui.horizontalLine
                    );
                } catch (DateTimeParseException e) {
                    Ui.printAlertForDeadline();
                } finally {
                    break;
                }

            case EVENT:
                try {
                    String regex = "\\b /from \\b";
                    Pattern pattern1 = Pattern.compile(regex);
                    Matcher matcher1 = pattern1.matcher(command);
                    if (!matcher1.find()) {
                        String message = "Please enter an event task in the format: "
                                + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm";
                        throw new AlexException(message);
                    }
                    int firstStart = matcher1.start();
                    int firstEnd = matcher1.end();

                    String regex2 = "\\b /to \\b";
                    Pattern pattern2 = Pattern.compile(regex2);
                    Matcher matcher2 = pattern2.matcher(command);
                    if (!matcher2.find()) {
                        String message = "Please enter an event task in the format: "
                                + "event (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm";
                        throw new AlexException(message);
                    }
                    int secondStart = matcher2.start();
                    int secondEnd = matcher2.end();

                    String description = firstStart > 6 ? command.substring(6, firstStart) : "";
                    String fromTime = command.substring(firstEnd, secondStart);
                    String toTime = command.substring(secondEnd);

                    Task event = new Event(description, fromTime, toTime);
                    TaskList.store(event);
                } catch (AlexException e) {
                    System.out.println(Ui.horizontalLine
                            + e.getMessage() + "\n"
                            + Ui.horizontalLine
                    );
                } catch (DateTimeParseException e) {
                    Ui.printAlertForEvent();
                } finally {
                    break;
                }
        }
    }

}
