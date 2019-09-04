// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LogInFragment_ViewBinding implements Unbinder {
  private LogInFragment target;

  @UiThread
  public LogInFragment_ViewBinding(LogInFragment target, View source) {
    this.target = target;

    target.mUserNameEt = Utils.findOptionalViewAsType(source, R.id.user_name_et, "field 'mUserNameEt'", EditText.class);
    target.mPasswordEd = Utils.findOptionalViewAsType(source, R.id.password_ed, "field 'mPasswordEd'", EditText.class);
    target.mRemmeberCb = Utils.findOptionalViewAsType(source, R.id.remember_cb, "field 'mRemmeberCb'", CheckBox.class);
    target.mForgetPasswordBtn = Utils.findOptionalViewAsType(source, R.id.forget_password_btn, "field 'mForgetPasswordBtn'", Button.class);
    target.mLogin = Utils.findOptionalViewAsType(source, R.id.login_btn, "field 'mLogin'", Button.class);
    target.mCreateAccountTv = Utils.findOptionalViewAsType(source, R.id.create_account_tv, "field 'mCreateAccountTv'", TextView.class);
    target.mGmailFloatBtn = Utils.findOptionalViewAsType(source, R.id.gmail_floatBtn, "field 'mGmailFloatBtn'", FloatingActionButton.class);
    target.mFaceBtn = Utils.findOptionalViewAsType(source, R.id.face_floatBtn, "field 'mFaceBtn'", FloatingActionButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LogInFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUserNameEt = null;
    target.mPasswordEd = null;
    target.mRemmeberCb = null;
    target.mForgetPasswordBtn = null;
    target.mLogin = null;
    target.mCreateAccountTv = null;
    target.mGmailFloatBtn = null;
    target.mFaceBtn = null;
  }
}
