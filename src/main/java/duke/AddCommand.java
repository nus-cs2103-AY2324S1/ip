package duke;

import java.time.LocalDate;

/**
 * Adds a task to the task list
 */
public class AddCommand extends Command {

    /** Description of the task */
    private final String TASK_TO_ADD;

    public AddCommand(String str) {
        TASK_TO_ADD = str;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (TASK_TO_ADD.startsWith("todo")) {
                if (TASK_TO_ADD.length() < 6) {
                    throw new IncompleteInputException("todo");
                }
                String des = TASK_TO_ADD.substring(5);
                Todo todo = new Todo(des);
                tasks.add(todo);
                return ui.showTaskAdded(todo, tasks.total());

            } else if (TASK_TO_ADD.startsWith("deadline")) {
                String[] words = TASK_TO_ADD.split("/");
                boolean isValidTask = words.length != 2;
                boolean isDesEmpty = words[0].length() < 10;
                boolean isDateEmpty = words[1].length() < 4;
                if (isValidTask || isDesEmpty || isDateEmpty) {
                    throw new IncompleteInputException("deadline");
                }
                String des = words[0].substring(9);
                String date = words[1].substring(3);
                LocalDate by = LocalDate.parse(date);
                Deadline dl = new Deadline(des, by);
                tasks.add(dl);
                return ui.showTaskAdded(dl, tasks.total());


            } else if (TASK_TO_ADD.startsWith("event ")) {
                String[] words = TASK_TO_ADD.split("/");
                boolean isValidTask = words.length != 3;
                boolean isDesEmpty = words[0].length() < 7;
                boolean isFromEmpty = words[1].length() < 6;
                boolean isToEmpty = words[2].length() < 4;
                if (isValidTask || isDesEmpty || isFromEmpty || isToEmpty) {
                    throw new IncompleteInputException("deadline");
                }
                String des = words[0].substring(6);
                String fromDate = words[1].substring(5, 15);
                String toDate = words[2].substring(3);
                LocalDate from = LocalDate.parse(fromDate);
                LocalDate to = LocalDate.parse(toDate);
                Event event = new Event(des, from, to);
                tasks.add(event);
                return ui.showTaskAdded(event, tasks.total());

            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
    }

}
