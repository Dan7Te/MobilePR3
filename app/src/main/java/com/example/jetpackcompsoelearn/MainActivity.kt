package com.example.jetpackcompsoelearn

import android.os.Bundle
import android.provider.SyncStateContract.Columns
import android.service.autofill.OnClickAction
import android.util.Log
import android.util.StateSet
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompsoelearn.ui.theme.JetpackCompsoeLearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val added = mutableStateOf(false)
        val i = mutableStateOf(0)
        val mess = mutableStateOf<String>("")
        val tasks = mutableListOf<String>()
        val completed = mutableListOf<MutableState<Boolean>>()


        setContent {
            HomeScreen(
                i,
                added,
                tasks = tasks,
                completed = completed,
                mess = mess,
                onMessChange = {
                    mess.value = it
                }
            )
        }
    }
}

@Composable
fun HomeScreen(i: MutableState<Int>,
               added: MutableState<Boolean>,
               tasks: MutableList<String>,
               completed: MutableList<MutableState<Boolean>>,
               mess: MutableState<String>,
               onMessChange: (String) -> Unit
    ) {
    Column {
        AddTaskScreen(i, added, mess = mess, onMessChange = onMessChange, tasks = tasks, completed)
        TasksScreen(added, tasks = tasks, completed = completed)
    }

}

@Composable
fun AddTaskScreen(i: MutableState<Int>,
                  added: MutableState<Boolean>,
                  mess: MutableState<String>,
                  onMessChange: (String) -> Unit,
                  tasks: MutableList<String>,
                  completed: MutableList<MutableState<Boolean>>
    ){
    Row {
        TextField(
            value = mess.value,
            onValueChange = onMessChange
        )
        Button(onClick = {addTask(i, added, mess.value, tasks, completed, mess)}){
            Text("Add task")
            added.value = false
        }
    }
}

@Composable
fun TasksScreen(added: MutableState<Boolean>,
    tasks: MutableList<String>,
    completed: MutableList<MutableState<Boolean>>
    ){

    if(added.value){}
    if(tasks.size >0){
        for(j in 0..tasks.size-1){
            Column{
                Row{
                    CheckBox(checked = completed[j], tasks, completed, task = tasks[j])
                }
            }
        }
    }
}

@Composable
fun CheckBox(checked: State<Boolean>,
             tasks: MutableList<String>,
             completed: MutableList<MutableState<Boolean>>,
             task: String) {
    Row {
        Checkbox(checked = checked.value,
            onCheckedChange = {completed[tasks.indexOf(task)].value = it},
        )
        if(checked.value){
            Text(task, fontSize = 32.sp, textDecoration = TextDecoration.LineThrough)
        }
        else{
            Text(task, fontSize = 32.sp)
        }

    }
}


fun addTask(i: MutableState<Int>,
            added: MutableState<Boolean>,
            task: String,
            tasks: MutableList<String>,
            completed: MutableList<MutableState<Boolean>>,
            mess: MutableState<String>
    ){
    tasks.add(task)
    completed.add(mutableStateOf(false))
    i.value++
    added.value = true
    mess.value = ""
}

//textDecoration = TextDecoration.LineThrough

