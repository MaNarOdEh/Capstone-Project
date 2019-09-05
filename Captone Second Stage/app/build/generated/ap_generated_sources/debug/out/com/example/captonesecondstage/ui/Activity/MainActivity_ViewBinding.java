// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Activity;

import android.view.View;
import android.widget.ScrollView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mMainScrollView = Utils.findOptionalViewAsType(source, R.id.main_scrollView, "field 'mMainScrollView'", ScrollView.class);
    target.mMainLayout = Utils.findOptionalViewAsType(source, R.id.main_layout, "field 'mMainLayout'", CoordinatorLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMainScrollView = null;
    target.mMainLayout = null;
  }
}
