public class ToDo extends Task {
    public ToDo(String name, Boolean marked) {
        super(name, marked);
    }

    @Override
    public String getType() {
        return "Todo";
    }

    @Override
    public String getTaskDetails() {
        return "Todo," + this.name + "," + this.isMarked;
    }

    @Override
    public String toString() {
        if (this.isMarked) {
            return "[T][X] " + this.name;
        }

        return "[T][ ] " + this.name;
    }
}
