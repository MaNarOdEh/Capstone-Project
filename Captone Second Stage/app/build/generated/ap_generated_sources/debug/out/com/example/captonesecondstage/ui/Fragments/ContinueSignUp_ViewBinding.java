// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContinueSignUp_ViewBinding implements Unbinder {
  private ContinueSignUp target;

  @UiThread
  public ContinueSignUp_ViewBinding(ContinueSignUp target, View source) {
    this.target = target;

    target.mProfileImage = Utils.findRequiredViewAsType(source, R.id.profile_image, "field 'mProfileImage'", CircleImageView.class);
    target.mUserNameTv = Utils.findRequiredViewAsType(source, R.id.user_name_tv, "field 'mUserNameTv'", TextView.class);
    target.mUserPhoneEt = Utils.findRequiredViewAsType(source, R.id.user_phone_et, "field 'mUserPhoneEt'", EditText.class);
    target.mAdressEt = Utils.findRequiredViewAsType(source, R.id.address_et, "field 'mAdressEt'", EditText.class);
    target.mDescriptionEt = Utils.findRequiredViewAsType(source, R.id.description_et, "field 'mDescriptionEt'", EditText.class);
    target.mUserTypeSpinner = Utils.findRequiredViewAsType(source, R.id.user_type_spinner, "field 'mUserTypeSpinner'", Spinner.class);
    target.mCoursesAutoComplete = Utils.findRequiredViewAsType(source, R.id.autoCompleteTv_courses, "field 'mCoursesAutoComplete'", MultiAutoCompleteTextView.class);
    target.mSignUpBtn = Utils.findRequiredViewAsType(source, R.id.signUp_btn, "field 'mSignUpBtn'", Button.class);
    target.mImageUpload = Utils.findRequiredViewAsType(source, R.id.image_upload, "field 'mImageUpload'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContinueSignUp target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mProfileImage = null;
    target.mUserNameTv = null;
    target.mUserPhoneEt = null;
    target.mAdressEt = null;
    target.mDescriptionEt = null;
    target.mUserTypeSpinner = null;
    target.mCoursesAutoComplete = null;
    target.mSignUpBtn = null;
    target.mImageUpload = null;
  }
}