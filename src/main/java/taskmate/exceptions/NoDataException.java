package taskmate.exceptions;

/**
 * The NoDataException class is a child class of the Exception class. It is thrown when there is an issue with reading
 * the file from the disk that stores the undeleted tasks. In particular, this exception is thrown when the file exists
 * on the disk, and it can be located, but the file content is empty.
 * It is thrown by one of the TaskList constructor when reading the file contents from the disk.
 */
public class NoDataException extends Exception {
}
