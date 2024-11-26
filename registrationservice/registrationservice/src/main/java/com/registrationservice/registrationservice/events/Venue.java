package com.registrationservice.registrationservice.events;

public record Venue(Integer id,
                    String name,
                    String street,
                    String city,
                    String country) {
}
