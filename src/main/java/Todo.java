public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public void displayTask(int index) {
        if (super.completed) {
            System.out.println(String.format("\t \t \t \t %d) [Todo] [X] ".concat(super.action), index));
        } else {
            System.out.println(String.format("\t \t \t \t %d) [Todo] [ ] ".concat(super.action), index));
        }
    }
}
