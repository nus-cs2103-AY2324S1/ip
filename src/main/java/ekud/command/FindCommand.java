package ekud.command;

public final class FindCommand extends Command {
    private final String query;

    public FindCommand(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    @Override
    public String toString() {
        return String.format("find %s", query);
    }
}
