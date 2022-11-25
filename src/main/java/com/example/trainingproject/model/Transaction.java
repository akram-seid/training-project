package com.example.trainingproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Transaction type must be specified!")
    private Type type;
    @NotBlank(message = "Amount must be specified!")
    private Double amount;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "account_id",
            referencedColumnName = "id")
    private Account account;

}
