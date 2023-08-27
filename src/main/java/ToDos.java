public class ToDos extends Tasks {

    private String taskDesc;
    public ToDos(String userInput) {
        try {
            String[] split = userInput.split(" ");
            if (split.length < 2) {
                throw new IllegalArgumentException();
            } else {
                this.taskDesc = userInput.substring(split[0].length()).trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! I think you forget to enter the todo description or leave a space after the command!");
            this.taskDesc = null;
        }
    }

    public ToDos(String taskDesc, String completion) {
        try {
            if (completion.equals("1")){
                this.taskDesc = taskDesc.trim();
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! There is an invalid todo task in the task list!");
            this.taskDesc = null;
        }
    }

    public boolean isValid() {

        return taskDesc != null;
    }
    @Override
    public String toFileString() {
        String x;
        if (this.status) {
            x = "1";
        } else {
            x = "0";
        }
        String str1 = String.format("%s", x);
        return "T | " + str1 + " | " + this.taskDesc;
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
