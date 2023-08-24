public class InvalidTaskNumberException extends Exception {
    String taskNum;
    InvalidTaskNumberException(String taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String toString() {
        return String.format("Error: There is no task with task number %s!", this.taskNum);
    }
}