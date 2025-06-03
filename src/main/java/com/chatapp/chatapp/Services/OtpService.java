package com.chatapp.chatapp.Services;

import com.chatapp.chatapp.Dataloader.Entity.OtpToken;
import com.chatapp.chatapp.Dataloader.repository.OtpTokenRepository;
import com.chatapp.chatapp.Utils.EmailService.EmailSenderUtils;
import com.chatapp.chatapp.Utils.LoggerUtils.CaLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpTokenRepository otpRepo;

    @Autowired
    private EmailSenderUtils emailSenderUtils;

    public void sendOtp(String email, String username) {
        try {
            String otp = String.format("%06d", new Random().nextInt(999999));

            OtpToken token = new OtpToken();
            token.setEmail(email);
            token.setOtp(otp);
            token.setCreatedTime(LocalDateTime.now());
            token.setExpirationTime(LocalDateTime.now().plusMinutes(10));
            otpRepo.save(token);

//            CaLogger.caLogs.info("Sending Otp "+ otp +" for Email "+ email);
            CaLogger.logs.info("Otp sent to Email {}  for Otp  {}", otp,email);
            String html= forgetPassOtp(username, otp);
            emailSenderUtils.sendEmailWithTemplateAndAttachment(token.getEmail(),otp +" Your OTP Code For ChatApp!",html, null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send OTP", e);
        }
    }

    public boolean verifyOtp(String email, String otp) {
        boolean result= false;
        try {
            OtpToken otpToken= otpRepo.findByEmailAndOtp(email, otp);

            if (otpToken != null ) {
                CaLogger.logs.info("Otp Token is {} for Email {}", otpToken.getOtp(), otpToken.getEmail());

            result = otpToken.getExpirationTime().isAfter(LocalDateTime.now());

            if (result){
                String template =getWelcomeHtml("Dear User","www.google.co.in");
                emailSenderUtils.sendEmailWithTemplateAndAttachment(otpToken.getEmail(),"🎉 🎉 🎉 Welcome to ChatApp!",template, null);
            }
            CaLogger.logs.info("Otp veryfyed {}",result?"Success":"Fail");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static String forgetPassOtp(String username, String otp) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <title>Password Reset OTP</title>
                <style>
                    body {
                        font-family: sans-serif;
                        background-color: #f5f6fa;
                        padding: 20px;
                    }
                    .container {
                        background: white;
                        padding: 30px;
                        border-radius: 10px;
                        max-width: 600px;
                        margin: auto;
                        box-shadow: 0 0 8px rgba(0,0,0,0.1);
                    }
                    .otp {
                        font-size: 28px;
                        font-weight: bold;
                        color: #2980b9;
                        margin: 20px 0;
                        text-align: center;
                        letter-spacing: 4px;
                    }
                    .footer {
                        margin-top: 30px;
                        font-size: 12px;
                        color: gray;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>Password Reset Request</h2>
                    <p>Hi <b>%s</b>,</p>
                    <p>Your one-time password (OTP) for resetting your password is:</p>
                    <div class="otp">%s</div>
                    <p>This code will expire in <strong>10 minutes</strong>.</p>
                    <p>If you didn’t request this, you can safely ignore this email.</p>
                    <div class="footer">
                        &copy; 2025 ChatApp Security Team
                    </div>
                </div>
            </body>
            </html>
            """.formatted(username, otp);
    }

    private String getWelcomeHtml(String username, String activationLink) {
        return """
            <!DOCTYPE html>
            <html>
            <head>
              <meta charset="UTF-8">
              <style>
                body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }
                .email-container { background-color: #ffffff; padding: 20px; border-radius: 8px; max-width: 600px; margin: auto; }
                .header { font-size: 24px; color: #2c3e50; margin-bottom: 20px; }
                .body-text { font-size: 16px; color: #555; line-height: 1.6; }
                .footer { margin-top: 30px; font-size: 12px; color: #888; text-align: center; }
                .button { display: inline-block; background-color: #3498db; color: #fff; padding: 10px 20px; margin-top: 20px; border-radius: 4px; text-decoration: none; }
              </style>
            </head>
            <body>
              <div class="email-container">
                <div class="header">Welcome to ChatApp!</div>
                <div class="body-text">
                  Hi <strong>%s</strong>,<br><br>
                  We're excited to have you on board. You can start chatting with your friends now.<br><br>
                  <a href="%s" class="button">Activate Your Account</a><br><br>
                  Cheers,<br>
                  The ChatApp Team
                </div>
                <div class="footer">
                  &copy; 2025 ChatApp. All rights reserved.
                </div>
              </div>
            </body>
            </html>
        """.formatted(username, activationLink);
    }

    public static String generateOtpEmailHtml(String username, String otp) {
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
              <meta charset="UTF-8">
              <title>Your OTP Code</title>
              <style>
                body {
                  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                  background-color: #f5f6fa;
                  padding: 20px;
                }
                .email-container {
                  max-width: 600px;
                  margin: auto;
                  background: #ffffff;
                  padding: 30px;
                  border-radius: 10px;
                  box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
                }
                .otp-box {
                  background: #f0f8ff;
                  padding: 20px;
                  font-size: 28px;
                  letter-spacing: 4px;
                  font-weight: bold;
                  color: #2c3e50;
                  text-align: center;
                  border-radius: 8px;
                  margin: 20px 0;
                }
                .footer {
                  font-size: 12px;
                  color: #999;
                  text-align: center;
                  margin-top: 30px;
                }
              </style>
            </head>
            <body>
              <div class="email-container">
                <h2>Hello %s,</h2>
                <p>
                  We received a request to authenticate your action. Please use the OTP below to proceed:
                </p>
                <div class="otp-box">
                  %s
                </div>
                <p>
                  This code is valid for <strong>10 minutes</strong>. If you did not request this OTP, please ignore this email or contact our support team.
                </p>
                <p>
                  Thanks,<br>
                  <strong>ChatApp Security Team</strong>
                </p>
                <div class="footer">
                  &copy; 2025 ChatApp. All rights reserved.
                </div>
              </div>
            </body>
            </html>
        """.formatted(username, otp);
    }



}
