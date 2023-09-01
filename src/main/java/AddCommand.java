public class AddCommand extends Command {

    public AddCommand(TaskType t, String desc) {
        setDescription(desc);
        setTaskType(t);
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws KieraException {
       TaskType t = this.getTaskType();
       String desc = this.getDescription();
       Task task;
        switch (t) {
            case TODO:
                task = new Todo(desc);
                tasks.add(task);
                ui.showAddNotice(task, t, tasks.getSize());
                break;
            case DEADLINE:
                task = new Deadline(desc);
                tasks.add(task);
                ui.showAddNotice(task, t, tasks.getSize());
                break;
            case EVENT:
                task = new Event(desc);
                tasks.add(task);
                ui.showAddNotice(task, t, tasks.getSize());
                break;
        }
        storage.save(tasks);
    }

    @Override
    boolean isExit() {
        return false;
    }

}
