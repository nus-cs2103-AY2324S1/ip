public class Parser {
    //return "" to continue without action, any other command to fit
    private String command;
    private String[] parsedStr;
    public String parseCommand(String fullCommmand) {
        String parsed_str[] = fullCommmand.split("\\s+");
        this.parsedStr = parsed_str;
        if (parsed_str[0].equals("list")) {
            if (parsed_str.length > 1) {
                //error handling
                System.out.println("Do not input another argument beside list");
                return "";
            }
            this.command = "list";
            return "list";
        } else if (parsed_str[0].equals("mark")) {
            if (parsed_str.length < 2) {
                //error handling
                System.out.println("Please specify the index of the task");
                return "";
            } else if (parsed_str.length > 2) {
                System.out.println("Extra argument detected!");
                return "";
            }
            this.command = "mark";
            return "mark";
        } else if (parsed_str[0].equals("unmark")) {
            if (parsed_str.length < 2) {
                System.out.println("Please specify the index of the task");
                return "";
            } else if (parsed_str.length > 2) {
                System.out.println("Extra argument detected!");
                return "";
            }
            this.command = "unmark";
            return "unmark";
        } else if (fullCommmand.equals("bye")) {
            this.command = "bye";
            return "bye";
        } else if (parsed_str[0].equals("todo")) {
            int size = parsed_str.length;
            if (size < 2) {
                //error handling
                System.out.println("You do not specify the todo name");
                return "";
            }
            this.command = "todo";
            return "todo";
        } else if (parsed_str[0].equals("deadline")) {
            this.command = "deadline";
            return "deadline";
        } else if (parsed_str[0].equals("event")) {
            this.command = "event";
            return "event";
        } else if (parsed_str[0].equals("delete")){
            this.command = "delete";
            return "delete";
        }
        this.command = "";
        return "";
    }

    public int parseToIndex() {
        if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
            int index = Integer.parseInt(parsedStr[1]) - 1;
            return index;
        }
        System.out.println("invalid operation");
        return -1;
    }

    public Task parseToTask() {
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
            String by_date = "";
            String description = "";
            int length = parsedStr.length;
            for (int i = 1; i < length; i++) {
                if (parsedStr[i].equals("/by")) {
                    reached = true;
                    continue;
                }
                if (reached == true) {
                    by_date += parsedStr[i] + " ";
                } else {
                    description += parsedStr[i] + " ";
                }
            }
            //error handling
            if (description.equals("")) {
                System.out.println("Please input the name of the deadline task");
                return null;
            } else if (by_date.equals("")) {
                System.out.println("Please specify when is the deadline");
                return null;
            }
            return new Deadline(description, by_date);
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
