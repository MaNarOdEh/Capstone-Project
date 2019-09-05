// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileStudent_ParentsActivity_ViewBinding implements Unbinder {
  private ProfileStudent_ParentsActivity target;

  @UiThread
  public ProfileStudent_ParentsActivity_ViewBinding(ProfileStudent_ParentsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileStudent_ParentsActivity_ViewBinding(ProfileStudent_ParentsActivity target,
      View source) {
    this.target = target;

    target.mAddFavImg = Utils.findOptionalViewAsType(source, R.id.add_fav_img, "field 'mAddFavImg'", ImageView.class);
    target.mcircleImgProfile = Utils.findOptionalViewAsType(source, R.id.circleImg_profile, "field 'mcircleImgProfile'", CircleImageView.class);
    target.mNameTxt = Utils.findOptionalViewAsType(source, R.id.name_txt, "field 'mNameTxt'", TextView.class);
    target.mPhoneTxt = Utils.findOptionalViewAsType(source, R.id.phone_txt, "field 'mPhoneTxt'", TextView.class);
    target.mAboutUsTxt = Utils.findOptionalViewAsType(source, R.id.aboutUs_txt, "field 'mAboutUsTxt'", TextView.class);
    target.mBtnCall = Utils.findOptionalViewAsType(source, R.id.btn_call, "field 'mBtnCall'", Button.class);
    target.mBtnSMS = Utils.findOptionalViewAsType(source, R.id.btn_sms, "field 'mBtnSMS'", Button.class);
    target.mRecycleCourses = Utils.findOptionalViewAsType(source, R.id.recycle_courses, "field 'mRecycleCourses'", RecyclerView.class);
    target.mBtnSendRequest = Utils.findOptionalViewAsType(source, R.id.btn_send_request, "field 'mBtnSendRequest'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileStudent_ParentsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mAddFavImg = null;
    target.mcircleImgProfile = null;
    target.mNameTxt = null;
    target.mPhoneTxt = null;
    target.mAboutUsTxt = null;
    target.mBtnCall = null;
    target.mBtnSMS = null;
    target.mRecycleCourses = null;
    target.mBtnSendRequest = null;
  }
}
