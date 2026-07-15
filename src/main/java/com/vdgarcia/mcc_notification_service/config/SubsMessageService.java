package com.vdgarcia.mcc_notification_service.config;

import com.azure.messaging.servicebus.ServiceBusErrorContext;
import com.azure.messaging.servicebus.ServiceBusReceivedMessage;
import com.azure.messaging.servicebus.ServiceBusReceivedMessageContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vdgarcia.mcc_notification_service.event.CreditDisbursementEvent;
import com.vdgarcia.mcc_notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubsMessageService {

    private final ObjectMapper objectMapper;
    private final NotificationService service;

    public void processMessage(ServiceBusReceivedMessageContext messageContext){
        ServiceBusReceivedMessage message = messageContext.getMessage();
        try{
            String payload = message.getBody().toString();
            CreditDisbursementEvent event = objectMapper.readValue(payload,CreditDisbursementEvent.class);
            log.info("Processing event: {}", event);
            this.service.sendNotification(event);
        } catch (Exception ex) {
            log.error("Error processing event", ex);
        }


    }
    public void processError(ServiceBusErrorContext context){
        log.error("Error processing event");
    }




}
