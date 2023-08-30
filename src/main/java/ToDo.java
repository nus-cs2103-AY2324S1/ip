import java.util.List;

public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public static ToDo create(List<String> queryList) throws DukeException {
        if (queryList.size() < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String name = "";
        for (int i = 1; i < queryList.size(); i++) {
            name += i > 1 ? " " : "";
            name += queryList.get(i);
        }
        return new ToDo(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String exportToText() {
        return String.format("todo,>%s", super.exportToText());
    }
}
