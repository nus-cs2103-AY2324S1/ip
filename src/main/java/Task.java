public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public abstract String toDataString();

    public static Task createTaskFromDataString(String dataString) throws DukdukException {
        String[] parts = dataString.split(" \\| ");

        if (parts.length < 3) {
            throw new DukdukException("Invalid task data format: " + dataString);
        }

        String type = parts[0];
        String description = parts[2];

        switch (type) {
            case "T":
                return new ToDo(description);
            case "D":
                if (parts.length >= 4) {
                    String by = parts[3];
                    return new Deadline(description, by);
                } else {
                    throw new DukdukException("Invalid Deadline task data format: " + dataString);
                }
            case "E":
                if (parts.length >= 4) {
                    String eventTiming = parts[3];
                    String[] eventParts = eventTiming.split(" ");
                    if (eventParts.length >= 2) {
                        String from = eventParts[0];
                        String to = eventParts[1];
                        return new Event(description, from, to);
                    } else {
                        throw new DukdukException("Invalid Event task data format: " + dataString);
                    }
                } else {
                    throw new DukdukException("Invalid Event task data format: " + dataString);
                }
            default:
                throw new DukdukException("Invalid task type in data string: " + type);
        }
    }
}
