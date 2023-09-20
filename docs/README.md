# DEVYBOT User Guide

DEVYBOT is a powerful task-tracking chatbot designed to help you manage your tasks efficiently. This user guide outlines the various features and how to use them effectively.

## Features

1. **Adding a Task According to Its Type**
   - a. Todo Task:
     - Format: `todo DESCRIPTION`
   - b. Event Task:
     - Format: `event DESCRIPTION /from STARTDATE /end ENDDATE`
     - Date format must be in either `d/M/yyyy HHmm`.
   - c. Deadline Task:
     - Format: `deadline DESCRIPTION /by DATE`
     - Date format must be in either `d/M/yyyy HHmm` or `d/MM/yyyy`.

2. **Marking/Unmarking Tasks**
   - Keep track of tasks as completed or not.
   - Format: `mark INDEX` to mark a task as done.

3. **Delete Task**
   - Format: `delete INDEX` to delete a task from the list of tasks and update the database.

4. **View All Tasks**
   - Display all tasks in chronological order of when they were added.
   - Includes task type and completion status.
   - Provides index for marking, unmarking, and deleting tasks.

5. **Save Tasks**
   - Automatically adds tasks to a database upon creation for persistent storage.

6. **Load Tasks**
   - Instantly access previously added tasks when entering the application.

7. **Find Tasks**
   - Search for specific tasks.

8. **Search Tasks**
   - Search for tasks using keywords.

## Usage

### `todo` - Adding a Todo Task
- Format: `todo DESCRIPTION`
  - Example: `todo Read a book`
  - Expected Outcome: DEVYBOT adds the Todo task and displays a success message.
  
  ```
  Task added:
  [T][ ] Read a book
  ```


### `deadline` - Adding a Deadline Task
- Format: `deadline DESCRIPTION /by DATE`
- Example: `deadline Submit report /by 10/12/2023 1500`
- Expected Outcome: DEVYBOT adds the Deadline task and displays a success message.

```
Task added:
[D][ ] Submit report (by: Oct 12 2023 1500)
```

### `mark` - Marking a Task as Done
- Format: `mark INDEX`
- Example: `mark 1`
- Expected Outcome: DEVYBOT marks the specified task as done and displays a success message.
```
Task marked as done:
[T][X] Read a book
```

### `delete` - Deleting a Task
- Format: `delete INDEX`
- Example: `delete 2`
- Expected Outcome: DEVYBOT deletes the specified task and updates the task list.
Task deleted:
```
[E][ ] Attend conference (from: Jan 1 2023 0900 to: Jan 2 2023 1800)
```

### `list` - Viewing All Tasks
- Format: `list`
- Expected Outcome: DEVYBOT displays all tasks in the task list with their details.
Here are your tasks:

```
[T][ ] Read a book
[D][ ] Submit report (by: Oct 12 2023 1500)
```


### `find` - Finding Tasks
- Format: `find KEYWORD`
- Example: `find book`
- Expected Outcome: DEVYBOT displays tasks containing the keyword.

```
Tasks found:
[T][ ] Read a book
```

### `search` - Searching Tasks
- Format: `search DETAIL`
- DEVYBOT provides search functionality, allowing you to search through existing tasks by keywords, descriptions, and dates.

### `bye` - Exiting the Program
- Format: `bye`
- Expected Outcome: DEVYBOT displays an ending message and closes the application.

Feel free to explore these features to effectively manage your tasks with DEVYBOT!

