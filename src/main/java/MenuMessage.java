public class MenuMessage extends Message {
    @Override
    public void send() {
        System.out.println(
                createMessage(
                        "Commands:",
                        "list -> shows list of tasks",
                        "mark <int: task number> -> mark task as done",
                        "unmark <int: task number> -> unmark task as not done",
                        "todo <String: task name> -> create a task",
                        "deadline <String: task name> /by <String: deadline> -> create task with deadline",
                        "event <String: event name> /from <String: start date> /to <String: end date> -> create event",
                        "bye -> close application",
                        horizontalLine
                )
        );
    }
}
