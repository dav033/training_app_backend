package com.mpx90.training_app.mappers;

import java.util.List;

public interface GenericMapper<T, E> {
    E toEntity(T dto);
    T toDto(E entity);
    List<T> toDtoList(List<E> entityList);
}
