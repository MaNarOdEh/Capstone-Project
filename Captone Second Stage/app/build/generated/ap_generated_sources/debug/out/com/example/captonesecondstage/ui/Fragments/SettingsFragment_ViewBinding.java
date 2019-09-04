// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingsFragment_ViewBinding implements Unbinder {
  private SettingsFragment target;

  @UiThread
  public SettingsFragment_ViewBinding(SettingsFragment target, View source) {
    this.target = target;

    target.mBtnUpdateUserName = Utils.findOptionalViewAsType(source, R.id.btn_update_user_name, "field 'mBtnUpdateUserName'", Button.class);
    target.mBtnUpdatePasssword = Utils.findOptionalViewAsType(source, R.id.btn_update_password, "field 'mBtnUpdatePasssword'", Button.class);
    target.mBtnUpdateEmail = Utils.findOptionalViewAsType(source, R.id.btn_update_email, "field 'mBtnUpdateEmail'", Button.class);
    target.mBtn_update_address = Utils.findOptionalViewAsType(source, R.id.btn_update_address, "field 'mBtn_update_address'", Button.class);
    target.mBtnUpdateCourses = Utils.findOptionalViewAsType(source, R.id.btn_update_courses, "field 'mBtnUpdateCourses'", Button.class);
    target.mBtnUpdatePhoneNumber = Utils.findOptionalViewAsType(source, R.id.btn_update_phone_number, "field 'mBtnUpdatePhoneNumber'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnUpdateUserName = null;
    target.mBtnUpdatePasssword = null;
    target.mBtnUpdateEmail = null;
    target.mBtn_update_address = null;
    target.mBtnUpdateCourses = null;
    target.mBtnUpdatePhoneNumber = null;
  }
}
