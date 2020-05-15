package com.lucamadd.cognitivechatbotdemo;

import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lucamadd.cognitivechatbotdemo.controller.MessageLayoutCreator;
import com.lucamadd.cognitivechatbotdemo.helper.BotBrain;
import com.lucamadd.cognitivechatbotdemo.helper.Message;
import com.lucamadd.cognitivechatbotdemo.helper.MessageAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private MessageAdapter messageAdapter;
    private ListView messagesView;
    private Button sendButton;
    private BotBrain bot;
    private LinearLayout disclaimerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText = findViewById(R.id.edittext_chatbox);

        messageAdapter = new MessageAdapter(this);
        messagesView = findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        disclaimerLayout = findViewById(R.id.disclaimer_layout);

        sendButton = findViewById(R.id.button_chatbox_send);
        bot = new BotBrain(this);


    }

    public void sendMessage(View view) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(40);
        final String message = editText.getText().toString();
        if (message.length() > 0) {
            messageAdapter.remove();
            disclaimerLayout.setVisibility(View.GONE);
            messageAdapter.add(new Message(message,Message.USER_MESSAGE));
            // scroll the ListView to the last added element
            messagesView.smoothScrollToPosition(messagesView.getCount() - 1);
            editText.getText().clear();

            sendButton.setEnabled(false);
            sendButton.setBackground(getDrawable(R.drawable.ic_send_disabled));
            final Handler handler = new Handler();
            final String botMessage = bot.getBotReply(message);
            handler.postDelayed(new Runnable() {
                public void run() {
                    messageAdapter.add(new Message(botMessage,Message.BOT_THINKING));
                    messagesView.smoothScrollToPosition(messagesView.getCount() - 1);
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            messageAdapter.remove();
                            messageAdapter.add(new Message(botMessage,Message.BOT_MESSAGE));
                            messagesView.smoothScrollToPosition(messagesView.getCount() - 1);
                            sendButton.setEnabled(true);
                            sendButton.setBackground(getDrawable(R.drawable.ic_send));

                        }
                    }, 150 * botMessage.length());
                }
            }, 1000);






        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
