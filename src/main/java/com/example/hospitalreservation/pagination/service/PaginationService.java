package com.example.hospitalreservation.pagination.service;

import java.util.List;

public interface PaginationService {

    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages);

    public int currentBarLength();
}
