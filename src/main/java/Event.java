public class Event extends Task{
    private String startDate;
    private String endDate;

    public Event(String text, String startDate, String endDate){
        super(text);
        super.getText();
        super.setType("E");
        this.startDate = startDate;
        this.endDate= endDate;
    }
    @Override
    public String getTypeCheckedText(){
        String result = getType() + getChecked() + " " + getText() + " s(from: " + startDate + " to: "+endDate+")";
        return result;
    }

    @Override
    public String getParsed(){
        String result = super.getParsed() + ";" + this.startDate + ";" + this.endDate;
        return result;
    }





}
