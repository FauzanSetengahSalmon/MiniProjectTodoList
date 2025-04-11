package org.cheva.miniprojecttodolist.TodoList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cheva.miniprojecttodolist.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    state: TodoListState,
    onEvent: (TodoListEvent) -> Unit

) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            var taskInput by remember { mutableStateOf("") }

            TextField(
                value = taskInput,
                onValueChange = { taskInput = it },
                label = { Text(stringResource(R.string.add_task_label)) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(TodoListEvent.AddTask(taskInput))
                    taskInput = ""
                }
            ) {
                Text(stringResource(R.string.add_task_button))
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(state.tasks) { task ->
                    TaskItem(
                        task = task,
                        onDeleteTask = { onEvent(TodoListEvent.DeleteTask(task)) },
                        onEditTask = { newTask ->
                            onEvent(TodoListEvent.UpdateTask(task, newTask))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: String, onDeleteTask: () -> Unit, onEditTask: (String) -> Unit) {
    var isEditing by remember { mutableStateOf(false) }
    var editedTask by remember { mutableStateOf(task) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isEditing) {
            TextField(
                value = editedTask,
                onValueChange = { editedTask = it },
                modifier = Modifier.weight(1f),
                label = { Text("Edit Task") }
            )
            IconButton(onClick = {
                onEditTask(editedTask)
                isEditing = false
            }) {
                Icon(Icons.Default.Check, contentDescription = "Save Task")
            }
        } else {
            Text(task, modifier = Modifier.weight(1f))
            IconButton(onClick = { isEditing = true }) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Task")
            }
            IconButton(onClick = onDeleteTask) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TodoListScreenPreview() {
    TodoListScreen(
        state = TodoListState(tasks = listOf("Task 1", "Task 2")),
        onEvent = {}
    )
}