package action;

public final class CreateTodoAction extends Action {
    private final String title;

    public CreateTodoAction(String title) {
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
