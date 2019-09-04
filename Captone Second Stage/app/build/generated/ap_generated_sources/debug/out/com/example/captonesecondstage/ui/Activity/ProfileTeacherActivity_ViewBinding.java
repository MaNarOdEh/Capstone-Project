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

public class ProfileTeacherActivity_ViewBinding implements Unbinder {
  private ProfileTeacherActivity target;

  @UiThread
  public ProfileTeacherActivity_ViewBinding(ProfileTeacherActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ProfileTeacherActivity_ViewBinding(ProfileTeacherActivity target, View source) {
    this.target = target;

    target.mAddFavImg = Utils.findOptionalViewAsType(source, R.id.add_fav_img, "field 'mAddFavImg'", ImageView.class);
    target.mStarImageF = Utils.findOptionalViewAsType(source, R.id.star_img_f, "field 'mStarImageF'", ImageView.class);
    target.mStartImageS = Utils.findOptionalViewAsType(source, R.id.star_img_s, "field 'mStartImageS'", ImageView.class);
    target.mStartImageT = Utils.findOptionalViewAsType(source, R.id.star_img_t, "field 'mStartImageT'", ImageView.class);
    target.mStartImageFour = Utils.findOptionalViewAsType(source, R.id.star_img_fourth, "field 'mStartImageFour'", ImageView.class);
    target.mStartImageFifith = Utils.findOptionalViewAsType(source, R.id.star_img_fifth, "field 'mStartImageFifith'", ImageView.class);
    target.mcircleImgProfile = Utils.findOptionalViewAsType(source, R.id.circleImg_profile, "field 'mcircleImgProfile'", CircleImageView.class);
    target.mNameTxt = Utils.findOptionalViewAsType(source, R.id.name_txt, "field 'mNameTxt'", TextView.class);
    target.mPhoneTxt = Utils.findOptionalViewAsType(source, R.id.phone_txt, "field 'mPhoneTxt'", TextView.class);
    target.mAboutUsTxt = Utils.findOptionalViewAsType(source, R.id.aboutUs_txt, "field 'mAboutUsTxt'", TextView.class);
    target.mBtnCall = Utils.findOptionalViewAsType(source, R.id.btn_call, "field 'mBtnCall'", Button.class);
    target.mBtnSMS = Utils.findOptionalViewAsType(source, R.id.btn_sms, "field 'mBtnSMS'", Button.class);
    target.mRecycleCourses = Utils.findOptionalViewAsType(source, R.id.recycle_courses, "field 'mRecycleCourses'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileTeacherActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mAddFavImg = null;
    target.mStarImageF = null;
    target.mStartImageS = null;
    target.mStartImageT = null;
    target.mStartImageFour = null;
    target.mStartImageFifith = null;
    target.mcircleImgProfile = null;
    target.mNameTxt = null;
    target.mPhoneTxt = null;
    target.mAboutUsTxt = null;
    target.mBtnCall = null;
    target.mBtnSMS = null;
    target.mRecycleCourses = null;
  }
}
