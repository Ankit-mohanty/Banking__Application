package com.jtbank.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jtbank.backend.constant.TransactionMode;

import java.time.LocalDateTime;

public record TransactionDTO(
        @JsonFormat(pattern = "dd MMM yyyy | hh:mm:ss a")
        LocalDateTime timestamp,
        TransactionMode mode,
        double balance
) {
}
