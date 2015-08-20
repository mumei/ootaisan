package info.mumei_himazin.ootaisan;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.WindowManager;
import android.widget.ImageView;

public class DisplayService extends Service {
	ImageView imageView;

	@Override
	public void onCreate() {
		super.onCreate();
		imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.ootaisan);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}


	public static void startService(Context context){
		Intent intent = new Intent(context,DisplayService.class);
		context.startService(intent);
	}


	public static void stopService(Context context){
		Intent intent = new Intent(context,DisplayService.class);
		context.stopService(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_TOAST,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				PixelFormat.TRANSLUCENT
		);

		windowManager.addView(imageView, layoutParams);
		return START_NOT_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		windowManager.removeView(imageView);
	}
}
