# Oscar

Oscar is a chatbot named after F1 driver Oscar Piastri. 
Oscar is a desktop app for managing your notes and tasks via entering commands (case-insensitive).
This user guide will teach about Oscar's features and how to use Oscar.

## Features 

### 📅 Add a Deadline

Add a Deadline task to the item list by using the following command:
```
deadline [task] /by yyyy-MM-dd HHmm
```

### 📆 Add an Event

Add an Event task to the item list by using the following command:
```
event [task] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
```

### ✅ Add a Todo

Add a Todo task to the item list by using the following command:
```
todo [task]
```

### 📄 Add a Note

Add a note to the item list by using the following command:
```
note [description]
```

### 🗑️ Delete an item

Delete an item from the item list by using the following command:
```
delete [item number]
```

### 📋️ List all items

List all items stored by Oscar by using the following command:
```
list
```

### 🔍 Find items

Find items containing a keyword by using the following command:
```
find [keyword]
```

### ✔ Mark a task as done

Mark a task (Deadline/Event/Todo) as done by using the following command:
```
mark [item number]
```

### ❌ Mark a task as not done

Mark a task (Deadline/Event/Todo) as not done by using the following command:
```
unmark [item number]
```

### 👋 Exit programme

Exit the programme after a short delay by using the following command:
```
bye
```

## Usage

Type in any of the above commands into the chatbox and hit Enter or click the 'Send' button.

Example of usage: 

`deadline Submit project /by 2023-09-11 2359`

Expected outcome:

Oscar adds your deadline task to the list of items. Oscar then returns the following confirmation message.

```
Oscar has added:
[D][] Submit project (by: Sep 11 2023 11:59PM)

You now have X items in the list.
```
