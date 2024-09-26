# Glen User Guide
Glen is your ultimate task manager, designed to simplify your task management process and boost your productivity. This user guide will walk you through its key features and how to use them effectively.

## Features

### Task Management
Glen makes managing your tasks a breeze. You can easily add, edit, and mark tasks as complete.

### Deadlines and Reminders (Coming Soon)
Never miss an important date or task with Glen's upcoming deadlines and reminders feature.

### Customization (Coming Soon)
Personalize Glen to match your workflow with our upcoming customization options.

## Usage
Here's how to use Glen effectively:

### `list` - List all the existing tasks.

**Example of usage:**

`list`

**Expected outcome:**

Lists all existing tasks.

### `todo {task description}` - Add a Todo task.

**Example of usage:**

`todo Buy groceries`

**Expected outcome:**

Adds a new Todo task with the provided description.

### `deadline {task description} /by {deadline}` - Add a task with a deadline.

**Example of usage:**

`deadline Submit report /by 2023-09-30 14:00`

**Expected outcome:**

Adds a new task with the provided description and deadline.

### `event {task description} /from {start time} /to {end time}` - Add an event.

**Example of usage:**

`event Team meeting /from today /to tomorrow night`

**Expected outcome:**

Adds a new event with the provided description, start time, and end time.

### `mark {index}` - Mark a task as completed.

**Example of usage:**

`mark 1`

**Expected outcome:**

Marks the task at the specified index as completed.

### `unmark {index}` - Mark a task as uncompleted.

**Example of usage:**

`unmark 1`

**Expected outcome:**

Marks the task at the specified index as uncompleted.

### `delete {index}` - Delete an existing task.

**Example of usage:**

`delete 1`

**Expected outcome:**

Deletes the task at the specified index.

**Example of usage:**

`delete all`

**Expected outcome:**

Deletes all tasks.

### `find {keyword}` - Find tasks by keyword.

**Example of usage:**

`find book`

**Expected outcome:**

Lists all tasks containing the provided keyword.