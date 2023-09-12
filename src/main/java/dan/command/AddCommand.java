package dan.command;

import dan.task.*;

public class AddCommand extends Command {
    /** Fields */
    public static final String type = "add";
    private String text;
    private int id;
    private Task newTask;

    /** Constructor */
    public AddCommand(String text, int id) {
        super();
        this.text = text;
        this.id = id;
    }

    /** Methods */
    @Override
    public void op(TaskList tasks) throws IndexOutOfBoundsException {
        String[] texts = text.split("/");
        for (int i = 0; i < texts.length; i++) {
            texts[i] = texts[i].trim();
        }
        String description;
        switch (id) {
            case 1:
                description = texts[0].substring(5);
                newTask = new ToDo(description);
                break;
            case 2:
                description = texts[0].substring(9);
                String deadline = texts[1].substring(3);
                newTask = new Deadline(description, deadline);
                break;
            case 3:
                description = texts[0].substring(5);
                String start = texts[1].substring(5);
                String end = texts[2].substring(3);
                newTask = new Event(description, start, end);
                break;
        }
        tasks.add(newTask);
        tasks.storageChanged = 1;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return newTask.toString();
    }
}
