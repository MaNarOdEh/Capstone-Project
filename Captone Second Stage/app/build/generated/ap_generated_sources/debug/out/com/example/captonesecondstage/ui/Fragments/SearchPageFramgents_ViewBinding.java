// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchPageFramgents_ViewBinding implements Unbinder {
  private SearchPageFramgents target;

  @UiThread
  public SearchPageFramgents_ViewBinding(SearchPageFramgents target, View source) {
    this.target = target;

    target.mRandomSuggestionProfile = Utils.findOptionalViewAsType(source, R.id.random_suggestion_profile, "field 'mRandomSuggestionProfile'", RecyclerView.class);
    target.mSearchEdit = Utils.findOptionalViewAsType(source, R.id.search_edit, "field 'mSearchEdit'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchPageFramgents target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRandomSuggestionProfile = null;
    target.mSearchEdit = null;
  }
}
