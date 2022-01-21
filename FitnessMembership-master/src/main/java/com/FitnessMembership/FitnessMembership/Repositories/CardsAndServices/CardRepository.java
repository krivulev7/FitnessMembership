package com.FitnessMembership.FitnessMembership.Repositories.CardsAndServices;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Card;
import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByService(Services service);

}
