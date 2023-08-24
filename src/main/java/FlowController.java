import java.util.*;

public abstract class FlowController {

    //makes class EFFECTIVELY FINAL
    private FlowController() {}

    public enum Commands {
        NULL(null), LIST("list"), BYE("bye"), MARK("mark"),
        TODO("todo"), DEADLINE("deadline"), EVENT("event");
        private final String invocation;

        private Commands(String invocation) {
            this.invocation = invocation;
        }
        private static final HashMap<String, Commands> cmdset = new HashMap<>();

        public static Commands match(String s) {
            if (s == null) return Commands.NULL;
            Commands match = cmdset.get(s.toLowerCase());
            if(match == null) return Commands.NULL;
            else return match;
        }

        static {
            for (Commands cmd : values()) {
                cmdset.put(cmd.invocation, cmd);
            }
        }
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Task task;
        IOFormatter io = IOFormatter.ioFormatter;
        Parser parser = new Parser(' ');
        io.start();
        Commands cmd;
        mainLoop: while(true) {
            io.listen();
            cmd = Commands.match(io.getCommand());
            switch(cmd) { //todo think about doing something with the extra /commands
                case LIST:
                    io.send(taskList);
                    break;
                case TODO:
                    task = new Task.Todo(io.getArgs());
                    taskList.add(task);
                    io.send("Added: " + taskList.size() + ". " + task);
                    break;
                case EVENT:
                    task = new Task.Events(io.getArgs());
                    taskList.add(task);
                    io.send("Added: " + taskList.size() + ". " + task);
                    break;
                case DEADLINE:
                    task = new Task.Deadlines(io.getArgs());
                    taskList.add(task);
                    io.send("Added: " + taskList.size() + ". " + task);
                    break;
                case MARK: //todo acknowledge
                    taskList.get(Integer.parseInt(io.getArgs())-1).toggleDone(); //todo index out of bounds
                    break;
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
