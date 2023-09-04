import java.util.*;

public class FlowController {

    public void run() { //todo isolate meta-control into separate Catbot class, make FlowController singleton
        TaskList taskList = new TaskList();
        Task task; int index;
        IOFormatter io = IOFormatter.ioFormatter;
        Parser parser = new Parser(' ');
        io.start();
        Commands cmd;
        mainLoop: while(true) {
            io.listen();
            cmd = Commands.match(io.getCommand());
            switch(cmd) { //todo think about doing something with the extra /commands
                //experimenting with nested switch statements for readability and reduced redundancy
                case LIST:
                    io.send(taskList);
                    break;

                //cases: CREATE TASKS
                case TODO:
                case EVENT:
                case DEADLINE:
                    try {
                        switch (cmd) {
                            case TODO:
                                task = new Task.Todo(io.getArgs()); break;
                            case EVENT:
                                task = new Task.Events(io.getArgs()); break;
                            case DEADLINE:
                                task = new Task.Deadlines(io.getArgs()); break;
                            default:
                                throw new AssertionError("A task type is not handled");
                        }
                    } catch (Task.DescriptionIsEmptyError emptyError) {
                        io.warn("The " + emptyError.arg + " cannot be empty!");
                        io.send("Please write additional text after the " + cmd.invocation + " command to add a description.");
                        break;
                    } catch (Task.ArgumentIsEmptyError emptyError) {
                        io.warn("The " + emptyError.arg + " cannot be empty!");
                        io.send("Please write /" + emptyError.invocation + " followed by the " + emptyError.arg + ".");
                        break;
                    }
                    taskList.add(task);
                    io.send("Added: " + io.task(task, taskList.size()));
                    break;

                //cases: MANIPULATE TASKS
                case MARK:
                case UNMARK:
                case DELETE:
                    try {
                        index = Integer.parseInt(io.getArgs())-1;
                    } catch (NumberFormatException exception) {
                        io.warn("Number missing or unrecognisable.");
                        io.send("Please write the index of the task you want to " + cmd.invocation + ".");
                        break;
                    }
                    if (index >= taskList.size()) io.outOfBounds(index, taskList.size());
                    else {
                        task = taskList.get(index);
                        switch(cmd) {
                            case MARK:
                                if (task.isDone()) io.warn("Task is already done.");
                                else task.done();
                                io.send(io.task(task, index+1)); break;
                            case UNMARK:
                                if (!task.isDone()) io.warn("Task is already not done.");
                                else task.undone();
                                io.send(io.task(task, index+1)); break;
                            case DELETE:
                                taskList.remove(index);
                                io.send("Deleted task: " + task); break;
                            default:
                                throw new AssertionError("Unhandled task state transition");
                        }
                    }
                    break;

                //cases: MISC
                case NULL:
                    io.unexpected();
                    break;
                case BYE:
                    io.end();
                    break mainLoop;
            }
        }
    }

}
