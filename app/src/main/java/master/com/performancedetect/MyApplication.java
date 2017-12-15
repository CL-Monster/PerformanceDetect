package master.com.performancedetect;

import android.app.Application;

/**
 * Created by Monster
 * <p>
 * on 2017/12/15
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        /**
         * true  : open
         * false  : clsoe
         */
        PerformanceManager.startMonitor(true);


    }
}
