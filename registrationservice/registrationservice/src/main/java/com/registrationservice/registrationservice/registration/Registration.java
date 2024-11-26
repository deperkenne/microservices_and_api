package com.registrationservice.registrationservice.registration;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Document(collection = "registrations")
public record Registration(
                           @Id String id,
                           Integer productId,
                           String eventName,
                           BigDecimal price,
                           String ticketCode,
                           String attendeeName) {
}
