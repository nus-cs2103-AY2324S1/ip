package pogo.tasks;

import java.util.List;

/**
 * Interface for encoding and decoding tasks to some format.
 */
public interface TaskEncoder {
    String encode(List<Task> tasks);
    List<Task> decode(List<String> lines);
}