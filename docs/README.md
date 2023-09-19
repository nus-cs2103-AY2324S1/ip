# Ekud 🤖

> When in doubt on naming things, simply spell it backwards

**Ekud** is a simple chatbot that helps you remember your to-dos, deadlines and events.

## Features

* Create and delete tasks with _optional_ deadlines or start/end dates
* List all the tasks you currently have
* Mark tasks as done and unmark them as well
* Automatically saves tasks to a file and loads when the chatbot is opened

## Usage

1. Ensure a _recent_ version of Java is installed on your system
2. Download the [latest release](https://github.com/ravern/ip/releases) of Ekud from GitHub
3. Run the JAR file using the command `java -jar Ekud.jar`

## Example

```
Hello! I'm Ekud!
What can I do for you?
> list
Here are the tasks in your list:
1. [E] [ ] Birthday celebration (from: 17:00 to: 21:00)
> todo Clear the trash
Got it. I've added this task:
   [T] [ ] Clear the trash
Now you have 2 tasks in the list.
> list 
Here are the tasks in your list:
1. [E] [ ] Birthday celebration (from: 17:00 to: 21:00)
2. [T] [ ] Clear the trash
> mark 2
Nice! I've marked this task as done:
   [T] [X] Clear the trash
> delete 1
Noted. I've removed this task:
   [E] [ ] Birthday celebration (from: 17:00 to: 21:00)
Now you have 1 tasks in the list.
> list
Here are the tasks in your list:
1. [T] [X] Clear the trash
> bye
Bye. Hope to see you again soon!
```