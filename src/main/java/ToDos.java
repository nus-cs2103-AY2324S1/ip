public class ToDos extends Tasks {

    private String taskDesc;
    public ToDos(String userInput) {
        String[] split = userInput.split(" ");
        this.taskDesc = userInput.substring(split[0].length()).trim();
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
        return "[T]" + str1 + this.taskDesc;
    }
}
