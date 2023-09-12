package duke;

public class Executor {
    private String DELIM = " ";
    public String execute(String input, TaskList tasks, Storage storage, Ui ui) {
        try {
            String[] arr = input.split(DELIM);
            String type = arr[0];

            assert tasks != null: "TaskList should be initialized prior to calling this method.";

            if (type.equals("bye")) {

                assert ui != null: "UI should have been initialized.";
                ui.end();

                assert storage != null: "Storage should have been initialized.";
                storage.saveDataFile(tasks);
                storage.saveTextFile(tasks);

                return "Goodbye!";
            } else if (type.equals("list")) {
                return tasks.listOut();
            } else if (type.equals("mark")) {
                return tasks.mark(arr);
            } else if (type.equals("unmark")) {
                return tasks.unmark(arr);
            } else if (type.equals("delete")) {
                return tasks.delete(arr);
            } else if (type.equals("find")) {
                return tasks.find(arr);
            } else {
                // check for task type first
                if (type.equals("todo")) {
                    if (arr.length == 1) {
                        throw new EmptyDescription();
                    } else {
                        String desc = tasks.getDescription(arr);
                        return tasks.addTodo(desc);
                    }
                } else if (type.equals("deadline")) {
                    String desc = tasks.getDescription(arr);
                    String date = tasks.getDeadline(arr);
                    return tasks.addDeadline(desc, date);
                } else if (type.equals("event")) {
                    String desc = tasks.getDescription(arr);
                    String timeline = tasks.getEventTimeline(arr);
                    return tasks.addEvent(desc, timeline);
                } else {
                    throw new WrongInput();
                }
            }
        } catch (EmptyDescription | WrongInput e) {
            return e.getMessage();
        }
    }
}
