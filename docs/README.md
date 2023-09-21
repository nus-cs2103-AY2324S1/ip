# User Guide

Bob is a Chatbot that can help free your mind of having to remember things you need to do! It helps you record down your todos, deadlines or events using a CLI within a custom GUI.

## Features
### Notes about the command format:
- Words within curly brackers `{}` are the parameters to be supplied by the user.
  e.g. in `todo {description}`, `{description}` is a parameter which can be used as add `todo do laundry`.

- Extraneous parameters for commands that do not take in parameters (such as list and bye) will be ignored.
e.g. if the command specifies `list 123`, it will be interpreted as `list`.

- Items with …​ after them can be used multiple times.
e.g. `mark {index}…`​ can be used as `mark 2 4 5`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.

## Usage

### Feature - Adding a todo

Adds a todo task to the task list.

Format: `todo {description}`

Examples:
- `todo Visit Italy`
- `todo Wash car`

### Feature - Adding a deadline

Adds a task with a deadline to the task list.

Format: `deadline {description} /by {due_date}`

Tip 💡: `{due_date}` can accept dates in the pattern - `yyyy-mm-dd`

Examples: 
- `deadline Essay submission /by 2023-10-10`
- `deadline Submit bank statemnet /by Monday`

### Feature - Add an event

Adds an event with a start date and an end date to the task list.

Format: `event {description} /from {start_date} /to {end_date}`

Tip 💡: Both start and end dates can accept dates in the pattern - `yyyy-mm-dd`

Examples:
- `event Dining with Master /from Sunday 2pm /to 4pm`
- `event Flower festival /from 2023-10-10 /to 2023-10-15`

### Feature - List

Lists out all current todos, deadlines and events.

Format: `list`

### Feature - Marking tasks as done

Marks one or multiple task as completed in the task list. If a task is already completed, the completion status of the task will remain unchanged.

Format: `mark {index}...`
- Marks the task at the specified `{index}` as completed.
- The index refers to the index number shown in the displayed task list after `list` command.
- The index ***must be a positive integer*** 1, 2, 3, ...

Examples:
- `list` followed by `mark 2 3` marks the 2nd and 3rd task in the task list as completed.

### Feature - Unmarking completed tasks 

Unmarks one or multiple completed task as incomplete in the task list. If a task is incompleted, the completion status of the task will remain unchanged.

Format: `unmark {index}...`
- Unmarks the task at the specified `{index}` as incompleted.
- The index refers to the index number shown in the displayed task list after `list` command.
- The index ***must be a positive integer*** 1, 2, 3, ...

Examples:
- `list` followed by `unmark 1` marks the 1st task in the task list as incomplete.


### Feature - Deleting a task

Deletes one or multiple tasks from the task list.

Format: `delete {index}...`
- Deletes the task at the specified `{index}`
- The index refers to the index number shown in the task list after `list` command.
- The index ***must be a positive integer*** 1, 2, 3, …​

Examples:
- `list` followed by `delete 2 3` deletes the 2nd and 3rd task in the task list.

### Feature - Finding a Task

Finds a task which has a description containing the give keyword.

Format: `find {keyword}`

- The search is *case sensitive*. e.g `apple` will not match with `Apple`.
- Only the descriptions will be searched.
- Partial words will be matched e.g. `ea` will match wit `eat`

Examples:
- `find Flower` returns task with descriptions `Flower Festival` and `Flower Dance`
- `find Geo` returns task with description `Eat lunch with George`;

### Feature - Exit the program

Exits the program.

Format: `bye`

### Feature - Saving and Loading Data

The data of your task list is saved in the hard disk automatically after any command that changes the data is executed.
There is no need to save manually.
When starting the program in a future session, the previously saved task list will be loaded automatically as well.
