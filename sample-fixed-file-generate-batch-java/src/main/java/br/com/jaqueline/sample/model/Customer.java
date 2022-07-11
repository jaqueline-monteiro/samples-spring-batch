package br.com.jaqueline.sample.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Customer implements Serializable {
    private String name;
    private String lastName;
    private String age;
    private String email;
    private String telephone;
    private String address;
}
