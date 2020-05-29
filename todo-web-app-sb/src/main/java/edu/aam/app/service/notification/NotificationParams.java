package edu.aam.app.service.notification;

public class NotificationParams {
    private String receiver;
    private String subject;
    private String message;

    public NotificationParams() {

    }

    public NotificationParams(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }
}
