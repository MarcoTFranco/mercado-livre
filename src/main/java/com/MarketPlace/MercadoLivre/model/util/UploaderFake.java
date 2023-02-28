package com.MarketPlace.MercadoLivre.model.util;

import com.MarketPlace.MercadoLivre.model.util.Uploader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UploaderFake implements Uploader {
    public Set<String> upload(List<MultipartFile> images) {
        return images.stream()
                .map(image -> "http://bucket.io/"
                        + image.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
