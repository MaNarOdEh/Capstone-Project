// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CoursesAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private CoursesAdapter.MyViewHolder target;

  @UiThread
  public CoursesAdapter$MyViewHolder_ViewBinding(CoursesAdapter.MyViewHolder target, View source) {
    this.target = target;

    target.mCoursesNameTxt = Utils.findRequiredViewAsType(source, R.id.courses_name_txt, "field 'mCoursesNameTxt'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CoursesAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mCoursesNameTxt = null;
  }
}
