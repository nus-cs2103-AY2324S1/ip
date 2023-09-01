package aichan;

import aichan.command.Command;

public class AiChan {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public AiChan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (AiChanException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AiChanException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new AiChan("data/tasks.txt").run();
    /*
        String line = "_______________________________________________________________________\n";
        String greet = "Hiya! I'm Ai-chan~\n" +
                "Hey there, dear viewer, what's on your mind?\n" +
                "Is there anything I can do to sprinkle some magic into your day?\n";
        String bye = "Ta-da! It's time to go~ Keep smiling till we reunite!\n";

        Scanner scn = new Scanner(System.in);
        ArrayList<Task> arrTask = new ArrayList<>();

        System.out.println(line + greet + line);

        while (true) {
            int size = arrTask.size();
            try{
                String command = scn.nextLine();
                if (command.equals(ActionType.BYE.toString())) {
                    System.out.println(line + bye + line);
                    break;
                } else if (command.equals(ActionType.LIST.toString())){
                    System.out.print(line + "Here are the tasks in your list:\n");
                    int i = 1;
                    for (Task t : arrTask) {
                        System.out.println(i + "." + t.toString());
                        i++;
                    }
                    System.out.println(line);
                } else if (command.startsWith(ActionType.MARK.toString())){
                    if (command.length() < 6) {
                        throw new AiChanException("Please provide a task number.");
                    }
                    // get the number behind "mark "
                    int taskId = Integer.parseInt(command.substring(5));
                    if(taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
                    Task t = arrTask.get(taskId - 1);
                    t.mark();
                    System.out.println(line + "Nice! I've marked this task as done:\n"
                            + t.toString() + "\n" + line);
                } else if (command.startsWith(ActionType.UNMARK.toString())){
                    if (command.length() < 8) {
                        throw new AiChanException("Please provide a task number.");
                    }
                    // get the number behind "unmark "
                    int taskId = Integer.parseInt(command.substring(7));
                    if(taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
                    Task t = arrTask.get(taskId - 1);
                    t.unmark();
                    System.out.println(line + "OK, I've marked this task as not done yet:\n"
                            + t.toString() + "\n" + line);
                } else if (command.startsWith(ActionType.TODO.toString())){
                    if (command.length() < 6) {
                        throw new AiChanException("oops~ The description of a todo cannot be empty.");
                    } else if (command.charAt(4) != ' ') {
                        throw new AiChanException("Please leave a space behind 'todo'");
                    }
                    Task t = new ToDo(command.substring(5));
                    arrTask.add(t);
                    System.out.println(String.format("%sGot it. I've added this task:\n  %s\n" +
                            "Now you have %d tasks in the list\n%s", line, t, size + 1, line));
                } else if (command.startsWith(ActionType.DEADLINE.toString())){
                    if (command.length() < 10) {
                        throw new AiChanException("oops~ The description of a deadline cannot be empty.");
                    } else if (command.charAt(8) != ' ') {
                        throw new AiChanException("Please leave a space behind 'deadline'");
                    }
                    // split the substring behind "deadline " into two
                    String[] arr = command.substring(9).split(" /by ");
                    if(arr.length < 2) {
                        throw new AiChanException("Behind description, please provide the deadline after ' /by '");
                    }
                    Task t = new Deadline(arr);
                    arrTask.add(t);
                    System.out.println(String.format("%sGot it. I've added this task:\n  %s\n" +
                            "Now you have %d tasks in the list\n%s", line, t, size + 1, line));
                } else if (command.startsWith(ActionType.EVENT.toString())){
                    if (command.length() < 7) {
                        throw new AiChanException("oops~ The description of a event cannot be empty.");
                    } else if (command.charAt(5) != ' ') {
                        throw new AiChanException("Please leave a space behind 'event'");
                    }
                    // split the substring behind "event " into three elements
                    String[] arr = command.substring(6).split(" /from | /to ");
                    if(arr.length < 3) {
                        throw new AiChanException("Behind description, " +
                                "please provide \nthe respective date/time after ' /from ' and ' /to '");
                    }
                    Task t = new Event(arr);
                    arrTask.add(t);
                    System.out.println(String.format("%sGot it. I've added this task:\n  %s\n" +
                            "Now you have %d tasks in the list\n%s", line, t, size + 1, line));
                } else if (command.startsWith(ActionType.DELETE.toString())){
                    if (command.length() < 8) {
                        throw new AiChanException("Please provide a task number.");
                    }
                    // get the number behind "mark "
                    int taskId = Integer.parseInt(command.substring(7));
                    if(taskId < 1 || taskId > size) throw new AiChanException("Please provide a valid task number.");
                    Task t = arrTask.remove(taskId - 1);
                    System.out.println(String.format("%sNoted. I've removed this task:\n  %s\n" +
                            "Now you have %d tasks in the list\n%s", line, t, size - 1, line));
                } else {
                    throw new AiChanException("oops~ I'm so sorry, but I don't know what that means :'(");
                }
            } catch(AiChanException err) {
                System.out.println(line + err.getMessage() + "\n" + line);
            }
        }

     */
    }
}
