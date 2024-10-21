package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.After;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    private Long customerId;
    @NotEmpty @Length(min = 2)
    private String firstName;
    @NotEmpty @Length(min = 2)
    private String lastName;
    @Email
    private String email;
    @Digits(integer = 10, fraction =  0)
    private String phoneNumber;
    //    @Pattern(regexp = )
    private String dateOfBirth;
    @Pattern(regexp = "^[A-Z][0-9]{6}") // any letter followed by 6 ints
    private String eircode;


}

