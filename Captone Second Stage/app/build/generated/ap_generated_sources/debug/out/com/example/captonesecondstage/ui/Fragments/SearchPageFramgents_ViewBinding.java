// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import com.google.android.gms.ads.AdView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchPageFramgents_ViewBinding implements Unbinder {
  private SearchPageFramgents target;

  @UiThread
  public SearchPageFramgents_ViewBinding(SearchPageFramgents target, View source) {
    this.target = target;

    target.mRandomSuggestionProfile = Utils.findOptionalViewAsType(source, R.id.random_suggestion_profile, "field 'mRandomSuggestionProfile'", RecyclerView.class);
    target.mAdView = Utils.findOptionalViewAsType(source, R.id.adView, "field 'mAdView'", AdView.class);
    target.mSearchView = Utils.findOptionalViewAsType(source, R.id.search_view, "field 'mSearchView'", SearchView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchPageFramgents target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRandomSuggestionProfile = null;
    target.mAdView = null;
    target.mSearchView = null;
  }
}
