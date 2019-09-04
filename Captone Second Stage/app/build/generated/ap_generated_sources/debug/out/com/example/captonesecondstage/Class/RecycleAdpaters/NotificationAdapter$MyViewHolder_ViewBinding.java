// Generated code from Butter Knife. Do not modify!
package com.example.captonesecondstage.Class.RecycleAdpaters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.captonesecondstage.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationAdapter$MyViewHolder_ViewBinding implements Unbinder {
  private NotificationAdapter.MyViewHolder target;

  @UiThread
  public NotificationAdapter$MyViewHolder_ViewBinding(NotificationAdapter.MyViewHolder target,
      View source) {
    this.target = target;

    target.mImgDeleteClick = Utils.findOptionalViewAsType(source, R.id.img_delete_click, "field 'mImgDeleteClick'", ImageView.class);
    target.mCardViewnotification = Utils.findOptionalViewAsType(source, R.id.card_view_notification, "field 'mCardViewnotification'", CardView.class);
    target.mTxtnotificationStatus = Utils.findOptionalViewAsType(source, R.id.txt_notification_status, "field 'mTxtnotificationStatus'", TextView.class);
    target.mCircleNotificationStatus = Utils.findOptionalViewAsType(source, R.id.circleImg_profile, "field 'mCircleNotificationStatus'", CircleImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationAdapter.MyViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mImgDeleteClick = null;
    target.mCardViewnotification = null;
    target.mTxtnotificationStatus = null;
    target.mCircleNotificationStatus = null;
  }
}
