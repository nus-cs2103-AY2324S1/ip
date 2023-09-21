# J.A.R.V.I.S.
> A personal chatbot assistant to help you with your daily tasks.

## Types of Tasks
Jarvis supports 3 types of tasks: Todo, Deadline and Event.

1. Todo: Tasks that need to be done but have no deadline or time limit.
2. Deadline: Tasks that need to be done before a specific date and time.
3. Event: Tasks that start and end at a specific date and time. It spans over a period of time.

## Method of interaction
You can interact with Jarvis with a pre-defined set of commands following the format `command (optional arguments)`.
The list of all the commands that Jarvis supports is listed below.

- Add a task to the task list: `todo`, `deadline`, `event`
- Delete a task from the task list: `delete`
- Mark a task as done/undone: `mark`, `unmark`
- List all the tasks in the task list: `list`
- Find all tasks in the task list with a keyword: `find`
- Tag/untag a task: `tag`, `untag`
- Exit the program: `exit`

More details on the usage of each command can be found in the [Features](#features) section.

## Features 
> [!NOTE]
> 1. Words in <> represents compulsory arguments. 
> 2. Words in [] are optional arguments. They can be omitted.
> 3. Items with ... after them can be used multiple times, including zero times.
> 4. Commands like `mark`, `delete`, etc. require the index of the task in the task list as the argument. 
     The index starts from 1.

### Add a Task
- Todo task: `todo <task description>`. The task description cannot be empty.
- Deadline task: `deadline <task description> /by <datetime>`. The datetime must be in the format `yyyy/MM/dd HH:mm`.
  <br/> For example, `deadline return book /by 2020/09/18 23:59`.
- Event task: `event <task description> /from <start datetime> /to <end datetime>`. The datetime must be in the same
  format as the deadline command. 
  <br/> For example, `event project meeting /from 2020/09/18 14:00 /to 2020/09/18 16:00`.

### Mark a Task as Done/Undone
- Mark a task as done: `mark <task index>`. 
- Mark a task as undone: `unmark <task index>`. 

### Delete a Task
- Delete a task: `delete <task index>`.

### Find Tasks
- Find all tasks containing the keyword: `find <keyword>`. The keyword cannot be empty. The tasks will be listed in the
  order they were added to the task list.

### List All Tasks in the Task List
- List all tasks: `list`. Do not provide any arguments for this command.

### Tagging
- Tag a task: `tag <task index> <tag name 1> [tag name 2]...`. The tag name cannot be empty. Multiple tags can 
  be added to a task.
- Untag a task: `untag <task index> <tag name 1> [tag name 2]...`. Same restrictions apply as the tag command.  
  Note that when the tag name supplied is not present in the task, it will be ignored and there is no error message.

### Exit
- Exit Jarvis: `exit`. Do not provide any arguments for this command.