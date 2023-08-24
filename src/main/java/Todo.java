public class Todo extends Task{
    Todo(String s){
            super(s);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
