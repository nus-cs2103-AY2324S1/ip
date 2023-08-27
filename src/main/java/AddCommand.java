import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String description;
    private String till;
    private String from;
    private String type;

    // ToDo Constructor
    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    // Deadline Constructor
    public AddCommand(String description, String till) {
        this.description = description;
        this.till = till;
        this.type = "deadline";
    }

    // Event Constructor
    public AddCommand(String description, String from, String till) {
        this.description = description;
        this.till = till;
        this.from = from;
        this.type = "event";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (this.type) {
            case "todo":
                Task task1 = new Todo(this.description);
                tasks.add(task1);
                ui.showAdd(tasks.size(), task1);
                storage.writeData(tasks.getAllTasks());
                break;
            case "deadline":
                try {
                    Task task2 = new Deadline(this.description, ui.formatInputDate(this.till));
                    tasks.add(task2);
                    ui.showAdd(tasks.size(), task2);
                    storage.writeData(tasks.getAllTasks());
                } catch (DateTimeParseException exp) {
                    ui.showLine();
                    System.out.println("Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
                    ui.showLine();
                }
            case "event":
                try {
                    Task task3 = new Event(this.description, ui.formatInputDate(this.from), ui.formatInputDate(this.till));
                    tasks.add(task3);
                    ui.showAdd(tasks.size(), task3);
                    storage.writeData(tasks.getAllTasks());
                } catch (DateTimeParseException exc) {
                    ui.showLine();
                    System.out.println("Enter the date & time in a valid format! (DD/MM/YY HHMM)");
                    ui.showLine();
                }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
