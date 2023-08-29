public class DuckyInvalidTaskFormatException extends DuckyException {

    public DuckyInvalidTaskFormatException(String field, String taskType) {
        super(String.format("OOPS!!! The %s of a %s cannot be empty.", field, taskType));
    }
}
