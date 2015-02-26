package shahvivek.djsce.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vivek on 25/2/15.
 */
public class Todo
{
	private int id;
	private String desc;
	private String date;

	public Todo(){}

	public Todo(JSONObject json) throws JSONException
	{
		this.setId(Integer.parseInt(json.optString("id")));
		this.setDesc(json.optString("desc"));
		this.setDate(json.optString("date"));
	}

	public static ArrayList<Todo> getTodosFromJson(JSONArray jsonArray)
	{
		ArrayList<Todo> toDos = new ArrayList<Todo>();

		for (int i = 0;i<jsonArray.length();i++)
		{
			try
			{
				JSONObject json = jsonArray.getJSONObject(i);
				Todo todoObj = new Todo(json);
				toDos.add(todoObj);
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
		}

		return toDos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
