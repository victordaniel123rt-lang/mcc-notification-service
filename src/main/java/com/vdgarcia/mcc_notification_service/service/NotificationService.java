package com.vdgarcia.mcc_notification_service.service;

import com.vdgarcia.mcc_notification_service.event.CreditDisbursementEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    public void sendNotification(CreditDisbursementEvent creditDisbursementEvent){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("victordaniel.123rt@gmail.com");
            mimeMessageHelper.setTo(creditDisbursementEvent.getEmail());
            mimeMessageHelper.setSubject("Notification Service");
            mimeMessageHelper.setText("Dear customer, your credit disbursement of s/ " + creditDisbursementEvent.getAmount()
            + " has been successfully completed. ");
            log.info("send notification");
        };
        try{
            mailSender.send(messagePreparator);
        }catch (Exception ex){
            log.error("Error sending notification", ex);
            throw new RuntimeException(ex);
        }
    }

}
