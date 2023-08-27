public class Todo extends Task {

    public Todo (String description) {
        super(description);
    }

    public Todo() {
        super("");
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileString() {
        return "T | " + super.getStatusIcon() + " | " + this.description;
    }

    public void fromFileString(String fileString) {
        String[] fileStringArray = fileString.split(" \\| ");
        this.setStatusIcon(fileStringArray[1]);
        this.description = fileStringArray[2];
    }
}
