package testing.caffeframework;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;


import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

public class MainActivity extends Activity {


    private String root_path_;


    // =============================================================================================
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        private String TAG = "Testing";
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    System.loadLibrary("ndk_modules");
                    Log.i(TAG, "OpenCV loaded successfully");
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };
    // =============================================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root_path_ = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString() + "/caffe_mobile/";


    }


    @Override
    protected void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallback);



    }
}
