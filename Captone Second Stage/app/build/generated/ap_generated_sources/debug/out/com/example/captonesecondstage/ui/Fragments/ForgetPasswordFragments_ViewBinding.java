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
import java.lang.IllegalStateException;
import java.lang.Override;

public class ForgetPasswordFragments_ViewBinding implements Unbinder {
  private ForgetPasswordFragments target;

  @UiThread
  public ForgetPasswordFragments_ViewBinding(ForgetPasswordFragments target, View source) {
    this.target = target;

    target.mUserEmailEt = Utils.findOptionalViewAsType(source, R.id.user_email_et, "field 'mUserEmailEt'", EditText.class);
    target.mBtnLogin = Utils.findOptionalViewAsType(source, R.id.login_btn, "field 'mBtnLogin'", Button.class);
    target.mMessageTxt = Utils.findOptionalViewAsType(source, R.id.message_txt, "field 'mMessageTxt'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ForgetPasswordFragments target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mUserEmailEt = null;
    target.mBtnLogin = null;
    target.mMessageTxt = null;
  }
}
