# User Guide
___
Jose Mourinho bot is a chatbot assistant that helps you manage your tasks, deadlines and events.
Taking inspiration from the famous football manager, it is designed to both track and encourage 
to work hard. It is built with a simple to use interface and a instant reply speed. Let Mourinho 
coach you today!
___
## Features
Jose Mourinho will track all the tasks you have to complete, even after it is closed.

### `Todo` -  Add a to-do task to the list <br />

Create a task with no dates attached
Example of usage:<br/>
`todo <TASK NAME>`

Expected output

```
You must train hard and complete your task: <TASK NAME> 
You now have <Number of tasks> tasks to complete
```

### `Deadline`-  Add a task with a deadline to the list <br />
Example of usage:

`deadline <TASK NAME> /by <DUE DATE IN DD/MM/YYYY HHHH FORMAT>`

Expected output:

```
You must train hard and complete your task: <TASK NAME> 
You now have <Number of tasks> tasks to complete
```

### `Event` -  Add an event to the list <br />
Example of usage:

`event <TASK NAME> /from <START DATE IN DD/MM/YYYY HHHH FORMAT> /to 
<END DATE IN DD/MM/YYYY HHHH FORMAT>`

Expected output:

```
You must train hard and complete your task: <TASK NAME> 
You now have <Number of tasks> tasks to complete
```


### `List` -  View the list of tasks to do <br />
Example of usage:

`List`

Expected output:

```
List of tasks
```

### `Delete` -  Delete a task from the list <br />
Example of usage:

`delete <INDEX OF TASK>`

Expected output:

```
There is no excuse for you to be lazy. You should work hard and do your tasks: <TASK NAME>
```

### `MarkDone` -  Mark a task as completed <br />
Example of usage:

`mark <INDEX OF TASK>`

Expected output:
```
I have noticed you have been working hard, Good job on completing your task: <TASK NAME>
```

### `MarkUnDone` -  Mark a task as not done <br />
Example of usage:

`unmark <INDEX OF TASK>`

Expected output:

```
You want to skip training? Go and run 10 rounds around the field: <TASK NAME>
```

### `Find` - Find a task from your list <br />
Example of usage:

`keyword (optional arguments)`

Expected output:

```
These are your matching tasks: <INDEX OF TASK> <TASK NAME>
```

### `Bye` - Close the chat bot. <br/>
Example of usage:

`Bye`

Expected output:
```
A randomly generated goodbye message.
```

