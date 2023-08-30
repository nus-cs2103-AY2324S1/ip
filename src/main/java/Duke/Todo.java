package Duke;

public class Todo extends Task{
    public Todo (String val){
        super(val);
    }
    public String toString(){
        return "[T]"+super.toString();
    }
}
