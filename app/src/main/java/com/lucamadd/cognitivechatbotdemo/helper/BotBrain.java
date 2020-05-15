package com.lucamadd.cognitivechatbotdemo.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;


import com.rivescript.Config;
import com.rivescript.RiveScript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BotBrain {

    private RiveScript brain;
    private Context mContext;

    public BotBrain(Context context){
        mContext = context;

        brain = new RiveScript(Config.newBuilder()
                .throwExceptions(false)          // Whether exception throwing is enabled
                .strict(true)                    // Whether strict syntax checking is enabled
                .utf8(true)                     // Whether UTF-8 mode is enabled
                .unicodePunctuation("[.,!?;:]")  // The unicode punctuation pattern
                .forceCase(false)                // Whether forcing triggers to lowercase is enabled
                .depth(50)                       // The recursion depth limit
                .build());

        brain.stream(readBotFile());
        // Sort the replies after loading them!
        brain.sortReplies();

    }


    public String getBotReply(String userMessage){
        // Get a reply.
        Log.i("USER MESSAGE IS:",userMessage);
        String reply = brain.reply("user", userMessage);
        return reply;
    }

    private String readBotFile(){
        String str;
        try {
            InputStream inputStream = mContext.getAssets().open("basic.rive");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            str = new String(buffer);
        } catch (IOException e){
            str = "! version = 2.0\n+ *\n-C'è stato un errore.";
        }
        return str;
    }

}
