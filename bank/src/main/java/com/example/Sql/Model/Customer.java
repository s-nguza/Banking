package com.example.Sql.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private Integer age;

    @Email
    private String email;

    @NotNull
    private String password;

    // Relationship with Accounts
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;

    // Constructors, getters, and setters
    public String getName(){
        return name;
    }
    public void  setName(String name){
        this.name = name;
    }
    
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname  = surname;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getpassword(){
        return password;
    }
    public void setPasssword(String password){
        this.password= password;
    }

}
