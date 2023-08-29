package duke;

class Event extends Task{
    private final char taskType = 'E';

    public Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String checkBox;
        String taskType = String.format("[%c]", this.taskType);
        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}