public class Deadline extends Task{
    private String by;
    public Deadline (String val, String by){
        super(val);
        this.by=by;
    }
    public String toString(){
        return "[D]"+super.toString() +" (by: "+by+")";
    }
}
