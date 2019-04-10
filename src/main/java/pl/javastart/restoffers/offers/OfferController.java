package pl.javastart.restoffers.offers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.categories.Category;

import java.util.List;
import java.util.Optional;

@RestController
public class OfferController {

    private OfferRepository offerRepository;

    public OfferController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping("/api/offers/count")
    public Long getOfferNumbers() {
        return offerRepository.count();
    }

    @GetMapping("/api/offers")
    public List<Offer> searchOffer(@RequestParam(required = false) String title) {
        if (title == null || title == "") {
            return offerRepository.findAll();
        } else {
            return offerRepository.findByTitle(title);
        }
    }

    @PostMapping("/api/offers")
    @ResponseBody
    public ResponseEntity<Offer> addOffer(@RequestParam Offer offer, @RequestParam Category category) {
        if (offer.getId() != null) {
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
            bodyBuilder.build();
        }
        offer.setCategory(category);
        Offer save = offerRepository.save(offer);

//        Offer save = null;
//        save.setCategory(category);
//        save.setCategory(category);
//        Offer save = offerRepository
//                .save(offer);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/api/offers/{id}")
    public ResponseEntity<Offer> getOfferBy(@PathVariable Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            return ResponseEntity.ok(offer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/offers/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerRepository.deleteById(id);
    }
}
