package action;

public final class CreateDeadlineAction extends Action {
    private final String title;
    private final String by;

    public CreateDeadlineAction(String title, String by) {
        this.title = title;
        this.by = by;
    }

    public String getTitle() {
        return title;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return String.format("deadline %s \\by %s", title, by);
    }
}
