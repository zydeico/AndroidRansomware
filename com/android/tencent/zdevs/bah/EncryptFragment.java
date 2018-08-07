package com.android.tencent.zdevs.bah;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.wThgSpyWhatsApp.R;

public class EncryptFragment extends Fragment {
    private Handler mHandler = new Handler(new C01911());
    private CustomProgressBar myProgress;

    class C01911 implements Callback {
        C01911() {
        }

        public boolean handleMessage(Message message) {
            EncryptFragment.this.myProgress.setProgress(message.what);
            return false;
        }
    }

    class C01922 implements Runnable {
        C01922() {
        }

        public void run() {
            for (int i = 0; i <= 49; i++) {
                EncryptFragment.this.mHandler.sendEmptyMessage(i * 2);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void addListener() {
        new Thread(new C01922()).start();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = layoutInflater.inflate(R.layout.main1, viewGroup, false);
        this.myProgress = (CustomProgressBar) view.findViewById(R.id.pgsBar);
        addListener();
        return view;
    }
}
