package com.registrationservice.registrationservice.events;

import java.math.BigDecimal;

public record Product(Integer id,
                      Integer eventId,
                      String name,
                      String description,
                      BigDecimal price) {
}
