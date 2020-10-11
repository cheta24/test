package com.example.secondapplication

import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var todoRecyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        todoRecyclerView = findViewById(R.id.lists_recyclerview)
        todoRecyclerView.layoutManager = LinearLayoutManager(this)
        todoRecyclerView.adapter = TodoListAdapter()

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            val adapter = todoRecyclerView.adapter as TodoListAdapter
//            adapter.addItem()
            showCreateTodoListDialogue()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showCreateTodoListDialogue(){

        val dialogueTitle = getString(R.string.name_of_list)
        val positiveTitleButton = getString(R.string.create_list)
        val myDialog = AlertDialog.Builder(this)
        val todoEditText = EditText(this)
        todoEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

        myDialog.setTitle(dialogueTitle)
        myDialog.setView(todoEditText)
        myDialog.setPositiveButton(positiveTitleButton){
            dialog, _->
            val adapter = todoRecyclerView.adapter as TodoListAdapter
            adapter.addItem(todoEditText.text.toString())
                dialog.dismiss()
        }
        myDialog.create().show()


    }
}