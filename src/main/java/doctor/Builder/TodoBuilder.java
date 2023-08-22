package java.doctor.Builder;

import java.doctor.Task.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoBuilder implements Builder<Task> {
    private String pattern = "todo\\s+(.*)";
    

    @Override
    public Todo buildFromString(String input) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input);
        if (m.matches()) {
            String description = m.group(1);
            Todo todo = new Todo();
            todo.setDescription(description);
            return todo;
        }
        return null;
    }
}
