//zh: thanks to all the friends who told me that I should pay attention to Level-4 while doing Level-2 to avoid tedium
//zh: consequently, I did levels 2-4 at the same time
public abstract class Task {

    //region Fields

    private String description;
    private boolean isDone = false;
    private Parser parser = new Parser('/');

    //endregion

    //region Constructor

    private Task(String desc) {
        this.description = desc;
    }

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

    public void setDescription(String description) {
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
        public Todo(String desc) {
            super("");
            super.setDescription(super.parser.load(desc).parse().arg);
        }
    }

    public static class Deadlines extends Task {

        private String dueDate;

        public Deadlines(String desc, String date) {
            super(desc);
            this.dueDate = date;
        }

        public Deadlines(String args) {
            super("");
            Parser parser = super.parser;
            parser.load(args);
            Parser.CmdArg cmdArg = parser.parse();
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
        }

        @Override
        public String toString() {
            return super.toString() + " [due: " + this.dueDate + "]";
        }
    }

    public static class Events extends Task {

        private String eventStart;
        private String eventEnd;

        public Events(String desc, String start, String end) {
            super(desc);
            this.eventStart = start;
            this.eventEnd = end;
        }

        public Events(String args) {
            super("");
            Parser parser = super.parser;
            parser.load(args);
            Parser.CmdArg cmdArg = parser.parse();
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
        }

        @Override
        public String toString() {
            return super.toString() + " [from: " + this.eventStart + " | to: " + this.eventEnd + "]";
        }
    }

    //endregion

}
