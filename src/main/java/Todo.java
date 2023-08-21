public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return this.isDone ? "[T][X] "+this.description : "[T][ ] "+this.description ;
    }
}
