package duke.tasks;

class Event extends Task {

    public Event(String name) {
        super(name);
    }

    /**
     * returns string representation, with task type, marked, content and period.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        String checkBox;
        char taskType1 = 'E';
        String taskType = String.format("[%c]", taskType1);
        if (super.isMarked()) {
            checkBox = "[X]";
        } else {
            checkBox = "[ ]";
        }
        return String.format("%s%s %s", taskType, checkBox, super.getName());
    }
}