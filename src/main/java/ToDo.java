public class ToDo extends Task {

    public ToDo(String msg) {
        super("T",false, msg);
    }

    public static ToDo newToDo(String input) {
        return new ToDo(input.substring(5));
    }
}



