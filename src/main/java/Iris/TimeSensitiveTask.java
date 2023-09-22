package iris;

public abstract class TimeSensitiveTask extends Task {

    TimeSensitiveTask(String name) {
        super(name);
    }

    public abstract void postpone(String time);
}
