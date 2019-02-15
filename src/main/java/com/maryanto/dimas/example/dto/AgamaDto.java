package com.maryanto.dimas.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Timestamp;

public class AgamaDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AgamaNewRequest {
        @NotNull
        @NotEmpty
        @Size(min = 5, max = 50)
        private String name;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AgamaUpdateRequest {

        @NotNull
        private Integer id;
        @NotNull
        @NotEmpty
        @Size(min = 5, max = 50)
        private String name;
        private String description;
        @NotNull
        private Timestamp createdDate;
        @NotNull
        @NotEmpty
        private String createdBy;
        private Timestamp lastUpdateDate;
        private String lastUpdateBy;
    }
}
