package com.marvin.splashedink.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.marvin.splashedink.R;
import com.marvin.splashedink.utils.ActivityUtils;
import com.marvin.splashedink.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mapuw on 2016/12/6.
 */

public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder un;

    public P presenter;

    private AlertDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtils.addActivity(this);
        setContentView(getLayoutId());
        un = ButterKnife.bind(this);
        presenter = initPresenter();
        presenter.attach((V) this);
        actionbarInit();
        dataInit();
        eventInit();
    }

    protected abstract int getLayoutId();

    // 实例化presenter
    public abstract P initPresenter();

    protected abstract void actionbarInit();

    protected abstract void dataInit();

    protected abstract void eventInit();

    @Override
    protected void onDestroy() {
        ActivityUtils.removeActivity(this);
        un.unbind();
        presenter.dettach();
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 123) {
            showToast("权限获取失败");
        }
    }

    /**
     * 页面跳转
     *
     * @param activity（完整类名）
     * @param bundle
     */
    public void startIntent(Class activity, @Nullable Bundle bundle) {
        Intent intent = null;
        try {
            intent = new Intent(this, Class.forName(activity.getCanonicalName()));
            if (bundle != null) {
                intent.putExtra("INTENT", bundle);
            }
            startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 弹出Toast
     *
     * @param msg
     */
    public void showToast(CharSequence msg) {
        ToastUtil.getInstance(this)
                .setDuration(Toast.LENGTH_LONG)
                .setText(msg)
                .show();
    }

    /**
     * 弹出Alert
     *
     * @param title
     * @param msg
     * @param positive
     * @param negative
     * @param positivelistener
     * @param negativelistener
     */
    public void showAlertDialog(CharSequence title,
                                CharSequence msg,
                                @Nullable CharSequence positive,
                                @Nullable CharSequence negative,
                                @Nullable DialogInterface.OnClickListener positivelistener,
                                @Nullable DialogInterface.OnClickListener negativelistener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (title != null) {
            alert.setTitle(title);
        }
        alert.setMessage(msg);
        if (positive != null) {
            alert.setPositiveButton(positive, positivelistener);
        }
        if (negative != null) {
            alert.setNegativeButton(negative, negativelistener);
        }
        alert.create().show();
    }

    public AlertDialog getProgress() {
        if (progress == null) {
            progress = new AlertDialog.Builder(this).create();
            progress.setCancelable(false);
            progress.show();
            Window win = progress.getWindow();
            win.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams layoutParams = progress.getWindow().getAttributes();
            layoutParams.width = 300;
            layoutParams.height = 300;
            progress.getWindow().setAttributes(layoutParams);
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_progress, null);
            win.setContentView(view);
        }
        return progress;
    }

}
