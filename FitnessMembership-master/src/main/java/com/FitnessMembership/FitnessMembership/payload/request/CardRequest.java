package com.FitnessMembership.FitnessMembership.payload.request;

import com.FitnessMembership.FitnessMembership.Entities.CardsAndServices.Services;
import com.FitnessMembership.FitnessMembership.Entities.Member;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class CardRequest implements Serializable{

    private String memberemail;

    public boolean isWoman() {
        return isWoman;
    }

    public void setWoman(boolean woman) {
        this.isWoman = woman;
    }

    private boolean isWoman;

    private int abonamentPeriod;

    private Set<Services> cardServices;

    public String getMemberemail() {
        return memberemail;
    }

    public void setMemberemail(String memberemail) {
        this.memberemail = memberemail;
    }

    public int getSubscriptionPeriod() {
        return abonamentPeriod;
    }

    public void setAbonamentPeriod(int abonamentPeriod) {
        this.abonamentPeriod = abonamentPeriod;
    }

    public Set<Services> getCardServices() {
        return cardServices;
    }

    public void setCardServices(Set<Services> cardServices) {
        this.cardServices = cardServices;
    }
}
