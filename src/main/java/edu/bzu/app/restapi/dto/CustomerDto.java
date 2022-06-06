package edu.bzu.app.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
//The dto is created to deliver the data to the user or take data from the user
public class CustomerDto {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate bornAt;

}
