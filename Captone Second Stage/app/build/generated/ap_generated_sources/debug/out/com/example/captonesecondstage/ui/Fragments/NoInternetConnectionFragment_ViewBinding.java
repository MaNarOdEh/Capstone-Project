// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NoInternetConnectionFragment_ViewBinding implements Unbinder {
  private NoInternetConnectionFragment target;

  @UiThread
  public NoInternetConnectionFragment_ViewBinding(NoInternetConnectionFragment target,
      View source) {
    this.target = target;

    target.mBtnIsConnect = Utils.findOptionalViewAsType(source, R.id.btn_is_connect, "field 'mBtnIsConnect'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NoInternetConnectionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnIsConnect = null;
  }
}
