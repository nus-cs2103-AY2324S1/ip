package Utils;

public abstract class Task {
    enum Type {
        TODO("[T]", 3),
        DEADLINE("[D]", 4),
        EVENT("[E]", 5);

        private final String name;
        private final int numParams;

        private Type(String name, int numParams) {
            this.name = name;
            this.numParams = numParams;
        }

        protected static Type of(String name) throws DukeException {
            for (Type type : values()) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
            throw new InvalidFileDataException();
        }

        protected int param() {
            return this.numParams;
        }

        @Override
        public String toString() {
            return this.name;
        }

    }

    private static final String MARKED_CHECKBOX = "[X]";
    private static final String UNMARKED_CHECKBOX = "[ ]";
    private String title;
    private Type type;
    private boolean checked;

    protected Task(String title, Type type) {
        this.title = title;
        this.type = type;
        this.checked = false;
    }

    protected String name() {
        return this.title;
    }

    protected Type type() {
        return this.type;
    }

    protected boolean marked() {
        return this.checked;
    } 

    protected void mark() {
        this.checked = true;
    }

    protected void unmark() {
        this.checked = false;
    }

    protected abstract String toCsv();

    @Override
    public String toString() {
        return String.format("%s %s",
            this.checked ? Task.MARKED_CHECKBOX : Task.UNMARKED_CHECKBOX,
            this.name()
          );
    }
}
