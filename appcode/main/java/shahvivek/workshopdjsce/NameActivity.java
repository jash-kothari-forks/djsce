package shahvivek.workshopdjsce;

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
		EditText name = (EditText)findViewById(R.id.home_name_edit);
		String nameText = String.valueOf(name.getText());

		if(nameText.trim().length() < 3)
		{
			Toast.makeText(getApplicationContext(), R.string.name_error_case,
					Toast.LENGTH_LONG).show();
		}
		else if(nameText.trim().length() > 20)
		{
			Toast.makeText(getApplicationContext(), R.string.name_error_case_long,
					Toast.LENGTH_LONG).show();
		}
		else
		{
			SharedPreferences myPrefs = getApplicationContext().getSharedPreferences("djsce", MODE_PRIVATE);
			SharedPreferences.Editor myEdit = myPrefs.edit();
			myEdit.putString("username", nameText);
			myEdit.commit();

			Intent main = new Intent(NameActivity.this, TodoActivity.class);
			NameActivity.this.startActivity(main);
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}
}
