package com.darisamor.logosShop.service.impl;

import com.darisamor.logosShop.domain.ImageDTO;
import com.darisamor.logosShop.entity.Image;
import com.darisamor.logosShop.exception.AlreadyExistsException;
import com.darisamor.logosShop.exception.NotFoundException;
import com.darisamor.logosShop.repository.ImageRepository;
import com.darisamor.logosShop.service.ImageService;
import com.darisamor.logosShop.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository repository;

    @Autowired
    private ModelMapperUtil mapper;


    @Override
    public ImageDTO create(ImageDTO dto) {
        if(!Objects.isNull(dto.getId()) && repository.existsById(dto.getId()))
            throw new AlreadyExistsException("Image", "id", dto.getId());
        Image savedImage = repository.save(mapper.map(dto, Image.class));
        return map(savedImage);
    }

    @Override
    public ImageDTO update(ImageDTO dto) {
        if(Objects.isNull(dto.getId()) && repository.existsById(dto.getId()))
            throw new NotFoundException("Image", "id", dto.getId());
        Image updatedImage = repository.save(mapper.map(dto, Image.class));
        return map(updatedImage);
    }

    @Override
    public ImageDTO delete(ImageDTO dto) {
        repository.delete(map(dto));
        return dto;
    }

    @Override
    public String deleteById(String id) {
        if(!repository.existsById(id)) throw new NotFoundException("Image", "id", id);
        repository.deleteById(id);
        return id;
    }

    @Override
    public ImageDTO getEntity(String id) {
        Image image = repository.findById(id).orElseThrow(() -> new NotFoundException("Image", "id", id));
        return map(image);
    }

    @Override
    public List<ImageDTO> getAll() {
        return mapper.mapAll(repository.findAll(), ImageDTO.class);
    }

    private Image map(ImageDTO dto){
        return mapper.map(dto, Image.class);
    }

    private ImageDTO map(Image entity){
        return mapper.map(entity, ImageDTO.class);
    }
}