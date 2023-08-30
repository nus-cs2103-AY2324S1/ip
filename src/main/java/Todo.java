public class Todo extends Task{
    Todo(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskFileString() {
        return "T" + " , " + super.getTaskFileString();
    }

    @Override
    public void printStart() {
        System.out.println("    This todo has no start time");
    }

    @Override
    public void printEnd() {
        System.out.println("    This todo has no start time");
    }

    @Override
    public void printDueDate() {
        System.out.println("    This todo has no due date");
    }
}
