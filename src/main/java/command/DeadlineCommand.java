package command;

public final class DeadlineCommand extends TaskCommand {
    private String by;

    public DeadlineCommand(String title, String by) {
        super(title);
        this.by = by;
    }

    public String getBy() {
        return by;
    }
}
