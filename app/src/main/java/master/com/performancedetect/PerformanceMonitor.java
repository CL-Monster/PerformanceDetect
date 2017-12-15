package master.com.performancedetect;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;




public class PerformanceMonitor {
    private static final String TAG = "PerformanceMonitor";
    private static final long TIME_DELAY = 1500L;
    private static PerformanceMonitor sPerformanceMonitor;
    private HandlerThread mHandlerThread;
    private Handler mHandler;

    public PerformanceMonitor() {
        mHandlerThread = new HandlerThread(TAG);
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            StackTraceElement[] stack = Looper.getMainLooper().getThread().getStackTrace();
            try {
                StringBuilder sb = new StringBuilder();
                if (stack != null) {
                    for (StackTraceElement element : stack) {
                        sb.append(element + "\n");
                    }
                }
                Log.e(TAG, "-------------------------------------------------");
                Log.e(TAG, "Method more than:" + TIME_DELAY + " s ,stack:");
                Log.e(TAG, sb.toString());
                Log.e(TAG, "-------------------------------------------------");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    };

    public static PerformanceMonitor getInstance() {
        if (sPerformanceMonitor == null) {
            synchronized (PerformanceMonitor.class) {
                if (sPerformanceMonitor == null) {
                    sPerformanceMonitor = new PerformanceMonitor();
                }
            }
        }
        return sPerformanceMonitor;
    }

    public void onMethodStart() {
        mHandler.postDelayed(mRunnable, TIME_DELAY);
    }

    public void onMethodEnd() {
        mHandler.removeCallbacks(mRunnable);
    }

}
