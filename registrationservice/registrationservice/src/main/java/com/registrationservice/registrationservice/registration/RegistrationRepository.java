package com.registrationservice.registrationservice.registration;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RegistrationRepository extends MongoRepository<Registration,String> {
    Optional<Registration> findByTicketCode(String ticketCode);
    List<Registration> findByProductId(int productId);
    void deleteByTicketCode(String ticketCode);

}
