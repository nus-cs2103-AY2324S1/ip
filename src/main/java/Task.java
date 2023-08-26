import java.util.Arrays;

abstract class Task {
    protected boolean isDone;
    protected String taskName;

    protected Task(String taskName) throws IncompleteDescriptionException {
        if (taskName.isBlank()) throw new IncompleteDescriptionException();
        isDone = false;
        this.taskName = taskName;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unMarkDone() {
        this.isDone = false;
    }

    abstract String compressData();

    public static Task dataToTask(String data) throws IncompleteDescriptionException {
        String[] dataArr = data.split(" \\| ");
        Task t;
        switch (dataArr[0]) {
        case "T":
            t = ToDo.create(dataArr[2]);
            break;
        case "D":
            t = Deadline.create(dataArr[2], dataArr[3]);
            break;
        case "E" :
            t = Event.create(dataArr[2], dataArr[3], dataArr[4]);
            break;
        default:
            throw new IncompleteDescriptionException();
        }
        if (dataArr[1].equals("1")) t.markDone();
        return t;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
