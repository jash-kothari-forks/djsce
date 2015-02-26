package shahvivek.workshopdjsce;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;

import shahvivek.workshopdjsce.Adapter.TodoAdapter;
import shahvivek.workshopdjsce.Model.Todo;
import shahvivek.workshopdjsce.Network.Server;


public class TodoActivity extends Activity
{
	Context myContext;
	ListView todoView;
	Server server;
	ArrayList<Todo> todoList;
	TodoAdapter adapter;
	View dialogView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);

		myContext = getApplicationContext();

		SharedPreferences myPrefs = myContext.getSharedPreferences("djsce", MODE_PRIVATE);
		String name = myPrefs.getString("username", "");

		if(name == null || name == "")
		{
			goToNameAddActivity();
		}
		else
		{
			getActionBar().setTitle("Hi, "+name);
		}

		todoView = (ListView)findViewById(R.id.todo_listview);
		adapter = new TodoAdapter(getApplicationContext(), getLayoutInflater());
		todoView.setAdapter(adapter);

		getAllTodos();
	}

	private void getAllTodos()
	{
		Server.getTodos(null, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray response)
			{
				todoList = Todo.getTodosFromJson(response);
				adapter.updateData(todoList);
			}
		});
	}

	private void goToNameAddActivity()
	{
		Intent nameAdd = new Intent(TodoActivity.this, NameActivity.class);
		TodoActivity.this.startActivity(nameAdd);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_todo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_refresh)
		{
			getAllTodos();
			return true;
		}
		else if(id == R.id.action_add)
		{
			goToAddTodo();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void goToAddTodo()
	{
		dialogView = getLayoutInflater().inflate(R.layout.add_dialog, null);
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch(which)
				{
					case DialogInterface.BUTTON_NEGATIVE:
						break;
					case DialogInterface.BUTTON_NEUTRAL:
						EditText editText = (EditText)dialogView.findViewById(R.id.add_todo);
						String desc = String.valueOf(editText.getText());
						Log.d("Add value", desc);
						sendAddTodoRequest(desc);
						break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(TodoActivity.this);
		builder.setView(dialogView)
				.setNegativeButton("Cancel", dialogClickListener)
				.setNeutralButton("Add", dialogClickListener)
				.show();
	}

	private void sendAddTodoRequest(String value)
	{
		if(value.trim().length() < 3)
		{
			Toast.makeText(myContext, "Sorry, no data input. Please try again!", Toast.LENGTH_LONG).show();
			return;
		}

		RequestParams params = new RequestParams();
		params.put("desc", value);

		Server.addTodo(params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response)
			{
				Log.d("Response", response.toString());
				if(response.has("success"))
				{
					Toast.makeText(myContext, "Item has been added!", Toast.LENGTH_LONG).show();
					getAllTodos();
				}
				else
				{
					Toast.makeText(myContext, "Something went wrong, please try again.",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public void onBackPressed()
	{
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which)
				{
					case DialogInterface.BUTTON_NEGATIVE:
						break;
					case DialogInterface.BUTTON_POSITIVE:
						exitApp();
						break;
				}
			}
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to exit?")
				.setNegativeButton("No", dialogClickListener)
				.setPositiveButton("Yes", dialogClickListener)
				.show();
	}

	private void exitApp()
	{
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		finish();
		System.exit(0);
	}
}
