package duke;

public abstract class TaskMaster {
    protected static TaskList tasks;
    protected static Storage storage;
    private static Boolean havePastData;

    public static void masterTasks(String input) {
        String[] splt = input.split(" ");
        String keyword = splt[0];
        Parser.parseInput(splt, keyword);
    }

    public static void initialStorage(String filePath) {
        storage = new Storage(filePath);
        if (havePastData) {
            TaskMaster.tasks = new TaskList(storage.getPastData());
        } else {
            TaskMaster.tasks = new TaskList();
        }
    }

    public static String mark(int i) {
        return tasks.mark(i);
    }

    public static String unmark(int i) {
        return tasks.unmark(i);
    }

    public static String list() {
        return tasks.list();
    }

    public static String addTask(Task t) {
        return tasks.addTask(t);
    }

    public static String removeTask(int index) {
        return tasks.removeTask(index);
    }

    public static String findTask(String key) {
        return tasks.findTask(key);
    }

    public static String peekNotes(int index) {
        return tasks.peekNotes(index);
    }
    public static String editNotes(int index, String notes) {
        return tasks.editNotes(index, notes);
    }

    public static void setPastData(Boolean havePastData) {
        TaskMaster.havePastData = havePastData;
    }

    public static void storeTasks() {
        storage.storeNewData(tasks.getTasks());
    }
}
