package org.tvolkov.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Address> addresses;

    private String name;

    protected Customer(){}

    public Customer(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", addresses=" + addresses +
                '}';
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
