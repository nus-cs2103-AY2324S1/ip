import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> previous_Commands= new ArrayList<>();

    public void add(Task task) {
        this.previous_Commands.add(task);
        System.out.println(
                String.format("Added: %s", task.get())
        );
    }

    public void listItems() {
        for (int i = 0; i < this.previous_Commands.size(); ++i) {
            Task task = this.previous_Commands.get(i);
            System.out.println(
                    String.format("%d. [%s] %s",
                            i + 1, task.getStatusIcon(), task.get()
                    )
            );
        }
    }

    public void mark(int taskNumber) {
        Task task = previous_Commands.get(taskNumber - 1);

        if (task.isDone()) {
            return;
        }

        task.toggleDone();
        System.out.println(
                String.format(
                        "Nice! I've marked this task as done: [%s] %s",
                        task.getStatusIcon(), task.get()
                        )
        );
    }

    public void unmark(int taskNumber) {
        Task task = previous_Commands.get(taskNumber - 1);

        if (!task.isDone()) {
            return;
        }

        task.toggleDone();
        System.out.println(
                String.format(
                        "Ok, I've marked this task as not done yet: [%s] %s",
                        task.getStatusIcon(), task.get()
                )
        );
    }


}
