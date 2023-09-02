import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class OutputService {
    private static final int indentLength = 4;

    public void echo(String input) {
        echo(input, "");
    }

    public void echo(String input, String prefix) {
        echo(List.of(prefix + input));
    }

    public void echo(List<String> inputs) {
        String divider = indentLeft(String.format("%80s", "").replace(" ", "-"));
        System.out.println(divider);
        inputs.stream().map(this::indentLeft)
                .forEach(System.out::println);
        System.out.println(divider);
    }

    public String indentLeft(String input) {
        String indent = String.format("%" + indentLength + "s", "");
        String[] lines = input.split(System.lineSeparator()); // handle Unix and Windows new lines.
        for (int i = 0; i < lines.length; i++) {
            lines[i] = indent + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    public void printTasks(List<Task> taskList) {
        List<String> tasksWithNumber = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i ++) {
            String taskNumber = String.format("%s. ", i + 1);
            tasksWithNumber.add(taskNumber + taskList.get(i));
        }
        echo(tasksWithNumber);
    }
}
