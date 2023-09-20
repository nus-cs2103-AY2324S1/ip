# Evan - User Guide

## Features 

### Adding Tasks into the list

We allow you to add 3 different types of tasks into the list.
1. ToDo Task
2. Deadline Task
3. Event Task

A ToDo task allows you to add a task without any dates specified.
A Deadline task allows you to add a task with 1 date/time specified as it's deadline.
An Event task allows you to add a task with 2 date/times. The first is specified as the start time and the second is specified as the end time of the event.

### View List

Users can view the entire list of their pending tasks.

### Delete Task

You can delete a specified task on the list.

### Task Status

Users can mark a task as incompleted or completed.

### Find Task

Users can find a particular task they want to see by searching keywords.

## Usage

### `list` - Show the entire list of tasks

All the user has to type is "list", no arguments required.

Format: `list`

Example of usage: 

`list`

Expected outcome:

The Chatbot will respond with a list of all the tasks that is tracked

```
Here are the tasks in your list:
1. [T][] Read Book
2. [D][X] Finish CS2100 Assignment (by: 18-Sep-2023 1300)
3. [D][X] Finish CS2103T ip (by: 22-Sep-2023)
4. [E][] NUSBS Dharma Camp (from: 1-Jul-2024 to: 2-Jul-2024)
```

### `todo` - Starts a process to add a ToDo entry into the list

All the user has to type is "todo", no arguments required.
Further follow-up actions are required to complete the insertion of the task as instructed by the chatbot.
<br>
Follow-up actions required:
1. Type in name of the task


Format: `todo`

Example of usage (2-steps): 

User types `todo`

Expected outcome:

The Chatbot will instruct users on follow up actions to create a ToDo task

```
So you want to add a ToDo task. Tell me what's the task.
```

User types in the `name of task`

Expected outcome:

The Chatbot will respond that the task has been inserted

```
Got it. I've added this task:
[T][] Read Book
Now you have 1 tasks in the list.
```

### `deadline` - Starts a process to add a Deadline entry into the list

All the user has to type is "deadline", no arguments required.
Further follow-up actions are required to complete the insertion of the task as instructed by the chatbot.
<br>
Follow-up actions required:
1. Type in name of the task
2. Type in date of deadline
3. (Optional) Type in time of deadline


Format: `deadline`

Example of usage (4-steps): 

User types `deadline`

Expected outcome:

The Chatbot will prompt for the name of the task.

```
So you want to add a task with deadline. Tell me what's the task.
```

User types in the `name of task`

Expected outcome:

The Chatbot will prompt for the deadline date

```
Now indicate the deadline date.
```

User types in the `date of deadline`

Expected outcome:

The Chatbot will prompt for the deadline time

```
Indicate a start time ranging from 0000 - 2359. You may enter 'Skip' to not indicate a time.
```

User types in the `time of deadline` or `skip`

Expected outcome:

The Chatbot will respond that the task has been inserted

```
Got it. I've added this task:
[D][] Finish CS2100 Assignment (by: 18-Sep-2023)
Now you have 2 tasks in the list.
```

### `event` - Starts a process to add a Event entry into the list

All the user has to type is "event", no arguments required.
Further follow-up actions are required to complete the insertion of the task as instructed by the chatbot.
<br>
Follow-up actions required:
1. Type in name of the task
2. Type in date of start date
3. (Optional) Type in time of start date
4. Type in date of end date
5. (Optional) Type in time of end date


Format: `event`

Example of usage (6-steps): 

User types `event`

Expected outcome:

The Chatbot will prompt for the name of the task.

```
So you want to add an event task. Tell me what's the task.
```

User types in the `name of task`

Expected outcome:

The Chatbot will prompt for the start date

```
Now indicate the start date.
```

User types in the `date of start date`

Expected outcome:

The Chatbot will prompt for the start time

```
Indicate a start time ranging from 0000 - 2359. You may enter 'Skip' to not indicate a time.
```

User types in the `start time` or `skip`

Expected outcome:

The Chatbot will prompt for the end date

```
Now indicate the end date.
```

User types in the `date of end date`

Expected outcome:

The Chatbot will prompt for the end time

```
Indicate an end time ranging from 0000 - 2359. You may enter 'Skip' to not indicate a time.
```

User types in the `end time` or `skip`

Expected outcome:

The Chatbot will respond that the task has been inserted

```
Got it. I've added this task:
[E][] NUSBS Dharma Camp (from: 1-Jul-2024 0930 to: 2-Jul-2024 1800)
Now you have 3 tasks in the list.
```

### `mark` - Marks a specified task in the list as complete

User has to type `mark` followed by the corresponding index number of the task in the list

Format: `mark INDEX`
- Deletes the task at the specified `INDEX`
- `INDEX` must not exceed the size of the list
- `INDEX` must be a positive number

Example of usage: 

`mark 1`

Expected outcome:

The Chatbot will respond that the task has been successfully marked as done

```
Nice! I've marked this task as done
[T][X] Read Book
```

### `unmark` - Marks a specified task in the list as incomplete

User has to type `unmark` followed by the corresponding index number of the task in the list

Format: `unmark INDEX`
- Deletes the task at the specified `INDEX`
- `INDEX` must not exceed the size of the list
- `INDEX` must be a positive number

Example of usage: 

`unmark 1`

Expected outcome:

The Chatbot will respond that the task has been successfully marked as incomplete

```
OK! I've marked this task as not done yet:
[T][] Read Book
```

### `delete` - Delete a specified task in the list

User has to type `delete` followed by the corresponding index number of the task in the list

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`
- `INDEX` must not exceed the size of the list
- `INDEX` must be a positive number

Example of usage: 

`delete 1`

Expected outcome:

The Chatbot will respond that the task has been successfully deleted

```
Noted. I've removed this task:
[T][] Read Book
Now you have 2 tasks in the list
```

### `find` - Finds task with the associated specified keyword/phrase

User has to type `find` followed by the keyword/phrase that they want to find.

Format: `find WORDS`
- Finds the task with the specified `WORDS`
- `WORDS` can be a part of a word or phrase (e.g. find accum would find tasks with the word "accumulator" in it) 

Example of usage: 

`find read`

Expected outcome:

The Chatbot will provide a list of the tasks with the word "read" in it
```
Here are the matching tasks in your list with its correct corresponding index numbers:
1.[T][] Read Book
```
Note - The Index number displayed in the chatbot response will correspond to the actual index number of the task in the full list
