package com.example.hotel.Util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;// import cua thang domain
import org.springframework.data.domain.Sort;

public class PaginationUtil {
    public static Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDirection) ? Sort.Direction.DESC : Sort.Direction.ASC; // enum
        return PageRequest.of(page, size, Sort.by(direction, sortBy));
    }

    public static Pageable of(int page, int size) {
        return PageRequest.of(page, size);
    }

    public static Pageable of(int page, int size, String sortBy) {
        return PageRequest.of(page, size, Sort.by(sortBy));
    }

    public static boolean isValidPageNumber(int page) {
        return page >= 0;
    }

    public static boolean isValidPageSize(int size) {
        return size > 0 && size <= 100;
    }

    public static boolean isValidDirection(String sortDirection) {
        return "asc".equalsIgnoreCase(sortDirection) || "desc".equalsIgnoreCase(sortDirection);
    }

}
