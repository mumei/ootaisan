package info.mumei_himazin.ootaisan;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.button);
		button.setOnClickListener(this);

		if(isRunningService()){
			button.setText("hide");
		}else{
			button.setText("show");
		}

	}

	boolean isRunningService(){
		ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (DisplayService.class.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		String text = button.getText().toString();
		if("show".equals(text)){
			DisplayService.startService(this);
			button.setText("hide");
		}else{
			DisplayService.stopService(this);
			button.setText("show");
		}
	}
}
