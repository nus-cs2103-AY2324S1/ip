package brotherman.tasks;

public class Todo extends Task{
    public Todo (String description) {
        super(description);
    }

    public String type() {
        return "T";
    }



    @Override
    public String storeText() {
        return String.format("%s|%s|%s", this.type(), this.isDone, this.description);
    }
    @Override
    public String toString() {
        String type = this.type();
        return String.format("[%s]%s", type, super.toString() );
    }
}
