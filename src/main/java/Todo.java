public class Todo extends Task{
    public Todo(String title) {
        super(title);
    }
     @Override
     public String toString(){
        String mark = super.isMarked ? "[X] " : "[ ] ";
        return "[T] " + mark + title;
     }
}
