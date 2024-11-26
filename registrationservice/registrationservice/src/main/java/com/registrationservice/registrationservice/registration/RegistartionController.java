package com.registrationservice.registrationservice.registration;

import com.registrationservice.registrationservice.events.Event;
import com.registrationservice.registrationservice.events.Product;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
//@RequestMapping(path = "/registrations")
public class RegistartionController {

    private final RegistrationRepository registrationRepository;
    private final WebClient webClient;


    public RegistartionController(RegistrationRepository registrationRepository,WebClient webClient) {
        this.registrationRepository = registrationRepository;
        this.webClient = webClient;
    }

    @PostMapping(path = "/registrations")
    public Registration create(@RequestBody @Valid Registration registration) {
        Product product = productWeb(registration);
        Event event = eventWeb(product);
        String ticketCode = UUID.randomUUID().toString();

        return registrationRepository.save(new Registration(
                null, product.id(),event.name(),product.price(), ticketCode, registration.attendeeName()));
    }


    @GetMapping(path = "/registrations")
    public List<Registration> getAllRegistration(){
        return registrationRepository.findAll();

    }



    @GetMapping(path = "/{ticketCode}")
    public Registration getT(@PathVariable("ticketCode") String ticketCode) {
        return registrationRepository.findByTicketCode(ticketCode)
                .orElseThrow(() -> new NoSuchElementException("Registration with ticket code " + ticketCode + " not found"));
    }


    @GetMapping(path = "/{productId}")
    public ResponseEntity<List<Registration>> getP(@PathVariable("productId") Integer productId)  {
        List<Registration> currentlist = registrationRepository.findByProductId(productId);
        if(currentlist.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(currentlist);

    }


    public Event eventWeb(Product product){
        return webClient.get()
                .uri("events/{id}",product.eventId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Event.class)
                .block();

    }

    public Product productWeb(Registration registration){
        return webClient.get()
                .uri("products/{id}",registration.productId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

    }


}
