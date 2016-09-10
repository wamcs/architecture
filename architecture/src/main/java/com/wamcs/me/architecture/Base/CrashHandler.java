package com.wamcs.me.architecture.Base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.Log;

import com.wamcs.me.architecture.utils.DircUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/9.
 *
 *
 * this class will save the crash information to local store or web server,
 * you should use this by order:
 * getsInstance()->set method->init()
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static final  String TAG = "CrashHandler";

    private static final File LOCAL_DIR = DircUtil.getDirectory("/CrashTest/log");
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";

    private boolean isWork = true;
    private boolean isSaveInLocal = true;

    private static CrashHandler sInstance = new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private CrashOperator operator;
    private Context mContext;

    public static CrashHandler getsInstance(){
        return sInstance;
    }

    /**
     *
     * @param isWork if false this handler will not work
     *
     */
    public CrashHandler setWorkStatus(boolean isWork){
        this.isWork = isWork;
        return sInstance;
    }

    /**
     *
     * @param isSaveInLocal if false the exception message will not save in location
     *
     */
    public CrashHandler setSaveInLocal(boolean isSaveInLocal){
        this.isSaveInLocal = isSaveInLocal;
        return sInstance;
    }

    public void init(Context context){
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext = context;
    }

    /**
     *
     * @param operator this operator will upload message to web
     *
     */
    public CrashHandler setCrashOperator(CrashOperator operator){
        this.operator = operator;
        return sInstance;
    }


    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!isWork){
            return;
        }
        if (isSaveInLocal){
            dumpExceptionToLocal(throwable);
        }
        if (operator!=null) {
            operator.uploadExceptionToSever(throwable);
        }

        throwable.printStackTrace();

        if (mDefaultCrashHandler != null){
            mDefaultCrashHandler.uncaughtException(thread, throwable);
        }else {
            Process.killProcess(Process.myPid());
        }


    }

    private void dumpExceptionToLocal(Throwable ex){
        if (!LOCAL_DIR.exists()){
            LOCAL_DIR.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file = new File(LOCAL_DIR.getPath(),FILE_NAME+FILE_NAME_SUFFIX);

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        } catch (Exception e) {
            Log.e(TAG,"dump crash info failed");
        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        printWriter.print("App version:");
        printWriter.print(pi.versionName);
        printWriter.print("_");
        printWriter.println(pi.versionCode);

        //Android edition number
        printWriter.print("OS version:");
        printWriter.print(Build.VERSION.RELEASE);
        printWriter.print("_");
        printWriter.println(Build.VERSION.SDK_INT);

        //phone manufacturer
        printWriter.print("Vendor:");
        printWriter.println(Build.MANUFACTURER);

        //phone model
        printWriter.print("Model:");
        printWriter.println(Build.MODEL);

        //cpu framework
        printWriter.print("CPU ABI:");
        printWriter.println(Build.CPU_ABI);
    }

    /**
     * this interface defines the operation which will be worked when crash happen
     */
    public interface CrashOperator{
        void uploadExceptionToSever(Throwable throwable);
    }
}
