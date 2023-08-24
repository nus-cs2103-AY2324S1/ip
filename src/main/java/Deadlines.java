public class Deadlines extends Task {
    private String date;

    Deadlines(String description, String date) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("description of deadline cannot be empty");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new DukeException("date of deadline cannot be empty");
        }
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
        String completed = this.getCompleted() ? "[X] " : "[ ] ";
        String taskType = "[" + this.getType() + "]";
        String byMessage = "by: " + getDate();
        return  taskType + completed + this.getDescription() + "(" + byMessage +")";
    }
}
