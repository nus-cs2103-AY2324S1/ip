package duke.task;

public class ToDo extends Task{
    public ToDo (String text){
        super(text);
        super.setType("T");
    }

    public ToDo (String text,boolean checked){
        super(text,checked);
        super.setType("T");
    }



}
