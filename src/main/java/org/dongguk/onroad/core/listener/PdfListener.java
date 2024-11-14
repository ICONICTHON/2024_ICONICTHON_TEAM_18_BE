package org.dongguk.onroad.core.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.onroad.core.constant.Constants;
import org.dongguk.onroad.roadmap.application.event.PdfEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class PdfListener {

    private final KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

    @Async
    @EventListener(classes = {PdfEvent.class})
    public void handlePdfEvent(PdfEvent event) {
        kafkaTemplate.send(Constants.SUMMARY_REQUEST_TOPIC, event.toPayload());
    }
}
