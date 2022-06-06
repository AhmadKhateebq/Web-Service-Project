package edu.bzu.app.restapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data//to create common functions, getters and setters
@AllArgsConstructor
@NoArgsConstructor
@Entity//to mark this class as an entity
@Table(name = "customer")//to mark this as a table in the database with name customer

public class Customer {
    @Id   //declare the primary key
    @GeneratedValue(
            strategy = GenerationType.IDENTITY//auto increment
    )
    private Long id;
    @Column(name = "firstname", nullable = false)//this is a column in the table that's not nullable
    private String firstname;
    @Column(name = "lastname", nullable = false)//this is a column in the table that's not nullable
    private String lastname;
    @Column(name = "born_at", nullable = false)//this is a column in the table that's not nullable
    @JsonFormat(pattern="yyyy-MM-dd")//this is the json format of that date type
    //the name is bornAt while in database is born_at duo to db use snake case, while java uses camel case
    private LocalDate bornAt;

}
