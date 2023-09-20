# User Guide for ChadBot

Track your tasks using Chadbot!  

Chadbot is a **text-management app**.  
With Chadbot, you can manage your tasks <ins>efficiently</ins>.

> I use this instead of my brain! - no one

## Key Features 

### Add a task

Adds a task to your todo list

### Mark tasks

Marks a task with an 'X' when you have completed a task

### Edit tasks

Change the details of your task

### Delete tasks

Removes a task from your todo list

## Usage

### `todo/deadline/event` - adds a task

Adds a task to your todo list.  
There are three types of tasks:
- todo only requires a name
- deadline requires a date
- event requires a start and end date

Format of command:  
`todo (name of task)`  
`deadline (name of task) /by (date)`
`todo (name of task) /from (date) /to (date)`

Dates are in the format of "d MMM YYYY"

Example of usage:

`todo fold clothes`  
`deadline pick up parcel /by 23 Oct 2023`  
`event bali trip /from 24 Nov 2023 /to 26 Nov 2023`

### `mark/unmark` - marks/unmarks a task

Marks a task with a 'X' or remove a 'X' beside a task

Format of command:  
`mark (task number)`  
`unmark (task number)`

Example of usage:

`mark 2`  
`unmark 1`

### `edit` - edit a task

Change the name or a date of a task

Format of command:  
`edit (task number) (task type) (detail to edit) (new detail)`


Example of usage:

`edit 1 T /name read book`  
`edit 3 D /by 23 Oct 2024`  
`edit 2 E /to 23 Oct 2023`

### `delete` - delete a task

Remove a task from the todo list

Format of command:  
`delete (task number)`

Example of usage:

`delete 1`




### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
