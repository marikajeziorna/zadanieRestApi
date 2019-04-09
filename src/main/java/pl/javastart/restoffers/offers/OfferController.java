package pl.javastart.restoffers.offers;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.categories.Category;
import pl.javastart.restoffers.categories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class OfferController {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    public OfferController(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/offers")
    public List<Offer> getAllOffers(){
        return offerRepository.findAll();
    }

    @GetMapping("/api/offers/count")
    public Long getOfferNumbers(){
        return offerRepository.count();
    }

//    TODO search
//    @GetMapping("/api/offers" )
//    public List<Offer> searchOffer(@RequestParam (required = false) String title) {
//        String newString = title.toLowerCase();
//        if (newString != null) {
//            return offerRepository.findByTitleInIgnoreCase(title);
//        } else {
//            return offerRepository.findAll();
//        }
//    }

    @PostMapping("/api/offers")
    @ResponseBody
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer){
        if (offer.getId() !=null){
            ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.badRequest();
            bodyBuilder.build();
        }
        Offer save = offerRepository.save(offer);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/api/offers/{id}")
    public ResponseEntity<Offer> getOfferBy(@PathVariable Long id){
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if(optionalOffer.isPresent()){
            Offer offer = optionalOffer.get();
            return ResponseEntity.ok(offer);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/offers/{id}")
    public void deleteOffer(@PathVariable Long id){
        offerRepository.deleteById(id);
    }
}
