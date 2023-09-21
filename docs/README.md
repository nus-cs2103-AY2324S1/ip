# User Guide for Kevin, formerly Duke

## Features

### Task Management

- **Types of Tasks**: You can manage tasks of various types including ToDo, Deadline, or Event.
- **Operations**: Create, modify, delete, and list tasks with ease.

### Command Interpretation

The Parser class translates string inputs from users into executable command objects. This ensures a seamless interaction with the task management features.

## Usage

### Task Handling

#### `todo` - Add a ToDo task
Example:
```
todo Buy milk
```
Expected outcome: The ToDo task is added.

#### `deadline` - Add a Deadline task
Example:
```
deadline Submit ip final version /by 2023-09-22
```
Expected outcome: The Deadline task is added.

#### `event` - Add an Event task
Example:
```
event Recess Week /from 2023-09-22 /to 2023-09-29
```
Expected outcome: The Event task is added.

#### `list` - Display all tasks
Example:
```
list
```
Expected outcome: A list of all your tasks will be displayed, sorted by the order in which they were added.

#### `mark` - Mark a task as done
Example:
```
mark 2
```
Expected outcome: Task number 2 is marked as done.

#### `unmark` - Unmark a task as done
Example:
```
unmark 2
```
Expected outcome: Task number 2 is unmarked.

#### `delete` - Delete a task
Example:
```
delete 1
```
Expected outcome: Task number 1 is deleted.

#### `find` - Find tasks by keywords
Example:
```
find groceries
```
Expected outcome: Displays tasks containing the keyword "groceries".

#### `sort` - Sort tasks
Example:
```
sort
```
Expected outcome: Tasks are sorted based on their type (Event tasks by start time, Deadline tasks by deadline, ToDo tasks at the end).

#### `clear` - Clear all tasks
Example:
```
clear
```
Expected outcome: All tasks are cleared.

### Miscellaneous

#### `bye` - Exit the program
Example:
```
bye
```
Expected outcome: Shows a goodbye message.

---