package demo.group1.library.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;
}
