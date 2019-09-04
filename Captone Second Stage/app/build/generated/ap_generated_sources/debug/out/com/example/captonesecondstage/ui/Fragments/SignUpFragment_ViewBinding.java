// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.Button;
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

public class SignUpFragment_ViewBinding implements Unbinder {
  private SignUpFragment target;

  @UiThread
  public SignUpFragment_ViewBinding(SignUpFragment target, View source) {
    this.target = target;

    target.mUserNameEt = Utils.findRequiredViewAsType(source, R.id.user_name_et, "field 'mUserNameEt'", EditText.class);
    target.mUserEmailEt = Utils.findRequiredViewAsType(source, R.id.user_email_et, "field 'mUserEmailEt'", EditText.class);
    target.mPasswordEd = Utils.findRequiredViewAsType(source, R.id.password_ed, "field 'mPasswordEd'", EditText.class);
    target.mcfPasswordEt = Utils.findRequiredViewAsType(source, R.id.cfPassword_ed, "field 'mcfPasswordEt'", EditText.class);
    target.mSignUpBtn = Utils.findRequiredViewAsType(source, R.id.signUp_btn, "field 'mSignUpBtn'", Button.class);
    target.mGmailFloatBtn = Utils.findRequiredViewAsType(source, R.id.gmail_floatBtn, "field 'mGmailFloatBtn'", FloatingActionButton.class);
    target.mFaceFloatBtn = Utils.findRequiredViewAsType(source, R.id.face_floatBtn, "field 'mFaceFloatBtn'", FloatingActionButton.class);
    target.mCreateAccountTv = Utils.findRequiredViewAsType(source, R.id.create_account_tv, "field 'mCreateAccountTv'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignUpFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUserNameEt = null;
    target.mUserEmailEt = null;
    target.mPasswordEd = null;
    target.mcfPasswordEt = null;
    target.mSignUpBtn = null;
    target.mGmailFloatBtn = null;
    target.mFaceFloatBtn = null;
    target.mCreateAccountTv = null;
  }
}
