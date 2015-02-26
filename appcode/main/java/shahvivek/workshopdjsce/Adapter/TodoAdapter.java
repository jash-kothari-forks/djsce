package shahvivek.workshopdjsce.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import shahvivek.workshopdjsce.Model.Todo;
import shahvivek.workshopdjsce.Network.Server;
import shahvivek.workshopdjsce.R;

/**
 * Created by vivek on 25/2/15.
 */
public class TodoAdapter extends BaseAdapter
{
	Context myContext;
	LayoutInflater myInflater;
	View dialogView;
	ArrayList<Todo> toDos;
	Server server;

	public TodoAdapter(Context context, LayoutInflater inflater)
	{
		myContext = context;
		myInflater = inflater;
		toDos = new ArrayList<Todo>();
		server = new Server();
	}

	@Override
	public int getCount() {
		return toDos.size();
	}

	@Override
	public Todo getItem(int position){
		return toDos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		Todo todo = toDos.get(position);
		ViewHolder holder;

		if(convertView == null)
		{
			holder = new ViewHolder();
			convertView = myInflater.inflate(R.layout.todo_item, parent, false);
			holder.desc = (TextView)convertView.findViewById(R.id.todo_desc);
			holder.update = (ImageButton)convertView.findViewById(R.id.todo_edit);
			holder.delete = (ImageButton)convertView.findViewById(R.id.todo_delete);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}

		holder.desc.setText(todo.getDesc());
		holder.update.setTag(position);
		holder.delete.setTag(position);

		holder.update.setOnClickListener(updateClickListener);
		holder.delete.setOnClickListener(deleteClickListener);

		return convertView;
	}

	View.OnClickListener updateClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			final int position = (Integer)v.getTag();
			Log.d("Clicked", String.valueOf(position));

			dialogView = myInflater.inflate(R.layout.update_dialog, null);

			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					switch(which)
					{
						case DialogInterface.BUTTON_NEGATIVE:
							break;
						case DialogInterface.BUTTON_NEUTRAL:
							EditText editText = (EditText)dialogView.findViewById(R.id.update_todo);
							String desc = String.valueOf(editText.getText());
							Log.d("Update value", desc);
							sendUpdateTodoRequest(position, desc);
							break;
					}
				}
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
			builder.setView(dialogView)
					.setNegativeButton("No", dialogClickListener)
					.setNeutralButton("Update", dialogClickListener)
					.show();
		}
	};


	View.OnClickListener deleteClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			final int position = (Integer)v.getTag();

			DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which)
					{
						case DialogInterface.BUTTON_NEGATIVE:
							break;
						case DialogInterface.BUTTON_POSITIVE:
							Log.d("Delete value", String.valueOf(position));
							sendDeleteTodoRequest(position);
					}
				}
			};

			AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
			builder.setMessage("Are you sure you want to delete this todo")
					.setNegativeButton("No", listener)
					.setPositiveButton("Yes", listener)
					.show();
		}
	};

	private void sendDeleteTodoRequest(final int position)
	{
		Todo oldTodo = getItem(position);
		RequestParams params = new RequestParams();
		params.put("id", oldTodo.getId());
		server.deleteTodo(params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response)
			{
				Log.d("Response", response.toString());
				if (response.has("success"))
				{
					Toast.makeText(myContext, "Item has been deleted!", Toast.LENGTH_LONG).show();
					toDos.remove(position);
					notifyDataSetChanged();
				}
				else
				{
					Toast.makeText(myContext, "Something went wrong, please try again.",
							Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
				Log.d("Error", throwable.getMessage());
			}
		});
	}

	private void sendUpdateTodoRequest(int position, final String value)
	{
		final Todo oldTodo = getItem(position);

		if(value.trim().length() < 3)
		{
			Toast.makeText(myContext, "Sorry, no data input. Please try again!",Toast.LENGTH_LONG).show();
			return;
		}
		else if(value.trim() == oldTodo.getDesc())
		{
			Toast.makeText(myContext, "Sorry, same description. Please try again!",	Toast.LENGTH_LONG).show();
			return;
		}

		RequestParams params = new RequestParams();
		params.put("id", oldTodo.getId());
		params.put("desc", value);

		server.updateTodo(params, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
				Log.d("Response", response.toString());
				if(response.has("success"))
				{
					Toast.makeText(myContext, "Item has been updated!", Toast.LENGTH_LONG).show();
					oldTodo.setDesc(value);
					notifyDataSetChanged();
				}
				else
				{
					Toast.makeText(myContext, "Something went wrong, please try again.",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	public void updateData(ArrayList<Todo> todoList)
	{
		toDos = todoList;
		notifyDataSetChanged();
	}

	private static class ViewHolder
	{
		TextView desc;
		ImageButton update;
		ImageButton delete;
	}
}
