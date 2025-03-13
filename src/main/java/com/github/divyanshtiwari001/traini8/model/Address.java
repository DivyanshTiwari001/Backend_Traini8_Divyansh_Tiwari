package com.github.divyanshtiwari001.traini8.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    private Long id;

    @NotBlank(message="the detailed address field is required and cannot be blank")
    private String detailedAddress;

    @NotBlank(message="city is required and cannot be blank")
    private String city;

    @NotBlank(message="state cannot be left blank")
    private String state;

    @NotBlank(message = "pincode cannot be left blank")
    @Size(min=4,max=12,message = "pincode can have a minimum length of 4 and maximim length of 12")
    private String pincode;

    public Address() {
    }
    
    public Address(
            @NotBlank(message = "the detailed address field is required and cannot be blank") String detailedAddress,
            @NotBlank(message = "city is required and cannot be blank") String city,
            @NotBlank(message = "state cannot be left blank") String state,
            @NotBlank(message = "pincode cannot be left blank") @Size(min = 4, max = 12, message = "pincode can have a minimum length of 4 and maximim length of 12") String pincode) {
        this.detailedAddress = detailedAddress;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    @PrePersist
    @PreUpdate
    private void prepareAddress() {
        this.detailedAddress = this.detailedAddress.trim().toLowerCase();
        this.city = this.city.trim().toLowerCase();
        this.state = this.state.trim().toLowerCase();
        this.pincode = this.pincode.trim().toLowerCase();
    }

    // getters and setters

    public String getDetailedAddress() {
        return detailedAddress;
    }
    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((detailedAddress == null) ? 0 : detailedAddress.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((pincode == null) ? 0 : pincode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (detailedAddress == null) {
            if (other.detailedAddress != null)
                return false;
        } else if (!detailedAddress.equals(other.detailedAddress))
            return false;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (pincode == null) {
            if (other.pincode != null)
                return false;
        } else if (!pincode.equals(other.pincode))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "Address [detailedAddress=" + detailedAddress + ", city=" + city + ", state=" + state + ", pincode="
                + pincode + "]";
    }
}
