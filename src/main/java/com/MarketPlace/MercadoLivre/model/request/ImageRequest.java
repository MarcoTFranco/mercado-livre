package com.MarketPlace.MercadoLivre.model.request;

import com.MarketPlace.MercadoLivre.model.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


public class ImageRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> images = new ArrayList<>();

    @Deprecated
    public ImageRequest() {
    }

    public ImageRequest( @Size(min = 1) @NotNull List<MultipartFile> images) {
        this.images = images;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}
