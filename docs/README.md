# Corgi - The Most Intelligent Chatbot

Corgi is a simple yet powerful task management chatbot designed to help you keep track of your tasks, deadlines, and events. Whether you're a busy professional, a student, or just someone looking to stay organized, Corgi has got you covered.

## Getting Started

To get started with Corgi, follow these simple steps:

1. **Download the JAR File**
   - Visit the "Releases" section of this [GitHub repository](https://github.com/NereusWB922/ip/releases).
   - Download the latest `corgi.jar` file to your computer.

2. **Installation**
   - Ensure that you have Java 11 (or above) installed on your system. You can download Java 11 from [Oracle's official website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or use an OpenJDK distribution.

3. **Run Corgi**
   - Navigate to the directory where you downloaded `corgi.jar`.
   - Run the following command to launch Corgi:
     ```
     java -jar corgi.jar
     ```

4. **Usage**
   - Start entering commands to add, manage, and track your tasks and events.
   - Refer to the the next section for a list of available commands and their formats.

## Features

Corgi supports a variety of commands to manage your tasks effectively:

1. **TODO**
   - Command: `todo /desc [task]`
   - Description: Add a new todo to your list.

2. **DEADLINE**
   - Command: `deadline /desc [task] /by [yyyy-mm-dd]`
   - Description: Add a task with a specific deadline.

3. **EVENT**
   - Command: `event /desc [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]`
   - Description: Add an event with a start and end date.

4. **MARK**
   - Command: `mark /target [task no.]`
   - Description: Mark a task as completed.

5. **UNMARK**
   - Command: `unmark /target [task no.]`
   - Description: Unmark a completed task.

6. **DELETE**
   - Command: `delete /target [task no.]`
   - Description: Delete a task or event.

7. **DATE**
   - Command: `date /target [yyyy-mm-dd]`
   - Description: Filter tasks and events by a specific date.

8. **FIND**
   - Command: `find /target [keyword]`
   - Description: Search for tasks or events containing a keyword.

9. **LIST**
    - Command: `list`
    - Description: View your list of tasks and events.

10. **UNDO**
    - Command: `undo`
    - Description: Undo the previous action.

11. **BYE**
    - Command: `bye`
    - Description: Exit the Corgi app.

