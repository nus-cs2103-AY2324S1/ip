# "Bell Curve God" User Guide
_**Bell Curve God**_ is a Personal Assistant Chatbot that helps you keep track of various things.

<details>

**<summary>Summary of Commands</summary>**

| [Create a new task](#create-a-new-task) | [Actions on existing task(s)](#modify-existing-tasks) | [Miscellaneous](#miscllaneous-commands) |
| :---: | :---: | :---: |
| [**`todo`**](#todo-description---create-a-task-without-specifying-any-time) | [**`list`**](#list---list-all-existing-tasks) | [**`help`**](#help---see-how-to-use-the-commands) |
| [**`deadline`**](#deadline-description-by-deadline-date---create-a-task-with-both-start-and-end-time) | [**`delete`**](#delete-index---delete-a-task) | [**`bye`**](#bye---exit-the-app) |
| [**`event`**](#event-description-from-start-date-to-end-date---create-a-task-with-a-deadline) | [**`find`**](#find-keyword---look-for-a-task-containing-the-keyword) |  |
|  | [**`mark`**](#mark-index---mark-a-task-as-done) |  |
|  | [**`unmark`**](#unmark-index---mark-a-task-as-not-done-yet) |  |

</details>

## Features 

### Add tasks of different types

Three types of tasks - **`Todo`**, **`Deadline`**, and **`Event`** - are supported.
Create a task without specifying the time, with a deadline, or with both start and end time, using [**these commands**](#create-a-new-task).

### Modify existing tasks to manage them

You can list existing tasks, delete one or more of them, look for the ones with some keyword, mark some as done and not done using [**these commands**](#modify-existing-tasks).

## Usage

### Create a new task
### `todo <description>` - Create a Task without specifying any time.
- Action: Creates a `Todo` Task with given description.
- Example of usage: `todo read book`
- Expected outcome:
  ```
  Got it. I've added this task:
  [T][] read book
  Now you have 1 tasks in the list.
  ```
  > `[T]` indicates that this task is a `Todo` task, `[]` means the task is not done yet.

### `deadline <description> /by <deadline date>` - Create a Task with both start and end time.
- Action: Creates an `Deadline` Task with given description and deadline date.
- Example of usage: `deadline return book /by 2023-09-20`
- Expected outcome:
  ```
  Got it. I've added this task:
  [D][] return book (by: 2023-09-20)
  Now you have 2 tasks in the list.
  ```
  > `[D]` indicates that this task is a `Deadline` task, `[]` means the task is not done yet.

### `event <description> /from <start date> /to <end date>` - Create a Task with a deadline.
- Action: Creates a `Event` Task with given description, start date and end date.
- Example of usage: `event tournament /from 2023-09-20 /to 2023-09-30`
- Expected outcome:
  ```
  Got it. I've added this task:
  [E][] tournament (from: 2023-09-20 to: 2023-09-30)
  Now you have 3 tasks in the list.
  ```
  > `[E]` indicates that this task is a `Deadline` task, `[]` means the task is not done yet.

### Modify existing tasks
### `list` - List all existing tasks.
- Action: Lists all the tasks you have.
- Example of usage: `list`
- Expected outcome:
  ```
  Here are the tasks in your list:
  1.[T][] read book
  2.[D][] return book (by: 2023-09-20)
  3.[E][] tournament (from: 2023-09-20 to: 2023-09-30)
  ```
  
### `delete <index>` - Delete a Task.
- Action: Deletes the task with given index.
- Example of usage: `delete 2`
- Expected outcome:
  ```
  Noted. I've removed this task:
  -[D][] return book (by: 2023-09-20)
  Now you have 2 tasks in the list.
  ```
  To check the task with index 2 is actually deleted, let's type `list` and see:
  ```
  Here are the tasks in your list:
  1.[T][] read book
  2.[E][] tournament (from: 2023-09-20 to: 2023-09-30)
  ```
  
  You can also delete multiple tasks in one go, by typing **`delete <index>,<index>,...`**
  > indices and commas should not be separated by any space
  > i.e. use `delete 1,2,3` instead of `delete 1, 2, 3`
- Example of usage: `delete 1,2`
- Expected outcome:
  ```
  Noted. I've removed this task:
  -[T][] read book
  -[E][] tournament (from: 2023-09-20 to: 2023-09-30)
  Now you have 0 task in the list.
  ```
  We can check again using `list`:
  ```
  There is no task in your list.
  ```

### `find <keyword>` - Look for a Task containing the keyword.
- Action: Lists all tasks containing the keyword in the description.
- Example of usage: `find book`
- Expected outcome:
  ```
  Here are the matching tasks in your list:
  1.[T][] read book
  2.[D][] return book (by: 2023-09-20)
  ```

### `mark <index>` - Mark a task as done.
- Action: Marks the task with given index as done.
- Example of usage: `mark 2`
- Expected outcome:
  ```
  Nice! I've marked this task as done:
  [D][X] return book (by: 2023-09-20)
  ```
  > `[X]` means the task is done.
  To check the task with index 2 is actually marked as done, let's type `list` and see:
  ```
  Here are the tasks in your list:
  1.[T][] read book
  2.[D][X] return book (by: 2023-09-20)
  3.[E][] tournament (from: 2023-09-20 to: 2023-09-30)
  ```
  
  You can also mark multiple tasks as done in one go, by typing **`mark <index>,<index>,...`**
  > indices and commas should not be separated by any space
  > i.e. use `mark 1,2,3` instead of `mark 1, 2, 3`
- Example of usage: `mark 1,3`
- Expected outcome:
  ```
  Nice! I've marked the following tasks as done:
  -[T][X] read book
  -[E][X] tournament (from: 2023-09-20 to: 2023-09-30)
  ```
  We can check again using `list`:
  ```
  Here are the tasks in your list:
  1.[T][X] read book
  2.[D][X] return book (by: 2023-09-20)
  3.[E][X] tournament (from: 2023-09-20 to: 2023-09-30)
  ```

### `unmark <index>` - Mark a task as not done yet.
- Action: Marks the task with given index as not done yet.
- Example of usage: `unmark 2`
- Expected outcome:
  ```
  Ok, I've marked this task as not done yet:
  [D][] return book (by: 2023-09-20)
  ```
  > `[]` means the task is not done yet.
  To check the task with index 2 is actually marked as not done, let's type `list` and see:
  ```
  Here are the tasks in your list:
  1.[T][X] read book
  2.[D][] return book (by: 2023-09-20)
  3.[E][X] tournament (from: 2023-09-20 to: 2023-09-30)
  ```
  
  You can also mark multiple tasks as not done in one go, by typing **`unmark <index>,<index>,...`**
  > indices and commas should not be separated by any space
  > i.e. use `unmark 1,2,3` instead of `unmark 1, 2, 3`
- Example of usage: `unmark 1,3`
- Expected outcome:
  ```
  Ok, I've marked the following tasks as not done yet:
  -[T][] read book
  -[E][] tournament (from: 2023-09-20 to: 2023-09-30)
  ```
  We can check again using `list`:
  ```
  Here are the tasks in your list:
  1.[T][] read book
  2.[D][] return book (by: 2023-09-20)
  3.[E][] tournament (from: 2023-09-20 to: 2023-09-30)
  ```

### Miscllaneous commands
### `help` - See how to use the commands.
- Action: Displays information about the commands.
- Example of usage: `help`
- Expected outcome:
  ```
  The following are the commands you can use:
  Commands related to creating new tasks:
  "todo"  "deadline"  "event"
  Commands related to existing tasks:
  "list"  "find"  "mark"  "unmark"  "delete"
  Misc:
  "bye"
  Type 'help <command>' and enter to see how you can use these commands.
  ```
  You can find out how a specific command works, following the instruction, i.e. `help todo`:
  ```
  Type 'todo <description>' and enter, the app will create a task with the given description.
  e.g. todo read book
  ```

### `bye` - Exit the app.
- Action: Exip the app and close the window. All task data will be stored locally at `./data/tasks.txt"`.
- Example of usage: `bye`
- Expected outcome:
  ```
  Bye. Hope to see you soon!
  ```
