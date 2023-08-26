class TaskList {
    private Task[] tasks;
    private int taskCount;

    public TaskList() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public void processCommand(String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        String taskType = parts[0].toLowerCase();

        if (taskType.equals("todo")) {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            addTask(new TodoTask(parts[1]));
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        } else if (taskType.equals("deadline")) {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            addTask(new DeadlineTask(parts[1]));
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        } else if (taskType.equals("event")) {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            addTask(new EventTask(parts[1]));
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + tasks[taskCount - 1]);
            System.out.println(" Now you have " + taskCount + " tasks in the list.");
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;
    }

    public void markAsDone(String command) throws DukeException {
        int taskIndex = Integer.parseInt(command.substring(5).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[taskIndex]);
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    public void markAsNotDone(String command) throws DukeException {
        int taskIndex = Integer.parseInt(command.substring(7).trim()) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskIndex]);
        } else {
            throw new DukeException("Invalid task index.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            sb.append(" ").append(i + 1).append(".").append(tasks[i]);
            if (i != taskCount - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
