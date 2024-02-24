package demo.group1.library.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "borrowed_books")
@Getter
@Setter
public class borrowedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long borrowId;
    private Date borrowedDate;
    private Date returnDate;

    private Long copyId;
}
