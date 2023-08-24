public class Deadlines extends Tasks {

    private String taskDesc;
    private String duedate;
    public Deadlines(String userInput) {
        String[] split = userInput.split("/by");
        this.taskDesc = split[0].trim().substring(8).trim();
        this.duedate = split[1].trim();
    }

    @Override
    public String toString() {
        String x;
        if (this.status) {
            x = "X";
        } else {
            x = " ";
        }
        String str1 = String.format("[%s] ", x);
        String str2 = String.format(" (by: %s)", this.duedate);
        return "[D]" + str1 + this.taskDesc + str2 ;
    }
}
