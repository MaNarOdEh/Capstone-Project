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

public class FavoriteFragments_ViewBinding implements Unbinder {
  private FavoriteFragments target;

  @UiThread
  public FavoriteFragments_ViewBinding(FavoriteFragments target, View source) {
    this.target = target;

    target.mRecycleFavorite = Utils.findOptionalViewAsType(source, R.id.recycle_Favorite, "field 'mRecycleFavorite'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FavoriteFragments target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecycleFavorite = null;
  }
}
