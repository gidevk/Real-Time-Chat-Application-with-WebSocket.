package com.chatapp.chatapp.Utils.EmailService;

import com.chatapp.chatapp.Utils.LoggerUtils.CaLogger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailSenderUtils {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public void sendEmailWithAttachment(String to, String subject, String body, File attachment) {
        try {
            CaLogger.logs.info("this is sendEmailWithAttachment class");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // 'true' indicates multipart message

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            if (attachment != null && attachment.exists()) {
                CaLogger.logs.info("Attachment Name {} And Attachment {}",attachment.getName(), attachment);
                helper.addAttachment(attachment.getName(), attachment);
            }

            javaMailSender.send(message);
        } catch (Exception e) {
CaLogger.logs.error("Exception Occurred in sendEmailWithAttachment ",e);        }
    }


/*
    public void sendWelcomeEmail(String to, String username, String activationLink) {
        try {
            CaLogger.caLogs.info("Sending a welcome mail for "+ username +" to "+ to );

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String html = getWelcomeHtml(username, activationLink);

            helper.setTo(to);
            helper.setSubject("🎉 Welcome to ChatApp!");
            helper.setText(html, true); // Set HTML to true

            javaMailSender.send(message);
            CaLogger.caLogs.info("Mail sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
*/


    public void sendEmailWithTemplateAndAttachment(String to, String subject , String template, File attachment) {
        try {
            CaLogger.logs.info("Sending a sendOtpOnEmail mail to "+ to );

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);//"Your OTP Code For ChatApp!");
            helper.setText(template, true); // Set HTML to true

            if (attachment != null && attachment.exists()) {
                CaLogger.logs.info("Attachment Name {} And Attachemnt {}",attachment.getName(), attachment);
                helper.addAttachment(attachment.getName(), attachment);
            }

            javaMailSender.send(message);
            CaLogger.logs.info("sendOtpOnEmail sent successfully");
        } catch (MessagingException e) {
            CaLogger.logs.error("Exception occurred in Email sender ",e);
        }

    }
   
    /*public void sendOtpOnEmail(String to, String username, String otp) {
        try {
            CaLogger.caLogs.info("Sending a sendOtpOnEmail mail for "+ username +" to "+ to );

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            String html = generateOtpEmailHtml(username, otp);

            helper.setTo(to);
            helper.setSubject("Your OTP Code For ChatApp!");
            helper.setText(html, true); // Set HTML to true

            javaMailSender.send(message);
            CaLogger.caLogs.info("sendOtpOnEmail sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }*/

}
