package com.registrationservice.registrationservice.events;

import java.time.LocalDate;

public record Event(Integer id,
                    String name,
                    Organizer organizer,
                    Venue venue,
                    LocalDate startDate,
                    LocalDate endDate) {
}
