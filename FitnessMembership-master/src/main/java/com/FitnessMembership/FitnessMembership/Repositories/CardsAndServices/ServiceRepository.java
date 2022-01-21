package com.FitnessMembership.FitnessMembership.Repositories.CardsAndServices;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceRepository extends JpaRepository<Services, Long> {

    Services findServiceByServiceName(String Name);

    Set<Services> findAllByServiceName (String Name);
}
