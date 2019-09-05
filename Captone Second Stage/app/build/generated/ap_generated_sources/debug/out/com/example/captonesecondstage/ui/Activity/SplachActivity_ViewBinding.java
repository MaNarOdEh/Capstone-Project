// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Activity;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplachActivity_ViewBinding implements Unbinder {
  private SplachActivity target;

  @UiThread
  public SplachActivity_ViewBinding(SplachActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplachActivity_ViewBinding(SplachActivity target, View source) {
    this.target = target;

    target.mBtnLogIn = Utils.findOptionalViewAsType(source, R.id.btn_login, "field 'mBtnLogIn'", Button.class);
    target.mBtnSignUp = Utils.findOptionalViewAsType(source, R.id.btn_signUp, "field 'mBtnSignUp'", Button.class);
    target.mBtnAboutUs = Utils.findOptionalViewAsType(source, R.id.btn_aboutUs, "field 'mBtnAboutUs'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplachActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnLogIn = null;
    target.mBtnSignUp = null;
    target.mBtnAboutUs = null;
  }
}
