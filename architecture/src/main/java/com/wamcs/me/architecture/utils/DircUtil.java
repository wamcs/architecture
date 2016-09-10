package com.wamcs.me.architecture.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/7.
 */
public class DircUtil {

    private static String SDCARD_ROOT_DIR;
    private static String DATA_ROOT_DIR;
    private static final String FILES = "/files";
    private static final String TEMP = "/temp";
    private static final String RES = "/res";


    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static void init(Context context) throws Exception {
        File file = context.getExternalFilesDir(null);
        if (null != file) {
            SDCARD_ROOT_DIR = file.getPath();
        } else {
            throw new Exception("get SDCard path error");
        }
        DATA_ROOT_DIR = context.getFilesDir().toString();

    }

    private static File getDirectory(String root, String type) {
        if (SDCARD_ROOT_DIR == null || DATA_ROOT_DIR == null) {
            Log.e("DircUtil", "you should invoke init() method before use DirUtils");
            return null;
        }
        File file = new File(root + type);
        if (!file.exists()) {
            if (file.mkdir()) {
                Log.d("DircUtil", "======== create file ====== " + file.getAbsolutePath());
            } else {
                Log.d("DircUtil", "======== create file fail =======");
            }
        }
        return file;

    }

    public static File getDirectory(String type) {
        if (hasSDCard()) {
            return getDirectory(SDCARD_ROOT_DIR,type);
        } else {
            return getDirectory(DATA_ROOT_DIR + FILES,type);
        }
    }


}
