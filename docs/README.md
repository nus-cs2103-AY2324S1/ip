# Duke Project Pro
> “Your mind is for having ideas, not holding them.” – David Allen

DukePro frees your mind of having to remember things you need to do. It's,
- Text-based
- Best way to learn
- Easy and Simple to use

All you need to do is,

1. Download from [link](https://nus-cs2103-ay2324s1.github.io/website/schedule/week4/project.html)
2. Double click it
3. Add your tasks
4. Let it manage your task for you :blush:
It is also *Emphasize* Free

## User Guide
> Words in `UPPER_CASE` are the parameters to be supplied by the user. For example, if users type in `task TASK`, the `TASK` is a parameter which can be used as the name of the task user wants to fill it in 

### Adding a task : `task`
Adds a task to remind users what to do


Format : `task TASK` 
| Examples | 
| --- |
| `task Study CS2103T` | 
| `task Revise CS2103T with John Doe` | 

### Adding an event : `event`
Adds an event to remind users of a one-time activity


Format : `event NAME /from DAY TIME /to DAY TIME` where `TIME` is in this format `[NUMBER][am/pm]` 
| Examples | 
| --- |
| `event Supernova /from Mon 2pm /to Tue 2pm` | 
| `event CS2103T /from Mon 2pm /to Sun 2pm` | 


### Adding a deadline: `deadline`
Adds a deadline to remind users to do something before the due date


Format : `deadline NAME /by DD/MM/YYYY` 
| Examples | 
| --- |
| `deadline return book /by 02/12/2019` | 
| `deadline CS2103T IP /by 13/09/2023` | 


### List all tasks : `list`
Shows user all the current tasks both pending and completed as long as it is not removed / deleted


Format : `list` 

| Examples | 
| --- |
| `list` | 


### Mark as Done : `mark`
Marks a specific task / deadline / event as completed with a tick


Format : `mark INDEX` where INDEX is the current position of the activity users want to mark as done
- The `INDEX` must be a positive integer 1,2,3 where 1 is the first task appearing in the list

| Examples | 
| --- |
| `mark 1` | 
| `mark 2` | 


### Delete a Specific Job / Task : `delete`
Deletes the specified job / task from the task list


Format : `delete INDEX` where INDEX is the current position of the activity users want to delete
- The `INDEX` must be a positive integer 1,2,3 where 1 is the first task appearing in the list

| Examples | 
| --- |
| `delete 1` | 
| `delete 2` | 


### Bulk Delete : `remove`
Mass Removal of Activities that contain a certain word


Format : `remove KEYWORD` where KEYWORD is all the activities that contain that specific keyword
- The `KEYWORD` is case-sensitive
- The order of the word matters
- Partial words will be matched

| Examples | 
| --- |
| `remove CS2103T` | 
| `remove playing` | 


### Navigate to Specific Activty : `find`
Finds tasks / activities whose naming contain of the given keywords


Format : `find KEYWORD` where KEYWORD is all the activities that contain that specific keyword
- The `KEYWORD` is case-sensitive
- The order of the word matters
- Partial words will be matched

| Examples | 
| --- |
| `find CS2103T` | 
| `find playing` | 


### Exiting the Duke : `bye`
Exit the program and saves the current entry

Format : `bye` 

| Examples | 
| --- |
| `bye` | 

___


## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
