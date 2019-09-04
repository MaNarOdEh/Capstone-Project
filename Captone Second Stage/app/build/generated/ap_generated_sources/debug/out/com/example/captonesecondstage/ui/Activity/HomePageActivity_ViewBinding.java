// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Activity;

import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomePageActivity_ViewBinding implements Unbinder {
  private HomePageActivity target;

  @UiThread
  public HomePageActivity_ViewBinding(HomePageActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomePageActivity_ViewBinding(HomePageActivity target, View source) {
    this.target = target;

    target.mFrameContainer = Utils.findRequiredViewAsType(source, R.id.frame_container, "field 'mFrameContainer'", FrameLayout.class);
    target.mNavigationBottomContainer = Utils.findRequiredViewAsType(source, R.id.navigation_bottom_container, "field 'mNavigationBottomContainer'", BottomNavigationView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomePageActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mFrameContainer = null;
    target.mNavigationBottomContainer = null;
  }
}
