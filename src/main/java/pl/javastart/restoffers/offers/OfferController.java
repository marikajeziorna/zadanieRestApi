package pl.javastart.restoffers.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.categories.Category;
import pl.javastart.restoffers.categories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OfferController {

    private OfferRepository offerRepository;
    private CategoryRepository categoryRepository;

    public OfferController() {
    }

    @Autowired
    public OfferController(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/api/offers/count")
    public int getOfferNumbers() {
        final int offerNumber = (int) offerRepository.count();
        return offerNumber;
    }

    @GetMapping("/api/offers")
    public List<OfferDto> searchOffer(@RequestParam(required = false) String title) {
        List<OfferDto> offerDtoList = new ArrayList<>();
        List<Offer> offers;

        if (title == null || title.equals("")) {
            offers = offerRepository.findAll();
        } else {
            offers = offerRepository.findByTitle(title.toLowerCase());
        }

        for (Offer offer : offers) {
            OfferDto offerDto = new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
            offerDtoList.add(offerDto);
        }
        return offerDtoList;
    }

    @PostMapping("/api/offers")
    public void addOffer(@RequestBody OfferDto offerDto) {
        Category category = categoryRepository.findByName(offerDto.getCategory());

        Offer offer = new Offer(category, offerDto.getTitle(), offerDto.getDescription(), offerDto.getImgUrl(), offerDto.getPrice());
        offerRepository.save(offer);
    }

    @GetMapping("/api/offers/{id}")
    public ResponseEntity<OfferDto> getOfferBy(@PathVariable Long id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
            OfferDto offerDto = new OfferDto(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
            return ResponseEntity.ok(offerDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/offers/{id}")
    public void deleteOffer(@PathVariable Long id) {
        Optional<Offer> offerRepositoryById = offerRepository.findById(id);
        if(offerRepositoryById.isPresent()){
            offerRepository.delete(offerRepositoryById.get());
        }
    }
}
