// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileStudentAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private ProfileStudentAdapter.MyViewHolder target;

  @UiThread
  public ProfileStudentAdapter$MyViewHolder_ViewBinding(ProfileStudentAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.mImageProfileCircle = Utils.findOptionalViewAsType(source, R.id.image_profile_circle, "field 'mImageProfileCircle'", CircleImageView.class);
    target.mUserNameTv = Utils.findOptionalViewAsType(source, R.id.user_name_tv, "field 'mUserNameTv'", TextView.class);
    target.mUserPhoneTv = Utils.findOptionalViewAsType(source, R.id.user_phone_tv, "field 'mUserPhoneTv'", TextView.class);
    target.mCoursesTv = Utils.findOptionalViewAsType(source, R.id.courses_tv, "field 'mCoursesTv'", TextView.class);
    target.mBtnShowProfile = Utils.findOptionalViewAsType(source, R.id.btn_show_profile, "field 'mBtnShowProfile'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileStudentAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mImageProfileCircle = null;
    target.mUserNameTv = null;
    target.mUserPhoneTv = null;
    target.mCoursesTv = null;
    target.mBtnShowProfile = null;
  }
}
