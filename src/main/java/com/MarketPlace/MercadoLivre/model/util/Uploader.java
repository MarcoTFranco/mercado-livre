package com.MarketPlace.MercadoLivre.model.util;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {
    /**
     * Upload a image to a bucket and return its url.
     * @param images Images to be uploaded
     * @return Set containing urls of uploaded files
     */
    Set<String> upload(List<MultipartFile> images);
}
