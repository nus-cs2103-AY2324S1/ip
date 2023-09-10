package ren.task;

import ren.RenObjectMapper;

import java.time.LocalDate;
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
     * Adds ren.task.ToDo into ren.task.TaskList.
     *
     * @param commandArr array of the command split by whitespace
     */
    public void addTodoTask(String[] commandArr) throws ren.InsufficientArgumentsException {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < commandArr.length; i++) {
            builder.append(commandArr[i]).append(" ");
        }

        String todoDesc = builder.toString();
        this.tasks.add(new ToDo(todoDesc, false));
    }

    /**
     * Adds ren.task.Deadline into ren.task.TaskList.
     *
     * @param commandArr array of the command split by whitespace
     */
    public void addDeadlineTask(String[] commandArr) throws ren.InsufficientArgumentsException {
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

        String deadlineString = builder.toString();
        deadlineString = deadlineString.trim();
        LocalDate deadline = LocalDate.parse(deadlineString);
        this.tasks.add(new Deadline(deadlineDesc, false, deadline));
    }

    /**
     * Adds ren.task.Event into ren.task.TaskList.
     *
     * @param commandArr array of the command split by whitespace
     */
    public void addEventTask(String[] commandArr) throws ren.InsufficientArgumentsException {
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

    /**
     * Adds ren.task.Task into ren.task.TaskList.
     *
     * @param commandArr array of the command split by whitespace
     * @return the added task
     */
    public Task addTask(String[] commandArr) throws ren.InsufficientArgumentsException {
        if (commandArr.length <= 1) {
            throw new ren.InsufficientArgumentsException();
        }


        if (commandArr[0].equals("todo")) {
            addTodoTask(commandArr);
        } else if (commandArr[0].equals("deadline")) {
            addDeadlineTask(commandArr);
        } else if (commandArr[0].equals("event")) {
            addEventTask(commandArr);
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
     * into the ren.task.TaskList. List is 1-indexed
     */
    public void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("%d %s\n%n", i + 1, tasks.get(i));
        }
    }

    /**
     * Adds task into TaskList.
     *
     * @param commandArr array of the command split by whitespace
     * @return the added task
     */
    public Task deleteTask(String[] commandArr) throws ren.InsufficientArgumentsException {
        if (commandArr.length <= 1) {
            throw new ren.InsufficientArgumentsException();
        }

        int indexToBeDeleted = Integer.parseInt(commandArr[1]) - 1;
        Task taskDeleted = this.tasks.get(indexToBeDeleted);
        this.tasks.remove(indexToBeDeleted);
        RenObjectMapper.storeIntoHarddisk(this);
        return taskDeleted;
    }

    /**
     * Declares number of tasks in TaskList.
     */
    public String declareNumOfTasks() {
        assert this.tasks.size() >= 0;
        return String.format("%d tasks in the list\n", tasks.size());
    }

    /**
     * Lists out the string representation of Tasks in the order they were added
     * into the ren.task.TaskList. List is 1-indexed
     *
     * @param query the query to be matched
     */
    public void listMatchingTasks(String query) {
        this.tasks.stream()
                .filter(task -> task.queryInDescription(query))
                .forEach(task -> System.out.printf("%d %s\n%n", this.tasks.indexOf(task) + 1, task));
    }

    /**
     * Returns a list of tasks that match the query
     *
     * @param query the query to be matched
     * @return a list of tasks that match the query
     */
    public List filterToListOfMatchingTasks(String query) {
        return this.tasks.stream()
                .filter(task -> task.queryInDescription(query))
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Task task : this.tasks) {
            builder.append(task).append("\n");
        }
        return builder.toString();
    }


}
