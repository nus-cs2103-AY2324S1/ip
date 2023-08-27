import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds Task into TaskList.
     *
     * @param commandArr array of the command split by whitespace
     * @return the added task
     */
    public Task addTask(String[] commandArr) throws InsufficientArguments {
        if (commandArr.length <= 1) {
            throw new InsufficientArguments();
        }


        if (commandArr[0].equals("todo")) {
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < commandArr.length; i++) {
                builder.append(commandArr[i]).append(" ");
            }

            String todoDesc = builder.toString();
            this.tasks.add(new ToDo(todoDesc, false));
        } else if (commandArr[0].equals("deadline")) {
            StringBuilder builder = new StringBuilder();
            String deadlineDesc = "";
            for (int i = 1; i < commandArr.length; i++) {
                if (commandArr[i].equals("/by")) {
                    deadlineDesc = builder.toString();
                    builder = new StringBuilder();
                    continue;
                }
                builder.append(commandArr[i]).append(" ");
            }

            String deadline = builder.toString();
            this.tasks.add(new Deadline(deadlineDesc, false, deadline));
        } else {
            StringBuilder builder = new StringBuilder();
            String eventDesc = "";
            String startTime = "";

            for (int i = 1; i < commandArr.length; i++) {
                if (commandArr[i].equals("/from")) {
                    eventDesc = builder.toString();
                    builder = new StringBuilder();
                    continue;
                } else if (commandArr[i].equals("/to")) {
                    startTime = builder.toString();
                    builder = new StringBuilder();
                    continue;
                }

                builder.append(commandArr[i]).append(" ");
            }

            String endTime = builder.toString();
            this.tasks.add(new Event(eventDesc, false, startTime, endTime));
        }

        RenObjectMapper.storeIntoHarddisk(this);
        return this.tasks.get(this.tasks.size() - 1);
    }

    /**
     * Returns task that was toggled from done to undone and vice versa
     *
     * @param commandArr array of the command split by whitespace
     * @return the toggled task
     */
    public Task toggleTask(String[] commandArr) {
        Task task = this.tasks.get(Integer.parseInt(commandArr[1]) - 1);
        task.toggleTask();
        RenObjectMapper.storeIntoHarddisk(this);
        return task;
    }

    /**
     * Lists out the string representation of Tasks in the order they were added
     * into the TaskList. List is 1-indexed
     */
    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d %s\n%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Adds Task into TaskList.
     *
     * @param commandArr array of the command split by whitespace
     * @return the added task
     */
    public Task deleteTask(String[] commandArr) throws InsufficientArguments {
        if (commandArr.length <= 1) {
            throw new InsufficientArguments();
        }

        int indexToBeDeleted = Integer.parseInt(commandArr[1]) - 1;
        Task taskDeleted = this.tasks.get(indexToBeDeleted);
        this.tasks.remove(indexToBeDeleted);
        RenObjectMapper.storeIntoHarddisk(this);
        return taskDeleted;
    }


    public String declareNumOfTasks() {
        return String.format("%d tasks in the list\n", tasks.size());
    }


}
