package ekud.command;

public final class CreateTodoCommand extends Command {
    private final String title;

    public CreateTodoCommand(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("todo %s", title);
    }
}
