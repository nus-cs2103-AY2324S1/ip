public class Todo extends Task {

    public Todo(String task, Boolean isNotSaved) {
        super(task, isNotSaved);
        if (isNotSaved) {
            saveToFile();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
        + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }

    public String generateStr() {
        return "T | " + (this.getStatus() == TaskStatus.DONE ? 1 : 0)
                + " | " + this.getTask();
    }

    @Override
    public void saveToFile() {
        Duke.saveTaskToFile(generateStr());
    }

}
