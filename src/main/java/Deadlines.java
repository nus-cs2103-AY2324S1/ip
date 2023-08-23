public class Deadlines extends Task {
    private String date;

    Deadlines(String description, String date) {
        super(description);
        this.date= date;
    }

    public String getDate(){
        return this.date;
    }

    @Override
    public String getType(){
        return "D";
    }
    @Override
    public String getString() {
        String completed = this.getCompleted() ? "[X]" : "[ ]";
        String taskType = "[" + this.getType() + "] ";
        String byMessage = "by: " + getDate();
        return  taskType + completed + this.getDescription() + "(" + byMessage +")";
    }
}
