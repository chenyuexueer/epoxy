package com.airbnb.epoxy.sample.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.epoxy.ModelProp;
import com.airbnb.epoxy.ModelProp.Option;
import com.airbnb.epoxy.ModelView;
import com.airbnb.epoxy.R;
import com.airbnb.epoxy.TextProp;
import com.airbnb.paris.annotations.Style;
import com.airbnb.paris.annotations.Styleable;
import com.airbnb.paris.annotations.StyleableField;

import butterknife.BindView;
import butterknife.ButterKnife;

@Styleable("HeaderView")
@ModelView
public class HeaderView extends LinearLayout {

  @StyleableField(R.styleable.HeaderView_titleText)
  @BindView(R.id.title_text) TextView title;
  @BindView(R.id.caption_text) TextView caption;

  @Style static final int DEFAULT = R.style.HeaderView;
  @Style static final int GREEN = R.style.HeaderView_Green;

  public HeaderView(Context context) {
    super(context);
    init();
  }

  public HeaderView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
    //
  }

  private void init() {
    setOrientation(VERTICAL);
    inflate(getContext(), R.layout.view_header, this);
    ButterKnife.bind(this);
  }

  @TextProp(defaultRes = R.string.app_name)
  public void setTitle(CharSequence title) {
    this.title.setText(title);
  }

  @ModelProp(options = Option.GenerateStringOverloads)
  public void setCaption(CharSequence caption) {
    this.caption.setText(caption);
  }
}
