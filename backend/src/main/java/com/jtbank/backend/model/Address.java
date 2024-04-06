package com.jtbank.backend.model;

import com.jtbank.backend.model.helper.Auditing;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Address extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String addressId;
    String city;
    String state;
    String country;
    String zipcode;
    @OneToOne(mappedBy = "address")
    Account account;
}
