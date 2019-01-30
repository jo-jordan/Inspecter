package com.specter.inspecter.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.lang.ref.WeakReference;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class App extends Application {

    private static final long DEFAULT_READ_TIME_OUT = 1000;
    private static final long DEFAULT_WRITE_TIME_OUT = 1000;
    private static final long DEFAULT_CONNECT_TIME_OUT = 1000;


    private static App mApplication; // 单例模式
    // 标识是否是测试版
    protected boolean isDebug = true;

    private Stack<WeakReference<Activity>> activities = new Stack<>(); // Activity栈集合


    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);

        //全局的读取超时时间
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(DEFAULT_WRITE_TIME_OUT, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS);

        //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3);

        setDebug();
    }


    /**
     * 单例模式，获取Application的实例
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
    protected void setDebug() {
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
    public void finishAllActivityToAct(Class<?> activityClass) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null && activities.get(i).get() != null) {
                activities.get(i).get().finish();
            }
        }
        Intent intent = new Intent(this, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public Activity getActivityByClass(Class<?> activityClass) {
        for (int i = 0; i < activities.size(); i++) {
            if (activities.get(i) != null && activities.get(i).get() != null) {
                return activities.get(i).get();
            }
        }
        return null;
    }
}
