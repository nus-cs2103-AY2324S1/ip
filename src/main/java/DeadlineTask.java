public class DeadlineTask extends Task {

    private String by;

    String str;
    String deadline;
    public DeadlineTask(String task) {
        super(task);
        String[] strArr = task.split("/");
        str = strArr[0];
        deadline = strArr[1].split(" ", 2)[1];
    }

    @Override
    public String toString() {
        return String.format("[D] | %s | %s | %s",
                this.isDone() ? "[X]" : "[ ]",
                str, Parser.dateToString(deadline));
    }

}
