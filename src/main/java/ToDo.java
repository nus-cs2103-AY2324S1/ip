import java.util.Arrays;
import java.util.List;

public class ToDo extends Task {
    ToDo(String name) {
        super(name);
    }

    public static ToDo create(String[] queries) {
        List<String> queryList = Arrays.asList(queries);
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
}
