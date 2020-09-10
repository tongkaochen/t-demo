package com.tifone.demo.structure.mvc;

import java.util.ArrayList;
import java.util.List;

public class EmailModel {
    private List<EmailContent> mContents = new ArrayList<>();
    private List<EmailUpdateListener> mListener = new ArrayList<>();
    private static EmailModel INSTANCE;
    private EmailModel(){}
    public void registerEmailUpdateListener(EmailUpdateListener listener) {
        mListener.add(listener);
    }

    public EmailContent getEmailContent(String id) {
        return getContentForId(id);
    }
    public void checkNewEmail() {
        try {
            Thread.sleep(1000);
            EmailContent content = new EmailContent("Test", "Test","Test","Test","Test");
            mContents.add(content);
            publicNewEmail(content);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void publicNewEmail(EmailContent content) {
        for (EmailUpdateListener listener : mListener) {
            listener.onReceive(content.getId(), content.getTitle());
        }
    }

    private EmailContent getContentForId(String id) {
        for (EmailContent content : mContents) {
            if (content.getId().equals(id)) {
                return content;
            }
        }
        return null;
    }

    public void deleteEmail(String id) {
        int index = -1;
        for (EmailContent content : mContents) {
            if (content.getId().equals(id)) {
                index = mContents.indexOf(content);
            }
        }
        if (index != -1) {
            EmailContent content = mContents.remove(index);
            notifyDeleted(content.getId());
        }
    }

    private void notifyDeleted(String id) {
        for (EmailUpdateListener listener : mListener) {
            listener.onDeleted(id);
        }
    }

    interface EmailUpdateListener {
        void onReceive(String id, String title);
        void onDeleted(String id);
    }

    /**
     * singleton instance
     */
    public static EmailModel getInstance() {
        if (INSTANCE == null) {
            synchronized (EmailModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new EmailModel();
                }
            }
        }
        return INSTANCE;
    }
}
