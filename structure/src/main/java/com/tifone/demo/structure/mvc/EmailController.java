package com.tifone.demo.structure.mvc;

public class EmailController {
    private EmailModel mModel;
    private EmailView mView;
    public EmailController(EmailView view) {
        mView = view;
        mModel = EmailModel.getInstance();
    }
    public void showDetail(String id) {
        if (!isValidate(id)) {
            onFailed("Invalidate id");
            return;
        }
        EmailContent content = mModel.getEmailContent(id);
        mView.showContentToUI(content);
    }

    public void checkUpdate() {
        if (!networkConnected()) {
            mView.showError("network disconnected");
            return;
        }
        mModel.checkNewEmail();
    }
    private boolean networkConnected() {
        return true;
    }

    public void deleteEmail(String id) {
        if (!isValidate(id)) {
            onFailed("Invalidate id");
            return;
        }
        mModel.deleteEmail(id);
    }
    private void onFailed(String msg) {
        mView.showError(msg);
    }
    private boolean isValidate(String id) {
        return id.length() > 5 && id.length() < 50;
    }
}
