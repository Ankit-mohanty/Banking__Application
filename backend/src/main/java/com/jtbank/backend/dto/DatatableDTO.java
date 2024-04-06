package com.jtbank.backend.dto;

import java.util.List;

public record DatatableDTO(
        long totalRecord,
        int pageNumber,
        int pageSize,
        List<TransactionDTO> transactions
) {
}
