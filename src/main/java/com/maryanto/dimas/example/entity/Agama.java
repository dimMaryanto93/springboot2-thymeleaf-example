package com.maryanto.dimas.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agama {

    private Integer id;
    private String name;
    private String description;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdateDate;
    private String lastUpdateBy;
}
