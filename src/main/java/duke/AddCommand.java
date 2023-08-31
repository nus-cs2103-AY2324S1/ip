package duke;

import java.time.LocalDate;

public class AddCommand extends Command{

    private final String toAdd;

    public AddCommand(String str) {
        toAdd = str;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (toAdd.startsWith("todo ")) {
                if (toAdd.length() < 6) throw new IncompleteInputException("todo");
                String des = toAdd.substring(5);
                Todo todo = new Todo(des);
                tasks.add(todo);
                ui.showTaskAdded(todo, tasks.total());

            } else if (toAdd.startsWith("deadline ")) {
                String[] words = toAdd.split("/");
                if (words.length != 2 || words[0].length() < 10 || words[1].length() < 4) {
                    throw new IncompleteInputException("deadline");
                }
                String des = words[0].substring(9);
                String date = words[1].substring(3);
                LocalDate by = LocalDate.parse(date);
                Deadline dl = new Deadline(des, by);
                tasks.add(dl);
                ui.showTaskAdded(dl, tasks.total());


            } else if (toAdd.startsWith("event ")) {
                String[] words = toAdd.split("/");
                if (words.length != 3 || words[0].length() < 7 || words[1].length() < 6 || words[2].length() < 4) {
                    throw new IncompleteInputException("deadline");
                }
                String des = words[0].substring(6);
                String fromDate = words[1].substring(5, 15);
                String toDate = words[2].substring(3);
                LocalDate from = LocalDate.parse(fromDate);
                LocalDate to = LocalDate.parse(toDate);
                Event event = new Event(des, from, to);
                tasks.add(event);
                ui.showTaskAdded(event, tasks.total());

            } else {
                throw new InvalidInputException();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
