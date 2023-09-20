# User Guide
Skye is **desktop app for managing tasks** presented in the form of a chatbot. It is also optimized for use

## Features 

### Add To-Do

Adds a task that is flexible and not time bound.

### Add Deadline

Adds a task with a due date and time.

### Add Event

Adds a task with a start date / time and end date / time.

### Delete Task

Removes a task from a list of recorded tasks.

### List Tasks

Displays all tasks that were recorded.

### Find Tasks

Search for task descriptions that match a given keyword.

### Mark Task

Set a task as `done` or `not done`

### Check Due Tasks

Search for tasks due on a certain `date`

### Add Venue

Adds a venue name and its details: address, capacity and rent.

### Delete Venue

Removes a venue from a list of recorded venues.

### List Venues

List all venues that were recorded.

### Find Venues

Search for venue names that match a given keyword.

### Save Data

Saves all recorded tasks and venues. Data is automatically saved whenever a new task or venue is added or deleted.

## Usage

### Add a new todo: `todo <description>`

Create a ToDo task and adds it into a task list.

Example of usage: 

`todo Buy Eggs`

Expected outcome:

When successfully created, the chatbot responds with a creation success message.

```
Got it! I've added the following task:
    [T][] Buy Eggs
You've now 3 tasks in the list.
```

### Add a new deadline: `deadline <description> /by <dd-mm-yyyy hh:mm>`

Creates a Deadline task and adds it into a task list.

Example of usage:

`deadline Report Submission /by 27-09-2023 23:59`

Expected outcome:

When successfully created, the chatbot responds with a creation success message.

```
Got it! I've added the following task:
    [D][] Report Submission (by: 27 September 2023 23:59)
You've now 4 tasks in the list.
```

### Add a new event: `event <description> /from <dd-mm-yyyy hh:mm> /to <dd-mm-yyyy hh:mm>`

Creates an Event task and adds it into a task list.

Example of usage:

`event Marathon /from 01-10-2023 06:00 /to 01-10-2023 12:00`

Expected outcome:

When successfully created, the chatbot responds with a creation success message.

```
Got it! I've added the following task:
    [E][] Marathon (from: 01 October 2023 06:00 to: 01 October 2023 12:00)
You've now 5 tasks in the list.
```

### Delete a task: `delete tasks /index <task number>`

Removes a task from the task list.

Example of usage:

`delete tasks /index 4`

Expected outcome:

When successfully deleted, the chatbot will respond with a deletion success message.

```
Noted. I've removed this task:
    [D][] Report Submission (by: 27 September 2023 23:59)
You've now 4 tasks in the list.
```

### List all tasks: `list tasks`

Lists all tasks in the task list.

Expected outcome:

When successfully created, the chatbot will respond with a list of tasks.

```
Here are the tasks in your list:
    1. [T][] Buy Eggs
    2. [D][] Lab Submission
    3. [E][] Singapore GP
    4. [E][] Marathon
```

### Find tasks: `find tasks /q <keyword>`

Find tasks that description contains the keyword.

Example of usage:

`find tasks /q pore`

Expected outcome:
- If there are tasks that contain the keyword.
  - The chatbot will display a list of matching tasks.
- If there are no tasks that match the keyword.
  - The chatbot will display a message to indicate that no tasks are found. 

```
Here are the matching tasks in your list:
    1. [E][] Singapore GP
```

### Mark a task as complete: `mark <task number>`

Marks a task as complete.

Example of usage:

`mark 3`

Expected outcome:

The chatbot responds with a message indicating that the task has been marked as complete.

```
Good job! I've marked this task as done:
    [E][X] Singapore GP
```

### Unmark a task as incomplete: `unmark <task number>`

Marks a completed task as incomplete.

Example of usage:

`unmark 2`

Expected outcome:

The chatbot responds with a message indicating that the task has been unmarked.

```
OK, I've marked this task as not completed yet:
    [D][] Lab Assignment
```

### Check due tasks: `due <DD-MM-YYYY>`

Find and list tasks due on a given date.

Example of usage:

`due 27-09-2023`

Expected outcome:

The chatbot responds with a message indicating that the task has been unmarked.

```
Here are the tasks due on 2023-09-27:
    [D][] Lab Assignment
```

### Add a new venue: `venue <name> /address <address> /size <capacity> /rent <rent>`

Creates a Venue and adds to a venue list.

Example of usage:

`venue LT19 /address Computing Drive /size 250 /rent 10.99`

Expected outcome:

When successfully created, the chatbot responds with a creation success message.

```
Got it. I've added this venue:
    LT19 (Capacity: 250, Cost: 10.99)
Now you have 3 venue(s) in the list.
```

### Delete a venue: `delete venues /index 2`

Deletes a venue and removes the deleted venue from the venue list.

Example of usage:

`delete venues /index 3`

Expected outcome:

When successfully deleted, the chatbot responds with a creation delete message.

```
Noted. I've removed this venue:
    LT19 (Capacity: 250, Cost: 10.99)
```

### List venues: `list venues`

List all venues from the venue list

Example of usage:

`list venues`

Expected outcome:

The chatbot reponds with a list of venues from the venue list.

```
Here are the venues in your list:
    1. SR1 (Capacity: 300, Cost: 15.99)
    2. LT27 (Capacity: 400, Cost: 20.99)
    3. LT19 (Capacity: 250, Cost: 10.99)
```

### Find venues: `find venues /q <keyword>`

Finds venues from the venue list that names contain the keyword.

Example of usage:

`find venues /q LT`

Expected outcome:

The chatbot reponds with a list of venue names matching the keyword.

```
Here are the venues in your list:
    1. LT27 (Capacity: 400, Cost: 20.99)
    2. LT19 (Capacity: 250, Cost: 10.99)
```