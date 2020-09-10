package com.tifone.demo.structure.mvc;

public class EmailView implements EmailModel.EmailUpdateListener{
    private EmailController mController;
    private EmailModel mModel;
    public EmailView() {
        mController = new EmailController(this);
        mModel = EmailModel.getInstance();
        mModel.registerEmailUpdateListener(this);
    }
    private void showDetail(String id) {
        mController.showDetail(id);
    }

    private void checkUpdate() {
        mController.checkUpdate();
    }

    private void deleteEmail(String id) {
        mController.deleteEmail(id);
    }

    public void showContentToUI(EmailContent content) {
        // show email content
    }

    @Override
    public void onReceive(String id, String title) {
        // receive a new email
        // display email title
    }

    @Override
    public void onDeleted(String id) {
        // email for specify id is deleted
    }

    public void showError(String msg) {
        // show error information
    }
}
