package shahvivek.workshopdjsce.Network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by vivek on 25/2/15.
 */
public class Server
{
	private static AsyncHttpClient client = new AsyncHttpClient();
	private static String url = "http://192.168.1.7:5000";

	public static void getTodos(RequestParams params, JsonHttpResponseHandler handler)
	{
		client.post(url+"/gettodos", params, handler);
	}

	public static void addTodo(RequestParams params, JsonHttpResponseHandler handler)
	{
		client.post(url+"/addtodo", params, handler);
	}

	public static void updateTodo(RequestParams params, JsonHttpResponseHandler handler)
	{
		client.post(url+"/updatetodo", params, handler);
	}

	public static void deleteTodo(RequestParams params, JsonHttpResponseHandler handler)
	{
		client.post(url+"/deletetodo", params, handler);
	}
}
