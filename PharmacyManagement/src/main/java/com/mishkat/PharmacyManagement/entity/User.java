package com.mishkat.PharmacyManagement.entity;

import com.mishkat.PharmacyManagement.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;

@Entity // Defines this class as a database entity
@Data // Auto-generates standard boilerplate methods (Getters/Setters) via Lombok
@Table(name = "users") // Maps this entity to the 'users' data table
public class User {
    @Id // Declares the database Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures identity column auto-incrementation
    private Long id; // Unique database record index for the user

    @Column(unique = true, nullable = false) // Enforces that no two users share a login username
    private String username; // Unique login handle for the user account

    @Column(nullable = false) // Enforces mandatory security field
    private String password; // Encrypted hashing string of the user password

    private String fullName; // Display name of the employee

    @Enumerated(EnumType.STRING) // Saves enum as a readable text string in the DB (e.g. "PHARMACIST") instead of an integer position index (3)
    @Column(nullable = false) // Ensures roles are always specified
    private UserRole role; // Access control permissions group assigned to the user

    @ManyToOne // Multiple users can work at the exact same physical branch (Many-To-One)
    @JoinColumn(name = "branch_id") // Creates a Foreign Key column named 'branch_id' pointing to the 'branches' table. Nullable to allow global managers who aren't bound to one branch.
    private Branch branch; // The operational branch site this user is assigned to

    private boolean active = true; // Controls login permission capability status
}
