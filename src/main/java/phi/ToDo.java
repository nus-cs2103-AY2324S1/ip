package phi;
public class ToDo extends Task {

    public ToDo(String msg, boolean isDone) {
        super(Type.T,isDone, msg);
    }

    public static ToDo newToDo(String input) {
        if (input.equals("todo")) {
            throw new IllegalArgumentException("You gotta put an actual message in...");
        } else if (!input.startsWith("todo ")) {
            throw new IllegalArgumentException(
                    String.format("Hey nice job, did you mean \"todo %s\"...", input.substring(4)));
        }
        return new ToDo(input.substring(5), false);
    }

    @Override
    public String outputFormat() {
        return String.format("%s|%b|%s", taskType.toString(), done, taskName);
    }

}



