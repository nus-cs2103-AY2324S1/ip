//zh: thanks to all the friends who told me that I should pay attention to Level-4 while doing Level-2 to avoid tedium
//zh: consequently, I did levels 2-4 at the same time
public abstract class Task {

    //region Fields

    private String description;
    private boolean isDone = false;
    private Parser parser = new Parser('/');

    //endregion

    //region Constructor

    private Task() {}

    //endregion

    //region Getter/setter

    public boolean isDone() {
        return isDone;
    }

    public boolean toggleDone() {
        isDone = !isDone;
        return isDone;
    }

    public Task done() {
        isDone = true;
        return this;
    }
    public Task undone() {
        isDone = false;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws DescriptionIsEmptyError {
        if (description == null || description.isEmpty()) throw new DescriptionIsEmptyError();
        this.description = description;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "[" + (this.isDone? "X":" ") + "] " + this.description;
    }

    //endregion

    //region Subclasses

    public static class Todo extends Task {
        public Todo(String desc) throws ArgumentIsEmptyError {
            Parser.CmdArg cmdArg = super.parser.load(desc).parse();
            if (cmdArg == null) throw new DescriptionIsEmptyError();
            super.setDescription(cmdArg.arg);
        }
    }

    public static class Deadlines extends Task {

        private String dueDate;

        public Deadlines(String desc, String date) throws ArgumentIsEmptyError {
            super.setDescription(desc);
            this.dueDate = date;
            if (this.dueDate == null || dueDate.isEmpty()) throw new ArgumentIsEmptyError("due date", "by");
        }

        public Deadlines(String args) throws ArgumentIsEmptyError {
            Parser parser = super.parser;
            parser.load(args);
            Parser.CmdArg cmdArg = parser.parse();
            if (cmdArg == null) throw new DescriptionIsEmptyError();
            while (cmdArg != null) {
                switch(cmdArg.command) {
                    case "":
                        super.setDescription(cmdArg.arg);
                        break;
                    case "by":
                        this.dueDate = cmdArg.arg;
                        break;
                }
                cmdArg = parser.parse();
            }
            if (this.dueDate == null || dueDate.isEmpty()) throw new ArgumentIsEmptyError("due date", "by");
        }

        @Override
        public String toString() {
            return super.toString() + " [due: " + this.dueDate + "]";
        }
    }

    public static class Events extends Task {

        private String eventStart;
        private String eventEnd;

        public Events(String desc, String start, String end) throws ArgumentIsEmptyError {
            super.setDescription(desc);
            this.eventStart = start;
            this.eventEnd = end;
            if (this.eventStart == null || eventStart.isEmpty()) throw new ArgumentIsEmptyError("start date", "from");
            if (this.eventEnd == null || eventEnd.isEmpty()) throw new ArgumentIsEmptyError("end date", "to");
        }

        public Events(String args) throws ArgumentIsEmptyError {
            Parser parser = super.parser;
            parser.load(args);
            Parser.CmdArg cmdArg = parser.parse();
            if (cmdArg == null) throw new DescriptionIsEmptyError();
            while (cmdArg != null) {
                switch(cmdArg.command) {
                    case "":
                        super.setDescription(cmdArg.arg);
                        break;
                    case "from":
                        this.eventStart = cmdArg.arg;
                        break;
                    case "to":
                        this.eventEnd = cmdArg.arg;
                        break;
                }
                cmdArg = parser.parse();
            }
            if (this.eventStart == null || eventStart.isEmpty()) throw new ArgumentIsEmptyError("start date", "from");
            if (this.eventEnd == null || eventEnd.isEmpty()) throw new ArgumentIsEmptyError("end date", "to");
        }

        @Override
        public String toString() {
            return super.toString() + " [from: " + this.eventStart + " | to: " + this.eventEnd + "]";
        }
    }

    //endregion


    //I am not certain that this is a good design decision, but I will leave it because it is an optional goal,
    //and because I suppose I should take the opportunity to experiment with things I otherwise wouldn't
    public static class ArgumentIsEmptyError extends Exception {
        public final String arg;
        public final String invocation;

        public ArgumentIsEmptyError(String emptyArgument, String invocation) {
            this.arg = emptyArgument; this.invocation = invocation;
        }
    }
    public static class DescriptionIsEmptyError extends ArgumentIsEmptyError {
        public DescriptionIsEmptyError() {
            super("description", "");
        }
    }

}
