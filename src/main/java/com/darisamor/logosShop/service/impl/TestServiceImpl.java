package com.darisamor.logosShop.service.impl;

import com.darisamor.logosShop.domain.TestDTO;
import com.darisamor.logosShop.entity.TestEntity;
import com.darisamor.logosShop.repository.TestRepository;
import com.darisamor.logosShop.service.TestService;
import com.darisamor.logosShop.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository repository;

    @Autowired
    private ModelMapperUtil util;

    @Override
    @Transactional
    public TestDTO create(TestDTO dto) {
        TestEntity entityToSave = util.map(dto, TestEntity.class);
        TestEntity createdEntity = repository.save(entityToSave);
        return util.map(createdEntity, TestDTO.class);
    }

    @Override
    @Transactional
    public TestDTO update(TestDTO dto) {
        TestEntity entityToUpdate = util.map(dto, TestEntity.class);
        TestEntity updatedEntity = repository.save(entityToUpdate);
        return util.map(updatedEntity, TestDTO.class);
    }

    @Override
    @Transactional
    public TestDTO delete(TestDTO dto) {
        TestDTO deleted = getEntity(dto.getId());
        TestEntity entityToDelete = util.map(deleted, TestEntity.class);
        repository.delete(entityToDelete);
        return deleted;
    }

    @Override
    @Transactional
    public TestDTO deleteById(Long id) {
        TestDTO deleted = getEntity(id);
        TestEntity entityToDelete = util.map(deleted, TestEntity.class);
        repository.delete(entityToDelete);
        return deleted ;
    }

    @Override
    public TestDTO getEntity(Long id) {
        return util.map(repository.getById(id), TestDTO.class);
    }

    @Override
    public List<TestDTO> getAll() {
        return util.mapAll(repository.findAll(), TestDTO.class);
    }

    @Override
    public TestDTO findByName(String name) {
        TestEntity entity = repository.findByName(name);
        return util.map(entity, TestDTO.class);
    }
}