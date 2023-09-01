package duke;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Parser {
    TaskList taskList;
    private static final Pattern DATE_PATTERN_DASH = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern DATE_PATTERN_SLASH = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4} .*\\d{4}$");
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Writes task into Local Storage.
     * @param promptText    Task from user's input.
     * @throws DukeException    Exception to be thrown when the input cannot be read.
     */
    public void createTask(String promptText) throws DukeException {
        if (promptText.startsWith("todo")) {
            try {
                Task todo = new Todo(promptText.substring(5));
                taskList.add(todo, true);
                taskList.writeToFile();
            }
            catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of a todo cannot be empty.");
            }
        }
        else if (promptText.startsWith("deadline")) {
            try {
                String[] parts = promptText.split("/", 2);
                String date = parts[1].substring(3);
                if (DATE_PATTERN_DASH.matcher(date).matches()) {
                    String[] dateParts = date.split("-");
                    Task deadline = new Deadline(parts[0].substring(9), LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2])));
                    taskList.add(deadline, true);
                }
                else if (DATE_PATTERN_SLASH.matcher(date).matches()) {
                    String[] dateParts = date.split("/");
                    int day = dateParts[0].length() == 1 ? Integer.valueOf("0" + dateParts[0]) : Integer.valueOf(dateParts[0]);
                    int month = dateParts[1].length() == 1 ? Integer.valueOf("0" + dateParts[1]) : Integer.valueOf(dateParts[1]);
                    String[] yearParts = dateParts[2].split(" ");
                    Task deadline = new Deadline(parts[0].substring(9), LocalDate.of(Integer.valueOf(yearParts[0]), month, day));
                    taskList.add(deadline, true);
                }
                else {
                    Task deadline = new Deadline(parts[0].substring(9), parts[1].substring(2));
                    taskList.add(deadline, true);
                }
                taskList.writeToFile();
            }
            catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
            }
        }
        else {
            try {
                String[] parts = promptText.split("/", 2);
                String dateParts = parts[1].substring(5);
                int splitIndex = dateParts.indexOf("/to");
                String startDate = dateParts.substring(0, splitIndex - 1);
                String endDate = dateParts.substring(splitIndex + 4);

                if (DATE_PATTERN_DASH.matcher(startDate).matches() &&
                        DATE_PATTERN_DASH.matcher(endDate).matches()) {
                    String[] startDateParts = startDate.split("-");
                    String[] endDateParts = endDate.split("-");
                    Task event = new Event(parts[0].substring(6), LocalDate.of(Integer.parseInt(startDateParts[0]),
                            Integer.parseInt(startDateParts[1]), Integer.parseInt(startDateParts[2])),
                            LocalDate.of(Integer.parseInt(endDateParts[0]), Integer.parseInt(endDateParts[1]),
                                    Integer.parseInt(endDateParts[2])));
                    taskList.add(event, true);
                } else if (DATE_PATTERN_SLASH.matcher(startDate).matches() &&
                        DATE_PATTERN_SLASH.matcher(endDate).matches()) {
                    String[] startDateParts = startDate.split("/");
                    String[] endDateParts = endDate.split("/");
                    int startDay = startDateParts[0].length() == 1 ? Integer.valueOf("0" + startDateParts[0]) :
                            Integer.valueOf(startDateParts[0]);
                    int startMonth = startDateParts[1].length() == 1 ? Integer.valueOf("0" + startDateParts[1]) :
                            Integer.valueOf(startDateParts[1]);
                    int endDay = endDateParts[0].length() == 1 ? Integer.valueOf("0" + endDateParts[0]) :
                            Integer.valueOf(endDateParts[0]);
                    int endMonth = endDateParts[1].length() == 1 ? Integer.valueOf("0" + endDateParts[1]) :
                            Integer.valueOf(endDateParts[1]);
                    String[] startYearParts = startDateParts[2].split(" ");
                    String[] endYearParts = endDateParts[2].split(" ");
                    Task event = new Event(parts[0].substring(6), LocalDate.of(Integer.valueOf(startYearParts[0]),
                            startMonth, startDay), LocalDate.of(Integer.valueOf(endYearParts[0]), endMonth, endDay));
                    taskList.add(event, true);
                } else {
                    Task event = new Event(parts[0].substring(6), startDate, endDate);
                    taskList.add(event, true);
                }
                taskList.writeToFile();
            } catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of an event cannot be empty.");
            }
        }
    }

    /**
     * Marks task as done or undone.
     * @param promptText    User's input to mark or unmark a task.
     * @throws DukeException    Exception that is thrown when the task does not exist.
     */
    public void markTask(String promptText) throws DukeException {
        try {
            int i = Integer.parseInt(promptText.substring(promptText.length() - 1));
            Task t = taskList.get(i-1);
            if (promptText.startsWith("unmark")) {
                t.unmark();
                taskList.writeToFile();
            } else {
                t.mark(true);
                taskList.writeToFile();
            }
        } catch (NumberFormatException n) {
            throw new DukeException("OOPS!! You must mark/unmark an index.");
        } catch (IndexOutOfBoundsException i) {
            throw new DukeException("OOPS!! This index doesn't exist.");
        }
    }
}
