package com.lucamadd.cognitivechatbotdemo.controller;

import android.animation.LayoutTransition;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.lucamadd.cognitivechatbotdemo.R;

public class MessageLayoutCreator {

    private Context mContext;

    public MessageLayoutCreator(Context context) {
        this.mContext = context;
    }


    public RelativeLayout createBotMessage(String message) {

        //relativelayout
        RelativeLayout layout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, dpToPx(20, mContext));
        layout.setLayoutParams(layoutParams);
        layout.setLayoutTransition(new LayoutTransition());

        //imageview
        ImageView botPic = new ImageView(mContext);
        botPic.setBackground(mContext.getDrawable(R.drawable.bot));
        RelativeLayout.LayoutParams botPicParams = new RelativeLayout.LayoutParams(dpToPx(36, mContext), dpToPx(36, mContext));
        botPicParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        botPic.setLayoutParams(botPicParams);

        //textview
        TextView textView = new TextView(mContext);
        textView.setText(message);
        textView.setTextSize(16);
        textView.setBackground(mContext.getDrawable(R.drawable.green_message));
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(dpToPx(42, mContext), 0, 0, 0);
        textParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        textView.setLayoutParams(textParams);
        textView.setTypeface(ResourcesCompat.getFont(mContext,R.font.proximanovaregular));
        textView.setMaxWidth(dpToPx(240, mContext));
        textView.setPadding(dpToPx(8, mContext), dpToPx(8, mContext), dpToPx(8, mContext), dpToPx(8, mContext));
        textView.setTextColor(Color.parseColor("#ffffff"));

        //add all to layout
        layout.addView(botPic);
        layout.addView(textView);

        return layout;

    }

    public RelativeLayout createUserMessage(String message) {

        //relativelayout
        RelativeLayout layout = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, dpToPx(20, mContext));
        layout.setLayoutParams(layoutParams);
        layout.setLayoutTransition(new LayoutTransition());

        //imageview
        ImageView botPic = new ImageView(mContext);
        botPic.setBackground(mContext.getDrawable(R.drawable.user));
        RelativeLayout.LayoutParams botPicParams = new RelativeLayout.LayoutParams(dpToPx(36, mContext), dpToPx(36, mContext));
        botPicParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        botPic.setLayoutParams(botPicParams);

        //textview
        TextView textView = new TextView(mContext);
        textView.setText(message);
        textView.setTextSize(16);
        textView.setBackground(mContext.getDrawable(R.drawable.grey_message));
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0,0, dpToPx(42, mContext), 0);
        textParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        textView.setLayoutParams(textParams);
        textView.setTypeface(ResourcesCompat.getFont(mContext,R.font.proximanovaregular));
        textView.setMaxWidth(dpToPx(240, mContext));
        textView.setPadding(dpToPx(8, mContext), dpToPx(8, mContext), dpToPx(8, mContext), dpToPx(8, mContext));
        textView.setTextColor(Color.parseColor("#141414"));

        //add all to layout
        layout.addView(botPic);
        layout.addView(textView);

        return layout;

    }

    public static int dpToPx(int dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

}
