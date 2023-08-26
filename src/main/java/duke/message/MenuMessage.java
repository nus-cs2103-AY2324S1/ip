package duke.message;

public class MenuMessage extends Message {
    @Override
    public void send() {
        System.out.println(
                createMessage(
                        " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                        horizontalLine,
                        "Here are the list of commands:",
                        "list: shows list of tasks",
                        "mark {task number}: mark task as done",
                        "unmark {task number}: unmark task as not done",
                        "todo {task name}: create a task",
                        "deadline {task name} /by {deadline}: create task with deadline",
                        "event {event name} /from {start date} /to {end date}: create event",
                        "bye: close application",
                        horizontalLine
                )
        );
    }
}
