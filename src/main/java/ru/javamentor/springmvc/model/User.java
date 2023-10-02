package ru.javamentor.springmvc.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotEmpty(message = "Name should be between 2 and 25 latin characters")
    @Size(min = 2, max = 25)
    private String username;

    @Column(name = "age")
    @Min(value = 0, message = "Age should be >= 0")
    @Max(value = 127, message = "Age should be < 128")
    private Short age;

    @Column(name = "password")
    @Size(min = 3, max = 225, message = "Password should be between 3 and 50 characters")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> role;

    public User() {

    }

    public User(String name, Short age, String password, Collection<Role> role) {
        this.username = name;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRole() {
        return role;
    }

    public void setRole(Collection<Role> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("User {id = '%s', name = '%s', age = '%d', password = '%s', roles = '%s'}",
                getId(), getName(), getAge(), getPassword(), getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;

        hash = 31 * hash + (username == null ? 0 : username.hashCode());
        hash = 31 * hash + (age == null ? 0 : age.hashCode());

        return hash;
    }
}
