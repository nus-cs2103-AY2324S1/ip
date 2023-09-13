package duke.components;

/**
 * Enum is used to show the status of a Task. A task can either be done, or not done.
 */
public enum Status {
    DONE {
        @Override
        public String toString() {
            return "1";
        }
    },
    NOT_DONE {
        @Override
        public String toString() {
            return "0";
        }
    };

    /**
     * Converts the status value stored in the data file to its enum equivalent.
     *
     * @param status the Status to convert.
     * @return the enum equivalent of the Status.
     */
    public static Status convertToStatus(int status) {
        if (status == 0) {
            return Status.NOT_DONE;
        } else {
            return Status.DONE;
        }
    }
}
