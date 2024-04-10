package org.example.service;

import java.util.List;

public interface Service<T, Integer> {
    void add(String[] parameters);
    void remove(int id);
    void removeAll();
    void update(String[] parameters);
    T getById(int id);
    List<T> getAll();
}
