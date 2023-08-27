public class Events extends Tasks {

    private String taskDesc;
    private String from;
    private String to;
    public Events(String userInput) {
        try {

            if (userInput.equals("event")) {
                throw new IllegalArgumentException("Hey! Please enter the event description or leave a space after the command!");
            }

            String[] split_1 = userInput.split("/from");
            if (split_1.length < 2) {
                throw new IllegalArgumentException("Hey! Please provide a time range for your event");
            }

            String[] split_2 = split_1[1].split("/to");
            if (split_2.length < 2)  {
                throw new IllegalArgumentException("Hey! Please provide an end time for your event");
            } else {
                this.taskDesc = split_1[0].trim().substring(5).trim();
                this.from = split_2[0].trim();
                this.to = split_2[1].trim();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            this.taskDesc = null;
        }
    }

    public Events(String completion, String taskDesc, String from, String to) {
        try {
            if (completion.equals("1")){
                this.taskDesc = taskDesc.trim();
                this.from = from.trim();
                this.to = to.trim();
                this.markDone();
            } else {
                this.taskDesc = taskDesc.trim();
                this.from = from.trim();
                this.to = to.trim();
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
        return "E | " + str1 + " | " + this.taskDesc + " | " + this.from + " | " + this.to;
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
