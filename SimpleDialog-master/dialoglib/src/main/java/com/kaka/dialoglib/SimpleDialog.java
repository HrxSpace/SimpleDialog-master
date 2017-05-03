package com.kaka.dialoglib;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hrx on 2016/9/13.
 * dialog对话框
 */

public class SimpleDialog extends DialogFragment {
    public static final String TAG = SimpleDialog.class.getSimpleName();
    private Builder builder;
    private static SimpleDialog instance = new SimpleDialog();

    public static SimpleDialog getInstance() {
        return instance;
    }

    private CardView mCardView;//整个View
    private AppCompatImageView mImage;//头部图片
    private TextView mTitle, mSubTitle, mContent;//主标题，副标题，正文
    private Button mBtnLeft, mBtnMiddle, mBtnRight;//左按键，中按键，右按键
    private LinearLayout mBtnPanel;//按键面板

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        this.setCancelable(true);
        if (savedInstanceState != null) {
            if (builder != null) {
                builder = savedInstanceState.getParcelable(Builder.class.getSimpleName());
            }
        }
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (builder != null)
            outState.putParcelable(Builder.class.getSimpleName(), builder);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_simple, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        if (builder != null) {
            //点击外部是否消失
            getDialog().setCanceledOnTouchOutside(builder.isCanceledOnTouchOutside());

            //标题
            if (builder.getTitleText() != null) {
                mTitle.setText(builder.getTitleText());
                if (builder.getTitleColor() != 0) {
                    mTitle.setTextColor(ContextCompat.getColor(getActivity(), builder.getTitleColor()));
                }
                if (builder.getTitleSize() != 0) {
                    mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(builder.getTitleSize()));
                }
                if (builder.isTitleBold()) {
                    mTitle.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (builder.getTitleGravity() != null) {//标题位置
                    switch (builder.getTitleGravity()) {
                        case LEFT:
                            mTitle.setGravity(Gravity.START);
                            break;
                        case RIGHT:
                            mTitle.setGravity(Gravity.END);
                            break;
                        case CENTER:
                            mTitle.setGravity(Gravity.CENTER);
                            break;
                    }
                }
            } else {
                mTitle.setVisibility(View.GONE);
            }

            //副标题
            if (builder.getSubTitleText() != null) {
                mSubTitle.setText(builder.getSubTitleText());
                if (builder.getSubTitleColor() != 0) {
                    mSubTitle.setTextColor(ContextCompat.getColor(getActivity(), builder.getSubTitleColor()));
                }
                if (builder.getSubTitleSize() != 0) {
                    mSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(builder.getSubTitleSize()));
                }
                if (builder.isSubTitleBold()) {
                    mSubTitle.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (builder.getSubTitleGravity() != null) {//标题位置
                    switch (builder.getSubTitleGravity()) {
                        case LEFT:
                            mTitle.setGravity(Gravity.START);
                            break;
                        case RIGHT:
                            mTitle.setGravity(Gravity.END);
                            break;
                        case CENTER:
                            mTitle.setGravity(Gravity.CENTER);
                            break;
                    }
                }
            } else {
                mSubTitle.setVisibility(View.GONE);
            }

            //正文
            if (builder.getContentText() != null) {
                mContent.setText(builder.getContentText());
                if (builder.getContentColor() != 0) {
                    mContent.setTextColor(ContextCompat.getColor(getActivity(), builder.getContentColor()));
                }
                if (builder.getContentSize() != 0) {
                    mContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(builder.getContentSize()));
                }
                if (builder.isContentBold()) {
                    mContent.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (builder.getContentGravity() != null) {//标题位置
                    switch (builder.getContentGravity()) {
                        case LEFT:
                            mContent.setGravity(Gravity.START);
                            break;
                        case RIGHT:
                            mContent.setGravity(Gravity.END);
                            break;
                        case CENTER:
                            mContent.setGravity(Gravity.CENTER);
                            break;
                    }
                }
            } else {
                mContent.setVisibility(View.GONE);
            }

            //左键
            if (builder.getLeftBtnText() != null) {
                mBtnLeft.setText(builder.getLeftBtnText());
                if (builder.getRightBtnColor() != 0) {
                    mBtnLeft.setTextColor(ContextCompat.getColor(getActivity(), builder.getRightBtnColor()));
                }
                if (builder.getLeftBtnSize() != 0) {
                    mBtnLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(builder.getLeftBtnSize()));
                }
                if (builder.isLeftBtnBold()) {
                    mBtnLeft.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (builder.getOnLeftClicked() != null) {
                    mBtnLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.getOnLeftClicked().OnClick(v, getDialog());
                        }
                    });
                }
            } else {
                mBtnLeft.setVisibility(View.GONE);
            }
            //中键
            if (builder.getMiddleBtnText() != null) {
                mBtnMiddle.setText(builder.getMiddleBtnText());
                if (builder.getMiddleBtnColor() != 0) {
                    mBtnMiddle.setTextColor(ContextCompat.getColor(getActivity(), builder.getMiddleBtnColor()));
                }
                if (builder.getMiddleBtnSize() != 0) {
                    mBtnMiddle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(builder.getMiddleBtnSize()));
                }
                if (builder.isMiddleBtnBold()) {
                    mBtnMiddle.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (builder.getOnMiddleClicked() != null) {
                    mBtnMiddle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.getOnMiddleClicked().OnClick(v, getDialog());
                        }
                    });
                }
            } else {
                mBtnMiddle.setVisibility(View.GONE);
            }
            //右键
            if (builder.getRightBtnText() != null) {
                mBtnRight.setText(builder.getRightBtnText());
                if (builder.getRightBtnColor() != 0) {
                    mBtnRight.setTextColor(ContextCompat.getColor(getActivity(), builder.getRightBtnColor()));
                }
                if (builder.getRightBtnSize() != 0) {
                    mBtnRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(builder.getRightBtnSize()));
                }
                if (builder.isRightBtnBold()) {
                    mBtnRight.setTypeface(Typeface.DEFAULT_BOLD);
                }
                if (builder.getOnRightClicked() != null) {
                    mBtnRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            builder.getOnRightClicked().OnClick(v, getDialog());
                        }
                    });
                }
            } else {
                mBtnRight.setVisibility(View.GONE);
            }
            //图片资源
            if (builder.getImageRecourse() != 0) {
                Drawable imageRes = VectorDrawableCompat.create(getResources(), builder.getImageRecourse(), getActivity().getTheme());
                mImage.setImageDrawable(imageRes);
            } else if (builder.getImageDrawable() != null) {
                mImage.setImageDrawable(builder.getImageDrawable());
            } else {
                mImage.setVisibility(View.GONE);
            }
            //背景颜色
            if (builder.getBackgroundColor() != 0) {
                mCardView.setCardBackgroundColor(ContextCompat.getColor(getActivity(), builder.getBackgroundColor()));
            }

            if (builder.isAutoClose()) {
                int time = builder.getTimeToHide() != 0 ? builder.getTimeToHide() : 10000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (isAdded() && getActivity() != null)
                            dismiss();
                    }
                }, time);
            }
            //按键面板位置
            if (builder.getPanelGravity() != null && mBtnPanel != null) {
                switch (builder.getPanelGravity()) {
                    case LEFT:
                        mBtnPanel.setGravity(Gravity.START);
                        break;
                    case RIGHT:
                        mBtnPanel.setGravity(Gravity.END);
                        break;
                    case CENTER:
                        mBtnPanel.setGravity(Gravity.CENTER);
                        break;
                }
            }
        }
    }

    private void initViews(View view) {
        mCardView = (CardView) view.findViewById(R.id.card_view);
        mImage = (AppCompatImageView) view.findViewById(R.id.image);
        mTitle = (TextView) view.findViewById(R.id.tx_title);
        mSubTitle = (TextView) view.findViewById(R.id.tx_sub_title);
        mContent = (TextView) view.findViewById(R.id.content);
        mBtnLeft = (Button) view.findViewById(R.id.btn_right);
        mBtnMiddle = (Button) view.findViewById(R.id.btn_middle);
        mBtnRight = (Button) view.findViewById(R.id.btn_left);
        mBtnPanel = (LinearLayout) view.findViewById(R.id.btns_panel);
    }

    private Dialog show(Activity activity, Builder builder) {
        this.builder = builder;
        if (!isAdded())
            show(((AppCompatActivity) activity).getSupportFragmentManager(), TAG);
        return getDialog();
    }

    public static class Builder implements Parcelable {

        private Context context;

        private boolean canceledOnTouchOutside;//点击外部取消

        private String leftBtnText;//左按键
        private String middleBtnText;//中按键
        private String rightBtnText;//右按键

        private String titleText;//大标题内容
        private String subTitleText;//副标题内容
        private String contentText;//正文内容

        private OnLeftClicked onLeftClicked;//左按键监听
        private OnMiddleClicked onMiddleClicked;//中按键监听
        private OnRightClicked onRightClicked;//右按键监听

        private boolean autoClose;//true--自动关闭，false--不自动关闭
        private int timeToHide;//自动关闭时间

        private int leftBtnColor;//左按键颜色
        private int middleBtnColor;//中按键颜色
        private int rightBtnColor;//右按键颜色
        private int backgroundColor;//card背景颜色
        private int titleColor;//标题背景颜色
        private int subTitleColor;//副标题背景颜色
        private int contentColor;//正文颜色

        private int imageRecourse;//图片资源
        private Drawable imageDrawable;//图片资源

        private int leftBtnSize;//左按键size
        private int middleBtnSize;//中按键size
        private int rightBtnSize;//右按键size
        private int titleSize;//大标题size
        private int subTitleSize;//副标题size
        private int contentSize;//正文size

        private boolean titleBold;//标题，true--加粗
        private boolean subTitleBold;//副标题，true--加粗
        private boolean contentBold;//正文，true--加粗
        private boolean leftBtnBold;//左按键，true--加粗
        private boolean middleBtnBold;//中按键，true--加粗
        private boolean rightBtnBold;//右按键，true--加粗

        private PanelGravity panelGravity;//按键面板位置
        private PanelGravity titleGravity;//大标题位置
        private PanelGravity subTitleGravity;//副标题位置
        private PanelGravity contentGravity;//正文位置

        public Builder(Context mContext) {
            this.context = mContext;
        }

        /*************************************设置内容*********************************/
        public Builder setLeftBtnText(String leftBtnText) {
            this.leftBtnText = leftBtnText;
            return this;
        }

        public Builder setMiddleBtnText(String middleBtnText) {
            this.middleBtnText = middleBtnText;
            return this;
        }

        public Builder setRightBtnText(String rightBtnText) {
            this.rightBtnText = rightBtnText;
            return this;
        }

        public Builder setTitleText(String titleText) {
            this.titleText = titleText;
            return this;
        }

        public Builder setSubTitleText(String subTitleText) {
            this.subTitleText = subTitleText;
            return this;
        }

        public Builder setContentText(String contentText) {
            this.contentText = contentText;
            return this;
        }

        /*************************************设置监听*********************************/
        public Builder setOnLeftClicked(OnLeftClicked onLeftClicked) {
            this.onLeftClicked = onLeftClicked;
            return this;
        }

        public Builder setOnMiddleClicked(OnMiddleClicked onMiddleClicked) {
            this.onMiddleClicked = onMiddleClicked;
            return this;
        }

        public Builder setOnRightClicked(OnRightClicked onRightClicked) {
            this.onRightClicked = onRightClicked;
            return this;
        }

        /*************************************设置自动关闭*********************************/
        public Builder setAutoClose(boolean autoClose) {
            this.autoClose = autoClose;
            return this;
        }

        public Builder setTimeToHide(int timeToHide) {
            this.timeToHide = timeToHide;
            return this;
        }

        /*************************************设置样式*********************************/

        /**
         * 设置左键样式
         *
         * @param size  字体大小
         * @param color 颜色
         * @param bold  是否加粗，true-加粗
         */
        public Builder setLeftBtnStyle(int size, int color, boolean bold) {
            this.leftBtnSize = size;
            this.leftBtnColor = color;
            this.leftBtnBold = bold;
            return this;
        }

        /**
         * 设置中键样式
         *
         * @param size  字体大小
         * @param color 颜色
         * @param bold  是否加粗，true-加粗
         */
        public Builder setMiddleBtnStyle(int size, int color, boolean bold) {
            this.middleBtnSize = size;
            this.middleBtnColor = color;
            this.middleBtnBold = bold;
            return this;
        }

        /**
         * 设置右键样式
         *
         * @param size  字体大小
         * @param color 颜色
         * @param bold  是否加粗，true-加粗
         */
        public Builder setRightBtnStyle(int size, int color, boolean bold) {
            this.rightBtnSize = size;
            this.rightBtnColor = color;
            this.rightBtnBold = bold;
            return this;
        }

        /**
         * 设置标题的样式
         *
         * @param size  字体大小
         * @param color 颜色
         * @param bold  是否加粗，true-加粗
         */
        public Builder setTitleStyle(int size, int color, boolean bold) {
            this.titleSize = size;
            this.titleColor = color;
            this.titleBold = bold;
            return this;
        }

        /**
         * 设置副标题的样式
         *
         * @param size  字体大小
         * @param color 颜色
         * @param bold  是否加粗，true-加粗
         */
        public Builder setSubTitleStyle(int size, int color, boolean bold) {
            this.subTitleSize = size;
            this.subTitleColor = color;
            this.subTitleBold = bold;
            return this;
        }

        /**
         * 设置正文的样式
         *
         * @param size  字体大小
         * @param color 颜色
         * @param bold  是否加粗，true-加粗
         */
        public Builder setContentStyle(int size, int color, boolean bold) {
            this.contentSize = size;
            this.contentColor = color;
            this.contentBold = bold;
            return this;
        }

        /*************************************dialog样式设置*********************************/
        /**
         * 设置点击外部是否消失
         *
         * @param cancelAble true--消失，false--不消失
         */
        public Builder setCanceledOnTouchOutside(boolean cancelAble) {
            this.canceledOnTouchOutside = cancelAble;
            return this;
        }

        /**
         * 设置背景颜色
         *
         * @param color 颜色
         */
        public Builder setBackgroundColor(int color) {
            this.backgroundColor = color;
            return this;
        }

        /**
         * 设置按键面板的位置
         *
         * @param panelGravity LEFT,CENTER,RIGHT
         */
        public Builder setPanelGravity(PanelGravity panelGravity) {
            this.panelGravity = panelGravity;
            return this;
        }

        public Builder setTitleGravity(PanelGravity titleGravity) {
            this.titleGravity = titleGravity;
            return this;
        }

        public Builder setSubTitleGravity(PanelGravity subTitleGravity) {
            this.subTitleGravity = subTitleGravity;
            return this;
        }

        public Builder setContentGravity(PanelGravity contentGravity) {
            this.contentGravity = contentGravity;
            return this;
        }

        /*************************************图片资源*********************************/
        /**
         * 设置图片资源
         *
         * @param imageRecourse id
         */
        public Builder setImageRecourse(int imageRecourse) {
            this.imageRecourse = imageRecourse;
            return this;
        }

        /**
         * 设置图片资源
         *
         * @param imageDrawable Drawable
         */
        public Builder setImageDrawable(Drawable imageDrawable) {
            this.imageDrawable = imageDrawable;
            return this;
        }

        /*************************************获得内容*********************************/
        public String getLeftBtnText() {
            return leftBtnText;
        }

        public String getMiddleBtnText() {
            return middleBtnText;
        }

        public String getRightBtnText() {
            return rightBtnText;
        }

        public String getTitleText() {
            return titleText;
        }

        public String getSubTitleText() {
            return subTitleText;
        }

        public String getContentText() {
            return contentText;
        }

        /*************************************获得监听*********************************/
        public OnLeftClicked getOnLeftClicked() {
            return onLeftClicked;
        }

        public OnMiddleClicked getOnMiddleClicked() {
            return onMiddleClicked;
        }

        public OnRightClicked getOnRightClicked() {
            return onRightClicked;
        }

        /*************************************获得自动关闭的设置*********************************/
        public boolean isAutoClose() {
            return autoClose;
        }

        public int getTimeToHide() {
            return timeToHide;
        }

        /*************************************获得设置的样式*********************************/
        public int getLeftBtnSize() {
            return leftBtnSize;
        }

        public int getMiddleBtnSize() {
            return middleBtnSize;
        }

        public int getRightBtnSize() {
            return rightBtnSize;
        }

        public int getTitleSize() {
            return titleSize;
        }

        public int getSubTitleSize() {
            return subTitleSize;
        }

        public int getContentSize() {
            return contentSize;
        }

        public int getLeftBtnColor() {
            return leftBtnColor;
        }

        public int getMiddleBtnColor() {
            return middleBtnColor;
        }

        public int getRightBtnColor() {
            return rightBtnColor;
        }

        public int getBackgroundColor() {
            return backgroundColor;
        }

        public int getTitleColor() {
            return titleColor;
        }

        public int getSubTitleColor() {
            return subTitleColor;
        }

        public int getContentColor() {
            return contentColor;
        }

        public boolean isCanceledOnTouchOutside() {
            return canceledOnTouchOutside;
        }

        public boolean isTitleBold() {
            return titleBold;
        }

        public boolean isSubTitleBold() {
            return subTitleBold;
        }

        public boolean isContentBold() {
            return contentBold;
        }

        public boolean isLeftBtnBold() {
            return leftBtnBold;
        }

        public boolean isMiddleBtnBold() {
            return middleBtnBold;
        }

        public boolean isRightBtnBold() {
            return rightBtnBold;
        }

        /*************************************获得图片资源*********************************/
        public int getImageRecourse() {
            return imageRecourse;
        }

        public Drawable getImageDrawable() {
            return imageDrawable;
        }

        /*************************************获得对齐位置*********************************/
        public PanelGravity getPanelGravity() {
            return panelGravity;
        }

        public PanelGravity getTitleGravity() {
            return titleGravity;
        }

        public PanelGravity getSubTitleGravity() {
            return subTitleGravity;
        }

        public PanelGravity getContentGravity() {
            return contentGravity;
        }

        /**
         * 获得最终的builder
         */
        public Builder build() {
            return this;
        }

        /**
         * 显示出来
         */
        public Dialog show() {
            return SimpleDialog.getInstance().show(((Activity) context), this);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.canceledOnTouchOutside ? (byte) 1 : (byte) 0);
            dest.writeString(this.leftBtnText);
            dest.writeString(this.middleBtnText);
            dest.writeString(this.rightBtnText);
            dest.writeString(this.titleText);
            dest.writeString(this.subTitleText);
            dest.writeString(this.contentText);
            dest.writeByte(this.autoClose ? (byte) 1 : (byte) 0);
            dest.writeInt(this.timeToHide);
            dest.writeInt(this.leftBtnColor);
            dest.writeInt(this.middleBtnColor);
            dest.writeInt(this.rightBtnColor);
            dest.writeInt(this.backgroundColor);
            dest.writeInt(this.titleColor);
            dest.writeInt(this.subTitleColor);
            dest.writeInt(this.contentColor);
            dest.writeInt(this.imageRecourse);
            dest.writeInt(this.leftBtnSize);
            dest.writeInt(this.middleBtnSize);
            dest.writeInt(this.rightBtnSize);
            dest.writeInt(this.titleSize);
            dest.writeInt(this.subTitleSize);
            dest.writeInt(this.contentSize);
            dest.writeByte(this.titleBold ? (byte) 1 : (byte) 0);
            dest.writeByte(this.subTitleBold ? (byte) 1 : (byte) 0);
            dest.writeByte(this.contentBold ? (byte) 1 : (byte) 0);
            dest.writeByte(this.leftBtnBold ? (byte) 1 : (byte) 0);
            dest.writeByte(this.middleBtnBold ? (byte) 1 : (byte) 0);
            dest.writeByte(this.rightBtnBold ? (byte) 1 : (byte) 0);
            dest.writeInt(this.panelGravity == null ? -1 : this.panelGravity.ordinal());
            dest.writeInt(this.titleGravity == null ? -1 : this.titleGravity.ordinal());
            dest.writeInt(this.subTitleGravity == null ? -1 : this.subTitleGravity.ordinal());
            dest.writeInt(this.contentGravity == null ? -1 : this.contentGravity.ordinal());
        }

        protected Builder(Parcel in) {
            this.canceledOnTouchOutside = in.readByte() != 0;
            this.leftBtnText = in.readString();
            this.middleBtnText = in.readString();
            this.rightBtnText = in.readString();
            this.titleText = in.readString();
            this.subTitleText = in.readString();
            this.contentText = in.readString();
            this.autoClose = in.readByte() != 0;
            this.timeToHide = in.readInt();
            this.leftBtnColor = in.readInt();
            this.middleBtnColor = in.readInt();
            this.rightBtnColor = in.readInt();
            this.backgroundColor = in.readInt();
            this.titleColor = in.readInt();
            this.subTitleColor = in.readInt();
            this.contentColor = in.readInt();
            this.imageRecourse = in.readInt();
            this.leftBtnSize = in.readInt();
            this.middleBtnSize = in.readInt();
            this.rightBtnSize = in.readInt();
            this.titleSize = in.readInt();
            this.subTitleSize = in.readInt();
            this.contentSize = in.readInt();
            this.titleBold = in.readByte() != 0;
            this.subTitleBold = in.readByte() != 0;
            this.contentBold = in.readByte() != 0;
            this.leftBtnBold = in.readByte() != 0;
            this.middleBtnBold = in.readByte() != 0;
            this.rightBtnBold = in.readByte() != 0;
            int tmpBtnPanelGravity = in.readInt();
            this.panelGravity = tmpBtnPanelGravity == -1 ? null : PanelGravity.values()[tmpBtnPanelGravity];
            this.titleGravity = tmpBtnPanelGravity == -1 ? null : PanelGravity.values()[tmpBtnPanelGravity];
            this.subTitleGravity = tmpBtnPanelGravity == -1 ? null : PanelGravity.values()[tmpBtnPanelGravity];
            this.contentGravity = tmpBtnPanelGravity == -1 ? null : PanelGravity.values()[tmpBtnPanelGravity];
        }

        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() {
            @Override
            public Builder createFromParcel(Parcel source) {
                return new Builder(source);
            }

            @Override
            public Builder[] newArray(int size) {
                return new Builder[size];
            }
        };
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public interface OnLeftClicked {
        void OnClick(View view, Dialog dialog);
    }

    public interface OnMiddleClicked {
        void OnClick(View view, Dialog dialog);
    }

    public interface OnRightClicked {
        void OnClick(View view, Dialog dialog);
    }

    public enum PanelGravity {
        LEFT,
        RIGHT,
        CENTER
    }
}
