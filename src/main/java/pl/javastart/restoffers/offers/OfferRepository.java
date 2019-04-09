package pl.javastart.restoffers.offers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    public List<Offer> findByTitleInIgnoreCase(String title);

}
