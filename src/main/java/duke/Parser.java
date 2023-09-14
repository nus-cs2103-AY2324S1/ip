package duke;

/**
 * Parser class for the command
 */
public class Parser {
    //return "" to continue without action, any other command to fit
    private String command;
    private String[] parsedStr;
    /**
     * Parse the fullCommand and get the type of the command
     * @param fullCommmand everything the user entered in a line
     * @return type of the command
     */
    public String parseCommand(String fullCommmand) {
        String[] parsedStr = fullCommmand.split("\\s+");
        this.parsedStr = parsedStr;
        if (parsedStr[0].equals("f")) {
            this.command = "find";
            return "find";
        } else if (parsedStr[0].equals("ls")) {
            if (parsedStr.length > 1) {
                //error handlinrg
                System.out.println("Do not input another argument beside list");
                return "";
            }
            this.command = "list";
            return "list";
        } else if (parsedStr[0].equals("m")) {
            if (parsedStr.length < 2) {
                //error handling
                System.out.println("Please specify the index of the task");
                return "";
            } else if (parsedStr.length > 2) {
                System.out.println("Extra argument detected!");
                return "";
            }
            this.command = "mark";
            return "mark";
        } else if (parsedStr[0].equals("um")) {
            if (parsedStr.length < 2) {
                System.out.println("Please specify the index of the task");
                return "";
            } else if (parsedStr.length > 2) {
                System.out.println("Extra argument detected!");
                return "";
            }
            this.command = "unmark";
            return "unmark";
        } else if (fullCommmand.equals("bye")) {
            this.command = "bye";
            return "bye";
        } else if (parsedStr[0].equals("td")) {
            int size = parsedStr.length;
            if (size < 2) {
                //error handling
                System.out.println("You do not specify the todo name");
                return "";
            }
            this.command = "todo";
            return "todo";
        } else if (parsedStr[0].equals("ddl")) {
            this.command = "deadline";
            return "deadline";
        } else if (parsedStr[0].equals("e")) {
            this.command = "event";
            return "event";
        } else if (parsedStr[0].equals("dlt")) {
            this.command = "delete";
            return "delete";
        }
        this.command = "";
        return "";
    }

    /**
     * Parse query for the find command
     * @return the search query
     */
    public String parseQuery() {
        if (command.equals("find")) {
            //assume only one word query, no error handling yet
            return parsedStr[1];
        }
        return "";
    }
    /**
     * Parse the index that used by "mark", "unmark" or delete
     * run after parseCommand
     * @return the index, in 0-indexed form
     */
    public int parseToIndex() {
        if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
            int index = Integer.parseInt(parsedStr[1]) - 1;
            return index;
        }
        System.out.println("invalid operation");
        return -1;
    }

    /**
     * parse the command into task for todo, deadline and event
     * run after parseCommand
     * @return a Task
     */
    public Task parseToTask() {
        //rely on last method, last method must be called before this
        assert (this.command.equals("todo"));
        if (this.command.equals("todo")) {
            int size = this.parsedStr.length;
            String description = "";
            for (int i = 1; i < size; i++) {
                description += this.parsedStr[i] + " ";
            }
            if (description.equals("")) {
                System.out.println("Please input the name of the todo task");
                return null;
            }
            return new Todo(description);
        } else if (command.equals("deadline")) {
            boolean reached = false;
            String byDate = "";
            String description = "";
            int length = parsedStr.length;
            for (int i = 1; i < length; i++) {
                if (parsedStr[i].equals("/by")) {
                    reached = true;
                    continue;
                }
                if (reached == true) {
                    byDate += parsedStr[i] + " ";
                } else {
                    description += parsedStr[i] + " ";
                }
            }
            //error handling
            if (description.equals("")) {
                System.out.println("Please input the name of the deadline task");
                return null;
            } else if (byDate.equals("")) {
                System.out.println("Please specify when is the deadline");
                return null;
            }
            return new Deadline(description, byDate);
        } else if (command.equals("event")) {
            boolean reachFrom = false;
            boolean reachTo = false;
            String fromDate = "";
            String toDate = "";
            String description = "";
            for (int i = 1; i < parsedStr.length; i++) {
                if (parsedStr[i].equals("/from")) {
                    reachFrom = true;
                    continue;
                } else if (parsedStr[i].equals("/to")) {
                    reachTo = true;
                    continue;
                }
                if (reachFrom == true && reachTo == false) {
                    fromDate += parsedStr[i] + " ";
                } else if (reachFrom == false && reachTo == false) {
                    //part for description
                    description += parsedStr[i] + " ";
                } else {
                    toDate += parsedStr[i] + " ";
                }
            }
            if (description.equals("")) {
                System.out.println("Please input the name of the event");
                return null;
            } else if (fromDate.equals("")) {
                System.out.println("Please specify start time");
                return null;
            } else if (toDate.equals("")) {
                System.out.println("Please specify the end time");
                return null;
            }
            return new Event(description, fromDate, toDate);
        }
        return null;
    }
}
