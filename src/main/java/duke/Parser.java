

package duke;
public class Parser {
    private String userCommand;
    public Parser(String command) {
        this.userCommand = command;
    }

    public void parseAndRespond() {
        String[] splitted = userCommand.split(" ", 2);
        switch (splitted[0]) {
            case "list":
                Ui.listTasks();
                break;
            case "mark":
                if (splitted.length <= 1) {
                    Ui.markErr();
                } else {
                    int task_no = Integer.parseInt(splitted[1]);
                    TaskList.mark(task_no);
                    Storage.save();
                }
                break;
            case "unmark":
                if (splitted.length <= 1) {
                    Ui.unmarkErr();
                } else {
                    int taskNo = Integer.parseInt(splitted[1]);
                    TaskList.unmark(taskNo);
                    Storage.save();
                }
                break;
            case "todo":
                if (splitted.length <= 1) {
                    Ui.noDescription("todo");
                } else {
                    String todoTask = splitted[1];
                    Todo newTodo = new Todo(todoTask);
                    TaskList.add(newTodo, "todo");
                }
                break;
            case "deadline":
                if (splitted.length <= 1) {
                    Ui.noDescription("deadline");
                } else {
                    String[] deadTask = splitted[1].split("/by");
                    if (deadTask.length == 1) {
                        Ui.noTime("deadline");
                    } else {
                        String deadDescription = deadTask[0];
                        String deadTime = deadTask[1];
                        deadTime = deadTime.trim();
                        Deadline deadlineTask = new Deadline(deadDescription, deadTime);
                        TaskList.add(deadlineTask, "deadline");
                    }
                }
                break;
            case "event":
                if (splitted.length <= 1) {
                    Ui.noDescription("event");
                } else {
                    String[] eventTask = splitted[1].split("/from");
                    if (eventTask.length == 1) {
                        Ui.noTime("start time");
                    } else {
                        String eventDescription = eventTask[0];
                        String[] startEnd = eventTask[1].split("/to");
                        if (startEnd.length == 1) {
                            Ui.noTime("end time");
                        } else {
                            String eventStart = startEnd[0];
                            String eventEnd = startEnd[1];
                            Event newEvent = new Event(eventDescription, eventStart, eventEnd);
                            TaskList.add(newEvent, "event");
                        }
                    }
                }
                break;
            case "delete":
                if (splitted.length <= 1) {
                    Ui.deleteErr();
                } else {
                    int target = Integer.parseInt(splitted[1]);
                    TaskList.delete(target);
                }
                break;
            default:
                Ui.noCommand();
        }
    }
}
