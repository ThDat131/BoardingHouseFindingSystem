package com.md.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.md.pojo.Image;
import com.md.repository.ImageRepository;
import com.md.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ImageRepository imageRepository;
    @Override
    public boolean addImage(Image image) {
        if (!image.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(image.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                image.setUrl(res.get("secure_url").toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return this.imageRepository.addImage(image);
    }
}
