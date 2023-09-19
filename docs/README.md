# User Guide
TaskMate is a **desktop app for managing tasks,  optimized for use via a Command Line Interface (CLI)** while still retaining some Graphical User Interface (GUI) functions.

The two main features TaskMate offers are:
- adding tasks
- managing tasks
## Features - adding tasks

### Add ToDo task: `todo {taskDescription}`
Creates a new todo task with the specified description.

**Accepted values for parameters:**
- taskDescription: alphanumeric, national, and special characters allowed

**Example of usage:**

`todo homework 1`

**Expected outcomes:**
- Successfully added event task
```
I've added this task:
  [T][ ] homework 1
You have a total of 1 task.
```
- If description is empty
```
The description of a todo cannot be empty!
```
### Add Deadline task: `deadline {taskDescription} /by {deadline}`
Creates a new deadline task with the specified description and deadline.

**Accepted values for parameters:**
- taskDescription: alphanumeric, national, and special characters allowed
- deadline: Only numerical characters, in the following formats `year-month-date time` `year-month-date` `date/month/year time` `date/month/year`

**Example of usage:**

`deadline homework 2 /by 2023-9-27 1600`

**Expected outcomes:**
- Successfully added deadline task
```
I've added this task:
  [D][ ] homework 2 (by: Sep 27 2023 4:00PM)
You have a total of 2 tasks.
```
- If description is empty or missing '/by'
```
You are missing one or some of these inputs - description/ by.
```
- If deadline is invalid
```
Please use the following formats:
deadline task /by yyyy-mm-dd hhmm
deadline task /by dd/mm/yyyy hhmm
deadline task /by yyyy-mm-dd
deadline task /by dd/mm/yyyy
```
### Add Event task: event `{taskDescription} /from {startDateAndTime} /to {endDateAndTime}`
Creates a new event task with the specified description and start and end date and time.

**Accepted values for parameters:**
- taskDescription: alphanumeric, national, and special characters allowed
- startDateAndTime: Only numerical characters, in the following formats `year-month-date time` `year-month-date` `date/month/year time` `date/month/year`
- endDateAndTime: Only numerical characters, in the following formats `year-month-date time` `year-month-date` `date/month/year time` `date/month/year`

**Example of usage:**

`event party /from 23/9/2023 2359 /to 25/12/2023 2359`

**Expected outcomes:**
- Successfully added event task
```
I've added this task:
  [E][ ] party (from: Sep 23 2023 11:59PM to Dec 25 2023 11:59PM)
You have a total of 3 tasks.
```
- If description is empty or missing '/from' or '/to'
```
You are missing one or some of these inputs - description/ from/ to.
```
- If start or end date and time is invalid
```
Please use the following formats:
deadline task /by yyyy-mm-dd hhmm
deadline task /by dd/mm/yyyy hhmm
deadline task /by yyyy-mm-dd
deadline task /by dd/mm/yyyy
```
- If end date and time is earlier than start date and time
```
To date cannot be before from date.
```

## Features - managing tasks
### List all tasks: `list`
Shows a list of all tasks.

**Example of usage:**

`list`

**Expected outcomes:**
- Successfully viewed tasks
```
Here are your tasks:
1.[T][ ] homework 1
2.[D][ ] homework 2 (by: Sep 27 2023 4:00PM)
3.[E][ ] party (from: Sep 23 2023 11:59PM to Dec 25 2023 11:59PM)
```
- If no tasks are added
```
You have 0 task.
```
### Mark task: `mark {taskNumber}`
Marks a task as done by its index.

**Accepted values for parameters:**
- taskNumber: Only numerical characters allowed

**Example of usage:**

`mark 2`

**Expected outcomes:**
- Successfully marked task
```
Great job! Task 2 is done!
```
- If task number is empty
```
Task to mark cannot be empty!
```
- If task number is not valid
```
Invalid task number.
```
- If there are no tasks to mark
```
There are no tasks to mark.
```
- If task is already marked as done
```
Task 2 is already done!
```
### Unmark task: `unmark {taskNumber}`
Unmarks a task by its index.

**Accepted values for parameters:**
- taskNumber: Only numerical characters allowed

**Example of usage:**

`unmark 2`

**Expected outcomes:**
- Successfully unmarked task
```
Okay, I've updated Task 2 to be incomplete.
```
- If task number is empty
```
Task to unmark cannot be empty!
```
- If task number is not valid
```
Invalid task number.
```
- If there are no tasks to unmark
```
There are no tasks to unmark.
```
- If task is already unmarked
```
Task 2 is still incomplete.
```
### Delete task: `delete {taskNumber}`
Deletes a task by its index.

**Accepted values for parameters:**
- taskNumber: Only numerical characters allowed

**Example of usage:**

`delete 3`

**Expected outcomes:**
- Successfully deleted task
```
This task has been removed:
  [E][ ] party (from: Sep 23 2023 11:59PM to Dec 25 2023 11:59PM)
You have a total of 2 tasks.
```
- If task number is empty
```
Task to be deleted cannot be empty!
```
- If task number is not valid
```
Invalid task number.
```
- If there are no tasks to delete
```
There are no tasks to be deleted.
```
### Find task: `find {keyword}`
Finds matching task(s) by the specified keyword.

**Accepted values for parameters:**
- keyword: alphanumeric, national, and special characters allowed

**Example of usage:**

`find homework`

**Expected outcomes:**
- Successfully found matching tasks
```
Here are the matching tasks in your list:
1.[T][ ] homework 1
2.[D][ ] homework 2 (by: Sep 27 2023 4:00PM)
```
- If no matching tasks found
```
No matching tasks found.
```