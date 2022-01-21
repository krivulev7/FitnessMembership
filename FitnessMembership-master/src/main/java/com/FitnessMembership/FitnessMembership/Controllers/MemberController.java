package com.FitnessMembership.FitnessMembership.Controllers;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Card;
import com.FitnessMembership.FitnessMembership.Entities.Member;
import com.FitnessMembership.FitnessMembership.Repositories.CardsAndServices.CardRepository;
import com.FitnessMembership.FitnessMembership.Repositories.MemberRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/member")
public class MemberController {

    private final MemberRepository memberRepo;
    private final CardRepository cardRepo;

    public MemberController(MemberRepository memberRepo, CardRepository cardRepo) {
        this.memberRepo = memberRepo;
        this.cardRepo = cardRepo;
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> GetAllMembers() {
        if (memberRepo.findAll().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(memberRepo.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<?> findMember(String firstName, String lastName) {
        Optional<Member> customer = memberRepo.findMemberByFirstNameAndLastName(firstName, lastName);
        if (customer.isEmpty()) {
            return ResponseEntity.ok("No customer found!");
        }
        return ResponseEntity.ok(customer.get());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMember(Long memberId) {
        //Member member = memberRepo.getById(memberId);
        //da se dopulni s dependancy delete
        memberRepo.deleteById(memberId);
        return ResponseEntity.ok("member was deleted");
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveMember(String firstName, String lastName
            , String email, String address, String phoneNumber, String username, String password, Long CardId) {

        Card card = cardRepo.findById(CardId).get();
        Member existedMember = memberRepo.findMemberByCard(card);


        if (existedMember == null) {
            return ResponseEntity.ok(memberRepo.save(new Member(firstName, lastName, email, address, username, password, phoneNumber, card)));
        }
        else {
            existedMember.setFirstName(firstName);
            existedMember.setLastName(lastName);
            existedMember.setEmail(email);
            existedMember.setAddress(address);
            existedMember.setUsername(username);
            existedMember.setPassword(password);
            existedMember.setPhoneNumber(phoneNumber);
            memberRepo.save(existedMember);
            return ResponseEntity.ok(existedMember);
        }
    }
}

