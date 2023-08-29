public class ToDos extends Task {
    public ToDos(String task) throws DukeException {
        super(task);
    }

    @Override
    public String printTask() {
        return String.format("[T]%s", super.printTask());
    }

    @Override
    public String addToStorage() {
        return String.format("T %s%n", super.addToStorage());
    }
}
