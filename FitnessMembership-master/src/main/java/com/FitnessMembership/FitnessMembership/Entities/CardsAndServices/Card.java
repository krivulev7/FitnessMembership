package com.FitnessMembership.FitnessMembership.Entities.CardsAndServices;

import com.FitnessMembership.FitnessMembership.Entities.Employees.Employee;
import com.FitnessMembership.FitnessMembership.Entities.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity

public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private int abonamentPeriod;

    private int Price;

    private boolean gender;

    private boolean valid;

    private Timestamp cardCharged;

    private LocalDate expirationDate;

    @OneToOne(mappedBy = "card")
    @JsonIgnore
    private Member member;

    @ManyToMany
    @JoinTable(
            name = "card_services",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Services> service;

    public Card() {
    }

    //Нова карта ще създаваме само с абонаментен период и видове услуги , другите полета си ги призчисляваме
    // и са си за нас
    public Card(int abonamentPeriod , boolean gender, Member member, Set<Services> service) {
        this.member = member;
        this.service = service;
        if(gender)
        {
            Price += 40;
            //Wemen are allways right
        }
        else if(!gender)
        {
            Price += 60;
        }
     //   this.CardServices = cardServices;
        Price += 10*service.size();
        //При създаване абонамента е задължителен , тъй че по дефоут е валидна
        this.valid = true;

        this.cardCharged = new Timestamp(System.currentTimeMillis());

        //Изчисляваме до кога е валидна
        this.expirationDate = LocalDate.now().plusMonths(abonamentPeriod);
        this.abonamentPeriod = abonamentPeriod;

        //this.visitsCounter = visitsCounter;
    }



    public Long getId() {
        return Id;
    }

    public Set<Services> getService() {
        return service;
    }

    public void setService(Set<Services> service) {
        this.service = service;
    }

    public int getAbonamentPeriod() {
        return abonamentPeriod;
    }

    public void setAbonamentPeriod(int abonamentPeriod) {
        this.abonamentPeriod = abonamentPeriod;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Timestamp getCardCharged() {
        return cardCharged;
    }

    public void setCardCharged(Timestamp cardCharged) {
        this.cardCharged = cardCharged;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
