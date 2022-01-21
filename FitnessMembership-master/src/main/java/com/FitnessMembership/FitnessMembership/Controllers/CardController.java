package com.FitnessMembership.FitnessMembership.Controllers;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Card;
import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Services;
import com.FitnessMembership.FitnessMembership.Entities.Member;
import com.FitnessMembership.FitnessMembership.Repositories.CardsAndServices.CardRepository;
import com.FitnessMembership.FitnessMembership.Repositories.CardsAndServices.ServiceRepository;
import com.FitnessMembership.FitnessMembership.Repositories.MemberRepository;
import com.FitnessMembership.FitnessMembership.payload.request.CardRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/card")
public class CardController {

    private final CardRepository cardRepo;
    private final ServiceRepository serviceRepo;
    private final MemberRepository memberRepo;

    public CardController(CardRepository cardRepo, ServiceRepository serviceRepo, MemberRepository memberRepo) {
        this.cardRepo = cardRepo;
        this.serviceRepo = serviceRepo;
        this.memberRepo = memberRepo;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCard(Long CardId){
        Optional<Card> card = cardRepo.findById(CardId);
        if(card.isEmpty()){
            ResponseEntity.ok("Няма такава карта");
        }
        else
            cardRepo.deleteById(CardId);
        return ResponseEntity.ok("Card was deleted");
    }

    @GetMapping ( "/find")
    public ResponseEntity<?> getCard(Long id){
        Optional<Card> card = cardRepo.findById(id);
        if (card.isEmpty()){
            return ResponseEntity.ok("nqaa takava karta");
        }
    return ResponseEntity.ok(card.get());
    }

    @PostMapping( "/save" )
    public ResponseEntity<?> saveCard(@RequestBody CardRequest cardRequest)
    {

        if(!validateTimePeriod(cardRequest.getSubscriptionPeriod()))
        {
            return  ResponseEntity.ok("Tupi ti sa mesecite");
        }
                Set<Services> foundServices = new HashSet<>();
                Set<Services> inputtedServices = cardRequest.getCardServices();
                for (Services service: inputtedServices) {
                    if(serviceRepo.findServiceByServiceName(service.getServiceName()) == null ){
                       return ResponseEntity.ok("Mnogo uslugi iskash, malko predlagame");
                    } else
                    foundServices.add(serviceRepo.findServiceByServiceName(service.getServiceName()));
                }
                Card card = new Card(cardRequest.getSubscriptionPeriod(),
                        cardRequest.isWoman(), memberRepo.findMemberByEmail(cardRequest.getMemberemail()), foundServices);
                cardRepo.save(card);

                return ResponseEntity.ok("Shefche nova imash karta veche" );
        }
    /*    public boolean validateService(Services serviceDb, Services inputService ){

        if(serviceDb.getServiceName() == inputService.getServiceName()
                && serviceDb.getDescription() == inputService.getDescription())
        return true;
        else
            return false;
        }*/

    //delete

    //tutorial hibernate & spring

    public boolean validateTimePeriod(int subscriptionPeriod){
        if(subscriptionPeriod ==1 || subscriptionPeriod == 3 || subscriptionPeriod == 6 || subscriptionPeriod == 12 )
            return true;
        else
            return false;
    }
}
