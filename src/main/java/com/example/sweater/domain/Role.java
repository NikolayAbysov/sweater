package com.example.sweater.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN; //Значение енама являются имплементацией класса

    @Override
    public String getAuthority() {
        return name(); //Строковое представление значения енама
    }
}