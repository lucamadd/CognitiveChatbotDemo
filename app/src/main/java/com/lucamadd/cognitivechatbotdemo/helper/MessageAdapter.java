package com.lucamadd.cognitivechatbotdemo.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eyalbira.loadingdots.LoadingDots;
import com.lucamadd.cognitivechatbotdemo.R;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;


public class MessageAdapter extends BaseAdapter {

    private List<Message> messages = new ArrayList<>();
    private Context context;
    private int lastPosition = -1;

    public MessageAdapter(Context context) {
        this.context = context;
    }

    public void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged(); // to render the list we need to notify
    }

    public void remove(){
        try {
            if (!messages.isEmpty()) {
                for (Message m : messages)
                    if (m.getMessageType() == Message.BOT_THINKING)
                        messages.remove(m);
            }
        } catch (ConcurrentModificationException e){

        }
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        String message = messages.get(i).getText();
        int messageType = messages.get(i).getMessageType();

        switch (messageType){
            case Message.USER_MESSAGE:
                convertView = messageInflater.inflate(R.layout.user_message, null);
                holder.messageBody = convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message);
                if (i > lastPosition){
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
                    convertView.startAnimation(animation);
                    lastPosition = i;
                }
                break;
            case Message.BOT_MESSAGE:
                convertView = messageInflater.inflate(R.layout.bot_message, null);
                holder.messageBody = convertView.findViewById(R.id.message_body);
                convertView.setTag(holder);
                holder.messageBody.setText(message);
                if (i > lastPosition){
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.thinking);
                    convertView.startAnimation(animation);
                    lastPosition = i;
                }

                break;
            case Message.BOT_THINKING:
                convertView = messageInflater.inflate(R.layout.bot_thinking, null);
                holder.messageLoading = convertView.findViewById(R.id.loading_dots);
                convertView.setTag(holder);
                break;
        }




        return convertView;
    }



}

class MessageViewHolder {
    public LoadingDots messageLoading;
    public TextView messageBody;
}