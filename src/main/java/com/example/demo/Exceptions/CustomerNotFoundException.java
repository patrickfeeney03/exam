package com.example.demo.Exceptions;

public class CustomerNotFoundException extends Throwable {
    private Long id;
    public CustomerNotFoundException(Long id) {
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
