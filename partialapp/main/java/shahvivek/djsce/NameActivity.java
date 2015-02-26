package shahvivek.djsce;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class NameActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_name_add);
	}

	public void proceedToMain(View v)
	{
// GET NAME FROM EDITTEXT, SAVE IT IN SHAREDPREFERENCES, COMMIT IT, AND THEN TRAVEL TO TODOACTIVITY
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}
}
