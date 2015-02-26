package shahvivek.djsce.Adapter;

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

import java.util.ArrayList;

import shahvivek.djsce.Model.Todo;
import shahvivek.djsce.Network.Server;
import shahvivek.djsce.R;

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
//  SET VIEW HOLDER PATTERN, INFLATE LAYOUT AND SET TO HOLDER
//
//  SET DESCRIPTION TO HOLDER.DESC, SET POSITION TAGS ON BOTH
//
//  SET UPDATE, DELETE LISTENERS ON UPDATE AND DELETE

		return convertView;
	}

	View.OnClickListener updateClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
		dialogView = myInflater.inflate(R.layout.update_dialog, null);
//
// GET POSITION FROM TAG, MAKE INTERFACE TO LISTEN TO NEGATIVE AND NEUTRAL,
// GET VALUE FROM EDITTEXT, SEND IT TO UPDATE METHOD
//
// ALERTDIALOG.BUILDER STUFF
	}
	};


	View.OnClickListener deleteClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			// SIMILARLY, SET INTERFACE FOR DELETE LISTENER AND SEND IT TO DELETE METHOD
		}
	};

	private void sendDeleteTodoRequest(final int position)
	{
//  SEND DELETE REQUEST TO SERVER
	}

	private void sendUpdateTodoRequest(int position, final String value)
	{
// DO BASIC VALIDATIONS, AND SEND DATA TO SERVER
	}

	public void updateData(ArrayList<Todo> todoList)
	{
		toDos = todoList;
		notifyDataSetChanged();
	}

	private static class ViewHolder
	{
		//SET VIEWHOLDER PARAMETERS ACCORDING TO TODO_ITEM.XML
	}
}
