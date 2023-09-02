package services.bizerrors;

public class IndexOutOfRangeException extends JarvisException {
    public IndexOutOfRangeException(int index, int taskCount) {
        super(String.format("Sir, your calendar does not contain this task index (%d).\n"
                + "It currently has %d tasks.", index, taskCount));
    }
}
