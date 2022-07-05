package com.darisamor.logosShop.service.impl;

import com.darisamor.logosShop.domain.ImageDTO;
import com.darisamor.logosShop.entity.Image;
import com.darisamor.logosShop.repository.ImageRepository;
import com.darisamor.logosShop.service.ImageService;
import com.darisamor.logosShop.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ModelMapperUtil mapper;


    @Override
    public ImageDTO create(ImageDTO dto) {
        Image savedImage = repository.save(mapper.map(dto, Image.class));
        return mapper.map(savedImage, ImageDTO.class);
    }

    @Override
    public ImageDTO update(ImageDTO dto) {
        Image updatedImage = repository.save(mapper.map(dto, Image.class));
        return mapper.map(updatedImage, ImageDTO.class);
    }

    @Override
    public ImageDTO delete(ImageDTO dto) {
        repository.delete(mapper.map(dto, Image.class));
        return dto;
    }

    @Override
    public String deleteById(String id) {
        repository.deleteById(id);
        return id;
    }

    @Override
    public ImageDTO getEntity(String id) {
        return mapper.map(repository.getById(id), ImageDTO.class);
    }

    @Override
    public List<ImageDTO> getAll() {
        return mapper.mapAll(repository.findAll(), ImageDTO.class);
    }
}