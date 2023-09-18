package Frenchie;

public class Parser {
     public static Command parseCommand(String input) {
         String[] parts = input.split(" ");
         String command = parts[0];

         switch(command) {
             case "list":
                 return Command.list;
             case "mark":
                 return Command.mark;
             case "unmark":
                 return Command.unmark;
             case "todo":
                 return Command.todo;
             case "deadline":
                 return Command.deadline;
             case "event":
                 return Command.event;
             case "bye":
                 return Command.bye;
             case "delete":
                 return Command.delete;
             default:
                 return Command.invalid;

         }
     }

     public static String parseDetails(String input) {
         String[] parts = input.split(" ", 2);
         return (parts.length > 1) ? parts[1] : "";
     }

    /**public void parseAndExecute(String input) {
        try {
            if (input.equals("list")) { //Checking if user is looking to list all tasks
            frenchie.listTasks();
            } else if (input.contains("mark")) { //Checking if user input is to mark/unmark tasks
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Frenchie.Frenchie.Frenchie.Task target_task = frenchie.tasks.get(index);
                //Checking if user is looking to mark task as done or incomplete
                if (parts[0].equals("mark")) {
                    frenchie.completeTask(index);
                    System.out.println("____________________________________________________________\n" +
                            " Nice! I've marked this task as done: \n" +
                            target_task.toString() + "\n" +
                            "____________________________________________________________");
                } else {
                    frenchie.uncompleteTask(index);
                    System.out.println("____________________________________________________________\n" +
                            " OK, I've marked this task as not done yet: \n" +
                            target_task.toString() + "\n" +
                            "____________________________________________________________");
                }
            } else if (input.contains("delete")){
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                Frenchie.Frenchie.Frenchie.Task target_task = frenchie.tasks.get(index);
                frenchie.deleteTask(index);
                System.out.println("____________________________________________________________\n" +
                        "Noted. I've removed this task: \n" +
                        target_task.toString()   + "\n" +
                        "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                        "____________________________________________________________");
            } else if (input.contains("event") || input.contains("todo") || input.contains("deadline")) {
                String[] parts = input.split(" ");
                String taskType = parts[0];
                if (parts.length <= 1) {
                    throw new Frenchie.Frenchie.Frenchie.FrenchieException("____________________________________________________________\n" +
                            "OOPS!!! The description of a " + taskType + " cannot be empty.\n" +
                            "____________________________________________________________");
                } else {
                    if (taskType.equals("todo")) {
                        String taskName = input.split("todo")[1].trim();
                        Frenchie.Frenchie.Frenchie.ToDo currentTask = new Frenchie.Frenchie.Frenchie.ToDo(taskName);
                        frenchie.addTask(currentTask);
                        System.out.println("____________________________________________________________\n" +
                                " Got it! I've added this task: \n" +
                                currentTask + "\n" +
                                "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                                "____________________________________________________________");
                    } else if (taskType.equals("deadline")) {
                        String taskName = input.split("/by")[0].split("deadline")[1].trim();
                        String deadline = input.split("/by")[1].trim();
                        Frenchie.Frenchie.Frenchie.Deadline currentTask = new Frenchie.Frenchie.Frenchie.Deadline(taskName, deadline);
                        frenchie.addTask(currentTask);
                        System.out.println("____________________________________________________________\n" +
                                " Got it! I've added this task: \n" +
                                currentTask + "\n" +
                                "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                                "____________________________________________________________");
                    } else {
                        String taskName = input.split("/")[0].split("event")[1].trim();
                        String startTime = input.split("/from")[1].split("/to")[0].trim();
                        String endTime = input.split("/from")[1].split("/to")[1].trim();
                        Frenchie.Frenchie.Frenchie.Event currentTask = new Frenchie.Frenchie.Frenchie.Event(taskName, startTime, endTime);
                        frenchie.addTask(currentTask);
                        System.out.println("____________________________________________________________\n" +
                                " Got it! I've added this task: \n" +
                                currentTask + "\n" +
                                "Now you have " + frenchie.getNumOfTasks() + " tasks in the list.\n" +
                                "____________________________________________________________");
                    }
                }
            } else {
                throw new Frenchie.Frenchie.Frenchie.FrenchieException("____________________________________________________________\n" +
                        "OOPS!!! I'm sorry but I don't know what that means! :-(\n" +
                        "____________________________________________________________");
            }
            frenchie.saveTasksToFile();
        } catch (Frenchie.Frenchie.Frenchie.FrenchieException e) {
        System.err.println(e.getMessage());
    }
    }**/
}


