package com.FitnessMembership.FitnessMembership.Entities.CardsAndServices;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    private String serviceName;

    private String description;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

       /* @ManyToMany(mappedBy = "CardServices")
        private List<Card> ServicedCards;

        public Long getId() {
            return Id;
        }

*/
    public Long getId() {
        return Id;
    }


    public Services() {
    }



    public Services(String serviceName, String description) {
        this.serviceName = serviceName;
        this.description = description;
    }

}
