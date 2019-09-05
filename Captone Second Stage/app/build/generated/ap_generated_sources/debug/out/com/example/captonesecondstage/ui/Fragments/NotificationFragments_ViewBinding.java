// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationFragments_ViewBinding implements Unbinder {
  private NotificationFragments target;

  @UiThread
  public NotificationFragments_ViewBinding(NotificationFragments target, View source) {
    this.target = target;

    target.mRecycleNotification = Utils.findOptionalViewAsType(source, R.id.recycle_notification, "field 'mRecycleNotification'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationFragments target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecycleNotification = null;
  }
}
