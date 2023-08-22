public class ToDo extends Task{
    public ToDo(String list) {
        super(list);
    }

    @Override
    public String setMarked() throws DukeException{
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    @Override
    public String setUnmarked() throws DukeException{
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
 }
