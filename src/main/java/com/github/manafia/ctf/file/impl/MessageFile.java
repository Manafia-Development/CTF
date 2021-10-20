package com.github.manafia.ctf.file.impl;

import com.github.manafia.ctf.CTF;
import com.github.manafia.ctf.file.CustomFile;
import com.github.manafia.ctf.util.Message;

public class MessageFile extends CustomFile {

    public MessageFile() {
        super(CTF.instance, "", "messages");
        for (Message message : Message.values()) {
            getConfig().addDefault(message.getConfig(), message.getMessage());
        }
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    public void init() {
        for (Message message : Message.values()) {
            message.setMessage(getConfig().getString(message.getConfig()));
        }
    }
}
