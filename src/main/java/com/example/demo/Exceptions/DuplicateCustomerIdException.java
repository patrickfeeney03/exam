package com.example.demo.Exceptions;

public class DuplicateCustomerIdException extends Throwable{

    private Long id;

    public DuplicateCustomerIdException(Long id) {
        super();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
