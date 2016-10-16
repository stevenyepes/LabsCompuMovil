package co.edu.udea.compumovil.gr4.lab4fcm.entities;

import java.util.Date;

/**
 * Created by steven on 16/10/16.
 */

public class ChatMessage {

    private String mtext ;
    private String mSender;
    private Date mDate;

    public ChatMessage() {
    }

    public String getMtext() {
        return mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }

    public String getmSender() {
        return mSender;
    }

    public void setmSender(String mSender) {
        this.mSender = mSender;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }
}
