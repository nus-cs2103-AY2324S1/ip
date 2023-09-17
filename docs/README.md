# GeraldBot User Guide
GeraldBot is your ultimate task and contact manager, designed to simplify your task and contact management process and boost your productivity. This user guide will walk you through its key features and how to use them effectively.

## Features

### Task Management
GeraldBot makes managing your tasks a breeze. You can easily add, edit, and mark tasks as complete.

### Contact Management
Effortlessly manage your contacts with GeraldBot. Add, remove, and list your contacts quickly.

### Deadlines and Reminders (Coming Soon)
Never miss an important date or task with GeraldBot's upcoming deadlines and reminders feature.

### Customization (Coming Soon)
Personalize GeraldBot to match your workflow with our upcoming customization options.

## Usage
Here's how to use GeraldBot effectively:

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

### `deadline {task description}/by {deadline}` - Add a task with a deadline.

**Example of usage:**

`deadline Submit report/by 2023-09-30 14:00`

**Expected outcome:**

Adds a new task with the provided description and deadline.

### `event {task description}/by {start time}/{end time}` - Add an event.

**Example of usage:**

`event Team meeting/by 2023-09-28 10:00/2023-09-28 11:30`

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

### `find {keyword}` - Find tasks by keyword.

**Example of usage:**

`find report`

**Expected outcome:**

Lists all tasks containing the provided keyword.

### `contact list` - List all existing contacts.

**Example of usage:**

`contact list`

**Expected outcome:**

Lists all existing contacts.

### `contact add {contact name}/{phone number}/{email address}` - Add a contact.

**Example of usage:**

`contact add John Doe/123-456-7890/john@example.com`

**Expected outcome:**

Adds a new contact with the provided information.

### `contact remove {index}` - Remove a contact.

**Example of usage:**

`contact remove 1`

**Expected outcome:**

Removes the contact at the specified index.