package com.FitnessMembership.FitnessMembership.Repositories;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Card;
import com.FitnessMembership.FitnessMembership.Entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member , Long> {


    Member findMemberByEmail(String Email);

    Optional<Member> findMemberByFirstNameAndLastName(String firstName , String lastName);

    Member findMemberByCard(Card card);


}
