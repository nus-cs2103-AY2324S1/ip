public class Events extends Task {
    private String startDate;
    private String endDate;

    Events(String description, String startDate, String endDate) throws DukeException {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new DukeException("description of event cannot be empty");
        }
        if (startDate == null || startDate.trim().isEmpty()) {
            throw new DukeException("date of event cannot be empty");
        }

        if (endDate == null || endDate.trim().isEmpty()) {
            throw new DukeException("date of event cannot be empty");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }



    @Override
    public String getType(){
        return"E";
    }

    @Override
    public String getString() {
        String completed = this.getCompleted() ? "[X] " : "[ ] ";
        String taskType = "[" + this.getType() + "]";
        String fromMessage = "from: " + getStartDate();
        String byMessage = "to: " + getEndDate();
        return taskType + completed + this.getDescription() + "(" + fromMessage + " " + byMessage +")";
    }
}
