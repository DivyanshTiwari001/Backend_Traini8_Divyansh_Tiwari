package com.github.divyanshtiwari001.traini8.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.CascadeType;


@Entity
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "center_seq")
    @SequenceGenerator(name = "center_seq", sequenceName = "center_seq", allocationSize = 1)
    private Long id;


    public Center() {
        this.coursesOffered = new ArrayList<>();
    }
    

    public Center(
            @NotBlank(message = "center name cannot be left blank") 
            @Size(max = 40, message = "center name must be less than 40 characters") String centerName,
            @NotBlank(message = "center code cannot be left blank") 
            @Size(min = 12, max = 12, message = "center code must be exactly 12 characters long") 
            @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "center code must contain only letters and numbers.") String centerCode,
            @NotNull(message = "center address cannot be empty") Address centerAddress, 
            Long studentCapacity,
            List<String> coursesOffered, @Email(message = "center email is not valid") String centerEmail,
            @NotBlank(message = "center phone number cannot be left blank") 
            @Pattern(regexp = "^[0-9]{10}$", message = "center phone number must be exactly 10 digits long") String centerPhone) {
        this.centerName = centerName;
        this.centerCode = centerCode;
        this.centerAddress = centerAddress;
        this.studentCapacity = (studentCapacity != null) ? studentCapacity : 0L; ;
        this.coursesOffered = (coursesOffered !=null)? coursesOffered : new ArrayList<>();
        this.centerEmail = (centerEmail!=null)? centerEmail : "";
        this.centerPhone = centerPhone;
    }
    


    @NotBlank(message = "center name cannot be left blank")
    @Size(max=40,message = "center name must be less than 40 characters")
    private String centerName;

    @NotBlank(message = "center code cannot be left blank")
    @Size(min=12,max=12,message = "center code must be exactly 12 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "center code must contain only letters and numbers.")
    private String centerCode;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "center address cannot be empty")
    @Valid
    private Address centerAddress;

    private Long studentCapacity;
    private List<String> coursesOffered;
    
    @Column(nullable = false, updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdOn;

    @PrePersist
    @PreUpdate
    protected void onCreate() {
         this.createdOn = Timestamp.from(Instant.now()); // Store epoch time in milliseconds
         this.centerName = this.centerName.toLowerCase();
         this.centerEmail = this.centerEmail!=null?this.centerEmail.toLowerCase():"";
         if(this.coursesOffered!=null){
             for(int i=0;i<this.coursesOffered.size();i++){
                 this.coursesOffered.set(i, this.coursesOffered.get(i).toLowerCase());
             }
         }

    }

    @Email(message = "center email is not valid")
    private String centerEmail;

    @NotBlank(message = "center phone number cannot be left blank")
    @Pattern(regexp = "^[0-9]{10}$", message = "center phone number must be exactly 10 digits long")
    private String centerPhone;


    // getters and setters

    public Long getId() {
        return id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public Address getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(Address centerAddress) {
        this.centerAddress = centerAddress;
    }

    public Long getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(Long studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public List<String> getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(List<String> coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public void addCourse(String ...courses) {
        for(String course : courses){
            this.coursesOffered.add(course);
        }
    }

    public String getCenterEmail() {
        return centerEmail;
    }

    public void setCenterEmail(String centerEmail) {
        this.centerEmail = centerEmail;
    }

    public String getCenterPhone() {
        return centerPhone;
    }

    public void setCenterPhone(String centerPhone) {
        this.centerPhone = centerPhone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((centerName == null) ? 0 : centerName.hashCode());
        result = prime * result + ((centerCode == null) ? 0 : centerCode.hashCode());
        result = prime * result + ((centerAddress == null) ? 0 : centerAddress.hashCode());
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
        Center other = (Center) obj;
        if (centerName == null) {
            if (other.centerName != null)
                return false;
        } else if (!centerName.equals(other.centerName))
            return false;
        if (centerCode == null) {
            if (other.centerCode != null)
                return false;
        } else if (!centerCode.equals(other.centerCode))
            return false;
        if (centerAddress == null) {
            if (other.centerAddress != null)
                return false;
        } else if (!centerAddress.equals(other.centerAddress))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Center [centerName=" + centerName + ", centerCode=" + centerCode + ", centerAddress=" + centerAddress
                + ", studentCapacity=" + studentCapacity + ", coursesOffered=" + coursesOffered
                + ", centerEmail=" + centerEmail + ", centerPhone=" + centerPhone + ", createdOn="
                + createdOn + "]";
    }
}
