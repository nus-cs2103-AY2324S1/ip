import Exceptions.EmptyDescriptionException;

public class Todo extends Task{
    Todo(String s) throws EmptyDescriptionException {
            super(s);
    }

    public String toString(){
        return "[T]" + super.toString();
    }
}
