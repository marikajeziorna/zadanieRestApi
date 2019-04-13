package pl.javastart.restoffers.offers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(" SELECT offer FROM Offer offer" +
            " where  1 = 1 " +
            " and (:title is null  or lower(offer.title) like concat('%', :title, '%'))")
    List<Offer> findByTitle(@Param("title") String title);
}
