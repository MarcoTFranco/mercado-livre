package com.MarketPlace.MercadoLivre.model.dto;

import com.MarketPlace.MercadoLivre.model.entities.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDTO {

    private final Set<ProductImageDTO> link;
    private final String name;
    private final BigDecimal value;
    private final String description;
    private final Double avarageNotes;
    private final Integer totalNumberOfProductNotes;
    private final Set<ProductFeatureDTO> productFeatures;
    private final Set<ProductOpinionDTO> productOpinions;
    private final Set<DoubtAboutProductDTO> productDoubt;

    public ProductDTO(Product product) {

        this.link = imageDTO(product.getProductImages());
        this.name = product.getName();
        this.value = product.getValue();
        this.description = product.getDescription();
        this.avarageNotes = product.averangeNotes();
        this.totalNumberOfProductNotes = product.getProductOpinions().size();
        this.productFeatures = featureDTO(product.getProductFeatures());
        this.productOpinions = opinionDTO(product.getProductOpinions());
        this.productDoubt = doubtsDTO(product.getProductDoubts());
    }

    public Set<ProductImageDTO> imageDTO(Set<ProductImage> images) {
        return images.stream()
                .map(ProductImageDTO::new)
                .collect(Collectors.toSet());
    }

    public Set<ProductFeatureDTO> featureDTO(Set<ProductFeature> features) {
        return features.stream()
                .map(ProductFeatureDTO::new)
                .collect(Collectors.toSet());
    }

    public Set<ProductOpinionDTO> opinionDTO(Set<ProductOpinion> opinions) {
        return opinions.stream()
                .map(ProductOpinionDTO::new)
                .collect(Collectors.toSet());
    }

    public Set<DoubtAboutProductDTO> doubtsDTO(Set<DoubtAboutProduct> doubts) {
        return doubts.stream()
                .map(DoubtAboutProductDTO::new)
                .collect(Collectors.toSet());
    }

    public Set<ProductImageDTO> getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Double getAvarageNotes() {
        return avarageNotes;
    }

    public Integer getTotalNumberOfProductNotes() {
        return totalNumberOfProductNotes;
    }

    public Set<ProductFeatureDTO> getProductFeatures() {
        return productFeatures;
    }

    public Set<ProductOpinionDTO> getProductOpinions() {
        return productOpinions;
    }

    public Set<DoubtAboutProductDTO> getProductDoubt() {
        return productDoubt;
    }
}
