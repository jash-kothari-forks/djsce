package shahvivek.djsce;

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

import shahvivek.djsce.Adapter.TodoAdapter;
import shahvivek.djsce.Model.Todo;
import shahvivek.djsce.Network.Server;


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

//  CHECK FOR NAME IN SHAREDPREFERENCES, IF PRESENT, SHOW ON TITLE BAR ELSE GO TO NAMEADDACTIVITY

//  SET VIEW, ADAPTER AND SET ADAPTER TO VIEW

		getAllTodos();
	}

	private void getAllTodos()
	{
//  SEND CALL TO SERVER TO GET JSONARRAY, MAKE THE LIST USING TODO JSON TO ARRAYLIST METHOD
		// CALL UPDATEDATA FOR ADAPTER WITH LISTVIEW
	}

	private void goToNameAddActivity()
	{
		Intent nameAdd = new Intent(TodoActivity.this, shahvivek.djsce.NameActivity.class);
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
			showAddTodo();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private void showAddTodo()
	{
		dialogView = getLayoutInflater().inflate(R.layout.add_dialog, null);

//  IMPLEMENT DIALOGINTERFACE.ONCLICKLISTENER AND SET THE LISTENERS FOR NEGATIVE AND NEUTRAL
//  CALL ADDTODO FROM THE NEUTRAL BUTTON AFTER GETTING DATA FROM EDITTEXT

		// MAKE ALERTDIALOG.BUILDER AND SET VIEW, LISTENER
	}

	private void sendAddTodoRequest(String value)
	{
		// CALL SERVER AFTER VALIDATING DATA, SEND TO SERVER AND HANDLE ERROR CASE IN SAME
		// OVERRIDE FUNCTION
	}

//ONLY COMMENTED NOT TO BE REMOVED

//	@Override
//	public void onBackPressed()
//	{
//		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which)
//			{
//				switch (which)
//				{
//					case DialogInterface.BUTTON_NEGATIVE:
//						break;
//					case DialogInterface.BUTTON_POSITIVE:
//						exitApp();
//						break;
//				}
//			}
//		};
//
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setMessage("Are you sure you want to exit?")
//				.setNegativeButton("No", dialogClickListener)
//				.setPositiveButton("Yes", dialogClickListener)
//				.show();
//	}
//
//	private void exitApp()
//	{
//		Intent intent = new Intent(Intent.ACTION_MAIN);
//		intent.addCategory(Intent.CATEGORY_HOME);
//		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		startActivity(intent);
//		finish();
//		System.exit(0);
//	}
}
