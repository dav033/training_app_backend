package com.mpx90.training_app.services.base;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mpx90.training_app.mappers.GenericMapper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService<T, ID, E, R extends JpaRepository<E, ID>> implements CrudService<T, ID> {
    protected final R repository;
    protected final GenericMapper<T, E> mapper;

    public BaseService(R repository, GenericMapper<T, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public T create(T dto) {
        E entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    @Override
    public List<T> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElse(null);
    }

    @Override
    public T update(ID id, T dto) {
        E entity = repository.findById(id).orElseThrow();
        mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }
}
