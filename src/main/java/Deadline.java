public class Deadline extends Task{
    private String dueDate;

    public Deadline (String text, String dueDate){
        super(text);
        super.setType("D");
        this.dueDate = dueDate;
    }
    public Deadline (String text, String dueDate,boolean checked){
        super(text,checked);
        super.setType("D");
        this.dueDate = dueDate;
    }
    @Override
    public String getTypeCheckedText(){
        String result = getType() + getChecked() + " " + getText() + " (by: " + dueDate+")";
        return result;
    }
    @Override
    public String getParsed(){
        String result = super.getParsed() + ";" + this.dueDate;
        return result;
    }




}
