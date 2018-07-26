package com.specter.inspecter.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Stack;

public class App extends Application {


    private static App mApplication; // 单例模式
    // 标识是否是测试版
    protected boolean isDebug = true;

    private Stack<WeakReference<Activity>> activities = new Stack<>(); // Activity栈集合


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        setDebug();
    }



    /**
     * 单例模式，获取BTApplication的实例
     *
     * @return Application实例对象
     */
    public static App getApplication() {
        return mApplication;
    }



    /**
     * 获取当前是否是测试版
     *
     * @return true表示当前是测试版。
     */
    public boolean isDebug() {
        return isDebug;
    }

    /**
     * 标识是正式版还是测试版
     */
    protected  void setDebug(){
        isDebug = true;
    }

    /**
     * 添加Activity
     *
     * @param activity activity对象
     */
    public void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(new WeakReference<>(activity));
        }
    }

    /**
     * 移除Activity
     *
     * @param activity activity对象
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            for (int i = 0; i < activities.size(); i++) {
                if (activities.get(i) != null && activity == activities.get(i).get()) {
                    activities.remove(i);
                    break;
                }
            }
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public int getVersion() {
        PackageManager manager = getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            Log.i("info", "" + info.versionCode);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 退出程序
     */
    public void exit() {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null && activities.get(i).get() != null) {
                activities.get(i).get().finish();
            }
        }
        System.exit(0);
    }
    /**
     * 关闭所有界面,跳转指定界面
     */
    public void finishAllActivityToAct(Class<?> activityclass) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null && activities.get(i).get() != null) {
                activities.get(i).get().finish();
            }
        }
        Intent intent = new Intent(this, activityclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
