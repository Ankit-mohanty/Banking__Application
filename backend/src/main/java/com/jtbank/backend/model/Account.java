package com.jtbank.backend.model;

import com.jtbank.backend.constant.AccountType;
import com.jtbank.backend.model.helper.Auditing;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account_table")
public class Account extends Auditing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountSlNo;
    @Column(unique = true, nullable = false)
    private long accountNumber;
    @Column(name = "account_name", nullable = false)
    private String accountHolderName;
    @Column(nullable = false)
    private String contactNumber;
    private String profilePicture;
    @Lob
    private String aboutCustomer;
    private double accountBalance;
    @Embedded
    private Credential credential;
    @Enumerated(value = EnumType.STRING)
    private AccountType accountType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
