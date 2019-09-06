// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.ui.Fragments;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
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

    target.mBtnUpdateDescription = Utils.findOptionalViewAsType(source, R.id.btn_update_description, "field 'mBtnUpdateDescription'", Button.class);
    target.mBtn_update_address = Utils.findOptionalViewAsType(source, R.id.btn_update_address, "field 'mBtn_update_address'", Button.class);
    target.mBtnUpdateCourses = Utils.findOptionalViewAsType(source, R.id.btn_update_courses, "field 'mBtnUpdateCourses'", Button.class);
    target.mBtnUpdatePhoneNumber = Utils.findOptionalViewAsType(source, R.id.btn_update_phone_number, "field 'mBtnUpdatePhoneNumber'", Button.class);
    target.mBtnAddNewStudent = Utils.findRequiredViewAsType(source, R.id.btn_add_new_student, "field 'mBtnAddNewStudent'", Button.class);
    target.mMainLayout = Utils.findOptionalViewAsType(source, R.id.main_layout, "field 'mMainLayout'", LinearLayout.class);
    target.mAddressLinear = Utils.findOptionalViewAsType(source, R.id.address_linear, "field 'mAddressLinear'", LinearLayout.class);
    target.mBtnUpdateAdre = Utils.findOptionalViewAsType(source, R.id.btn_update_addre, "field 'mBtnUpdateAdre'", Button.class);
    target.mAdressEt = Utils.findOptionalViewAsType(source, R.id.address_et, "field 'mAdressEt'", EditText.class);
    target.mPohneLayout = Utils.findOptionalViewAsType(source, R.id.phone_layout, "field 'mPohneLayout'", LinearLayout.class);
    target.mPohneEt = Utils.findOptionalViewAsType(source, R.id.phone_et, "field 'mPohneEt'", EditText.class);
    target.mBtnUpdatePhone = Utils.findOptionalViewAsType(source, R.id.btn_update_phone, "field 'mBtnUpdatePhone'", Button.class);
    target.mCoursesLayout = Utils.findOptionalViewAsType(source, R.id.courses_layout, "field 'mCoursesLayout'", LinearLayout.class);
    target.mMutluplecourses = Utils.findOptionalViewAsType(source, R.id.mulitple_courses, "field 'mMutluplecourses'", MultiAutoCompleteTextView.class);
    target.mBtnUpdateCour = Utils.findOptionalViewAsType(source, R.id.btn_update_cour, "field 'mBtnUpdateCour'", Button.class);
    target.mDecriptionLayout = Utils.findOptionalViewAsType(source, R.id.description_layout, "field 'mDecriptionLayout'", LinearLayout.class);
    target.mDesciptionEt = Utils.findOptionalViewAsType(source, R.id.description_et, "field 'mDesciptionEt'", EditText.class);
    target.mBtnUpdateDesc = Utils.findOptionalViewAsType(source, R.id.btn_update_desc, "field 'mBtnUpdateDesc'", Button.class);
    target.mAddStudentLayout = Utils.findOptionalViewAsType(source, R.id.add_student_layout, "field 'mAddStudentLayout'", LinearLayout.class);
    target.mBtnAddStudent = Utils.findOptionalViewAsType(source, R.id.btn_add_student, "field 'mBtnAddStudent'", Button.class);
    target.mAutoComleterStudnetName = Utils.findOptionalViewAsType(source, R.id.auto_complete_student_name, "field 'mAutoComleterStudnetName'", AutoCompleteTextView.class);
    target.mBbtBackToSetting = Utils.findOptionalViewAsType(source, R.id.btn_back_to_setting, "field 'mBbtBackToSetting'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnUpdateDescription = null;
    target.mBtn_update_address = null;
    target.mBtnUpdateCourses = null;
    target.mBtnUpdatePhoneNumber = null;
    target.mBtnAddNewStudent = null;
    target.mMainLayout = null;
    target.mAddressLinear = null;
    target.mBtnUpdateAdre = null;
    target.mAdressEt = null;
    target.mPohneLayout = null;
    target.mPohneEt = null;
    target.mBtnUpdatePhone = null;
    target.mCoursesLayout = null;
    target.mMutluplecourses = null;
    target.mBtnUpdateCour = null;
    target.mDecriptionLayout = null;
    target.mDesciptionEt = null;
    target.mBtnUpdateDesc = null;
    target.mAddStudentLayout = null;
    target.mBtnAddStudent = null;
    target.mAutoComleterStudnetName = null;
    target.mBbtBackToSetting = null;
  }
}
