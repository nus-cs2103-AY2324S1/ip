package TaskManager;
public class ToDos extends Tasks {

    private String taskDesc;
    public ToDos(String userInput) {
        this.taskDesc = userInput;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDos toDos = (ToDos) o;

        return taskDesc.equals(toDos.taskDesc);
    }

}
