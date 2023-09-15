# User Guide

Pooh is a Personal Assistant Chatbot that helps users to manage their day-to-day tasks.

## Features

### Creating Tasks

You can create up to 3 different types of tasks in Pooh:

- Todo - _Tasks that are to be done regardless of time_
- Event - _Tasks that are to be done between specified date / time_
- Deadline - _Tasks that are to be done by a certain date / time_

### Marking Tasks as Complete

You can mark your tasks to be done in Pooh once you have completed it.

### Listing Tasks

You can list all existing tasks to be done in Pooh.

### Deleting Tasks

You can delete existing tasks in Pooh.

### Finding Tasks

You can search through all your existing tasks in Pooh and find task(s) that matches your search keyword.

### Automatic Saving and Loading of Tasks

All tasks in Pooh are automatically saved into a local file as you enter commands, and are loaded when you start Pooh.

## Usage:

### `todo` - Adds a new todo task

Creates a todo task and adds it to the current list of tasks in Pooh.

Example of usage:

`todo buy honey`

Expected outcome:

A new todo will be created and added to the list.

```
Got it. I've added this task:
          [T][] buy honey
Now you have 1 task in the list
```

### `event` - Adds a new event task

Creates an event task and adds it to the current list of tasks in Pooh.

Example of usage:

`event buy honey /from Monday 4pm /to 6pm`

Expected outcome:

A new event will be created and added to the list.

```
Got it. I've added this task:
          [E][] buy honey (from: Monday 4pm to: 6pm)
Now you have 1 task in the list
```

### `deadline` - Adds a new deadline task

Creates a deadline task and adds it to the current list of tasks in Pooh.

Example of usage:

`deadline buy honey /by 2023-10-15`

Expected outcome:

A new deadline will be created and added to the list.

```
Got it. I've added this task:
          [D][] buy honey (by: Oct 15 2023)
Now you have 1 task in the list
```

### `mark` - Marks an existing task as done

Marks the task with the specified index as done in Pooh.

Example of usage:

`mark 1`

Expected outcome:

Tasks marked done will have a visible `X` within the list.

```
Nice! I've marked this task as done:
          [D][X] buy honey (by: Oct 15 2023)
```

### `unmark` - Marks an existing task as undone

Mark a task with the specified index undone in Pooh.

Example of usage:

`unmark 1`

Expected outcome:

Tasks marked undone will have existing `X` removed.

```
OK, I've marked this task as not done yet:
          [D][] buy honey (by: Oct 15 2023)
```

### `delete` - Deletes an existing task

Deletes the task with the specified index.

Example of usage:

`delete 1`

Expected outcome:

Deleted tasks will no longer be present in the tasklist.

```
Noted. I've removed this task:
          [D][] buy honey (by: Oct 15 2023)
Now you have 0 tasks in the list
```

### `find` - Searches existing tasks

Finds all existing tasks matching the specified keyword(s)

Example of usage:

`find honey`

Expected outcome:

Displays the list of all tasks with matching keywords.

```
Here are the matching tasks in your list:
          1. [D][] buy honey (by: Oct 15 2023)
```

### `postpone` - Postpone a deadline task

Postpone / update the time of a deadline task.

Example of usage:

`postpone 1 2023-10-20`

Expected outcome:

List is updated, where deadline task now has a new deadline.

```
OK, I have postponed this task:
          1. [D][] buy honey (by: Oct 20 2023)
```