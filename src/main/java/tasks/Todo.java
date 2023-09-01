package tasks;
public class Todo extends Task {
    public Todo (String description){
        super(description);
    }

    @Override
    public String store(){
        return String.format("T | %s | %s", this.isDone, this.description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
