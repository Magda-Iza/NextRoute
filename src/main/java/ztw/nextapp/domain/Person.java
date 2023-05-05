package ztw.nextapp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Person extends BaseEntity {

    public Person(Long id, String name, String telephone, ApplicationUserRole userRole) {
        super(id);
        this.name = name;
        this.telephone = telephone;
        this.userRole = userRole;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "driver_license")
    private String driverLicense;

    @Column(name = "locked")
    private boolean locked;

    @Column(name = "role")
    private ApplicationUserRole userRole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Delivery> employees = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Delivery> drivers = new ArrayList<>();

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}