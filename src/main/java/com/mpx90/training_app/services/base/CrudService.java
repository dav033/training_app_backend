package com.mpx90.training_app.services.base;

import com.mpx90.training_app.dto.core.Round;

import java.util.List;

public interface CrudService<T, ID> {
    T create(T dto);

    List<T> findAll();

    T findById(ID id);

    T update(ID id, T dto);

    void delete(ID id);

    List<Round> saveAll(List<T> dtos);
}
