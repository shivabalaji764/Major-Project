package com.project.restaurantpreorder.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.restaurantpreorder.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@SuppressWarnings("JpaDataSourceORMInspection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50, message = "Name must contain 2-50 characters only")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email Format")
    @Size(max = 100, message = "Email Cannot Exceed 100 Characters")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Mobile is Required")
    @Pattern(
            regexp = "^(?:(?:\\+91|0)?[ -]?)?[6-9]\\d{9}$",
            message = "Invalid Mobile Number"
    )
    @Column(name = "mobile", nullable = false, length = 13)
    private String mobile;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;



    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
