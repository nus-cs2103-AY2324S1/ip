public class ToDo extends Task {
    private String symbol = "[T]";
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return this.symbol + this.getCheckbox() + this.getName();
    }
}