public class Todo extends Task{
    String todo;

    public Todo(String todo) {
        super(todo);
        this.todo = todo;
    }

    @Override
    public String eventCode() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.eventCode(), this.getStatusIcon(), this.eventDescription());
    }



}
