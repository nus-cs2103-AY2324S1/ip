public class Deadlines extends Tasks {

    private String taskDesc;
    private String duedate;
    public Deadlines(String userInput) {
        try {
            String[] split = userInput.split("/by");
            if (userInput.equals("deadline")) {
                throw new IllegalArgumentException("Hey! Please enter the task description or leave a space after the command!");
            } else if (split.length < 2) {
                throw new IllegalArgumentException("Hey! Please provide a deadline for your task");
            } else {
                this.taskDesc = split[0].trim().substring(8).trim();
                this.duedate = split[1].trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.taskDesc = null;
        }
    }

    public Deadlines(String completion, String taskDesc, String duedate) {
        try {
            if (completion.equals("1")){
                this.taskDesc = taskDesc.trim();
                this.duedate = duedate.trim();
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
                this.duedate = duedate.trim();
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
        return "D | " + str1 + " | " + this.taskDesc + " | " + this.duedate;
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
