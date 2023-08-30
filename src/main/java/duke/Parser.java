package duke;

public class Parser {
    public static boolean command(String userOutput, TaskList inputList) {
        try {
            String[] splitOutput = userOutput.split(" ");
            if (userOutput.equals("list")) {
                inputList.list();
            } else if (splitOutput[0].equals("deadline")) {
                Deadline.setDeadline(userOutput, inputList);
            } else if (splitOutput[0].equals("todo")) {
                Todo.setTodo(userOutput, inputList);
            } else if (splitOutput[0].equals("event")) {
                Event.setEvent(userOutput, inputList);
            } else if (splitOutput[0].equals("delete")) {
                inputList.delete(splitOutput);
            } else if (splitOutput[0].equals("mark")) {
                inputList.mark(splitOutput);
            } else if (splitOutput[0].equals("unmark")) {
                inputList.unmark(splitOutput);
            } else if (userOutput.equals("bye")) {
                return true;
            } else {
                throw new InvalidInputException();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
