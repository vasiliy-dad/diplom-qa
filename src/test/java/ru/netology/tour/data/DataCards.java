package ru.netology.tour.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCards {
    private String number;
    private String month;
    private String year;
    private String owner;
    private String cvc;
}
