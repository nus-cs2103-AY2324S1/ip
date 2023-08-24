public class Events extends Tasks {

    private String taskDesc;
    private String from;
    private String to;
    public Events(String userInput) {
        String[] split_1 = userInput.split("/from");
        String[] split_2 = split_1[1].split("/to");
        this.taskDesc = split_1[0].trim().substring(5).trim();
        this.from = split_2[0].trim();
        this.to = split_2[1].trim();
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
        String str2 = String.format(" (from: %s to: %s)", this.from, this.to);
        return "[E]" + str1 + this.taskDesc + str2;
    }
}
