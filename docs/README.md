# DevyBot User Guide

DevyBot is a powerful task-tracking chatbot designed to help you manage your tasks efficiently. This user guide outlines the various features and how to use them effectively.

## Features

1. **Adding a Task According to Its Type**
   - a. Todo Task:
   - b. Event Task:
   - c. Deadline Task:

2. **Marking/Unmarking Tasks**
   - Keep track of tasks as completed or not.

3. **Delete Task**
   - Delete added tasks.

4. **View All Tasks**
   - Display all tasks in chronological order of when they were added.
   - Includes task type and completion status.
   - Provides index for marking, unmarking, and deleting tasks.

5. **Save Tasks**
   - Automatically adds tasks to a database upon creation for persistent storage.

6. **Load Tasks**
   - Instantly access previously added tasks when entering the application.

7. **Find Tasks**
   - Finds tasks based on keywords in description.

8. **Search Tasks**
   - Better searches for tasks based on description & dates.

## Usage

### `todo` - Adding a Todo Task
- Format: `todo DESCRIPTION`
  - Example: `todo Read a book`
  - Expected Outcome: DEVYBOT adds the Todo task and displays a success message.
  
  ```
  Got it. I've added this task:
  [T][ ] Read a book
  ```


### `deadline` - Adding a Deadline Task
- Format: `deadline DESCRIPTION /by DATE`
- Example: `deadline Submit report /by 10/12/2023 1500`
- Date format must be in either `d/M/yyyy HHmm` or `d/MM/yyyy`.
- Expected Outcome: DEVYBOT adds the Deadline task and displays a success message.

```
Got it. I've added this task:
[D][ ] Submit report (by: Oct 12 2023 3:00 PM)
```

### `event` - Adding an Event Task
- Format: `deadline DESCRIPTION /from STARTDATE /to ENDDATE`
- Example: `event Birthday party /from 1/3/2023 1000 /to 3/4/2023 2300`
- Date format must be in either `d/M/yyyy HHmm`.
- Expected Outcome: DEVYBOT adds the Deadline task and displays a success message.

```
Got it. I've added this task:
[E][ ] Birthday party (from: Mar 01 2023 10:00 AM to: Apr 03 2023 1100PM)
```


### `mark` - Marking a Task as Done
- Format: `mark INDEX`
- Example: `mark 1`
- The index **must be a positive integer** 1, 2, 3, ...
- Expected Outcome: DEVYBOT marks the specified task as done and displays a success message.
```
Nice! I've marked this task as done:
[T][X] Read a book
```

### `unmark` - unmarking an existing task in the list
- Format: `unmark INDEX`
- Example: `unmark 1`
- The index **must be a positive integer** 1, 2, 3, ...
- Expected Outcome: DEVYBOT unmarks the specified task as done and displays a success message.
```
Nice! I've unmarked this task as done:
[T][X] Read a book
```

### `delete` - Deleting a Task
- Format: `delete INDEX`
- Example: `delete 2`
- The index **must be a positive integer** 1, 2, 3, ...
- Expected Outcome: DEVYBOT deletes the specified task and updates the task list.
Task deleted:
```
Noted, I've removed this task:
[D][ ] Submit Report (by: Dec 10 2023, 3:00PM)
```

### `list` - Viewing All Tasks
- Format: `list`
- Expected Outcome: DEVYBOT displays all tasks in the task list with their details.

```
Here are the tasks in your list:
[T][ ] Read a book
[D][ ] Submit report (by: Oct 12 2023 3:00 PM)
```


### `find` - Finding Tasks
- Format: `find KEYWORD`
- Example: `find book`
- Expected Outcome: Give users a way to find a task by searching for a keyword.

```
Here are the matching tasks in your list:
[T][ ] Read a book
```

### `search` - Searching Tasks
- Format: `search DETAIL`
- Example: `search oct`
- DEVYBOT provides search functionality, allowing you to search through existing tasks by keywords, descriptions, and dates.

```
Here are the matching tasks in your list:
[D][] Pay bills (by: Oct 01 2023 10:00 AM)
```


### `bye` - Exiting the Program
- Format: `bye`
- Expected Outcome: DEVYBOT displays an ending message and closes the application.

Feel free to explore these features to effectively manage your tasks with DEVYBOT!

