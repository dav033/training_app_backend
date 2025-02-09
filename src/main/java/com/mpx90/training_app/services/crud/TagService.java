package com.mpx90.training_app.services.crud;

import com.mpx90.training_app.dto.core.Tag;
import com.mpx90.training_app.mappers.TagMapper;
import com.mpx90.training_app.models.training.TagEntity;
import com.mpx90.training_app.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    public TagService(TagRepository tagRepository, TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    public Tag create(Tag tag) {
        TagEntity entity = tagMapper.toEntity(tag);
        return tagMapper.toDto(tagRepository.save(entity));
    }

    public List<Tag> findAll() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toList());
    }

    public Tag findById(Long id) {
        return tagRepository.findById(id)
                .map(tagMapper::toDto)
                .orElse(null);
    }

    public Tag update(Long id, Tag tag) {
        return tagRepository.findById(id)
                .map(existing -> {
                    TagEntity updatedEntity = tagMapper.toEntity(tag);
                    updatedEntity.setId(existing.getId());
                    return tagMapper.toDto(tagRepository.save(updatedEntity));
                })
                .orElse(null);
    }

    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
