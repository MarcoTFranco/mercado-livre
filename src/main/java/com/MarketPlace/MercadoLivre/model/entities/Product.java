package com.MarketPlace.MercadoLivre.model.entities;

import com.MarketPlace.MercadoLivre.model.request.FeaturesRequest;
import com.MarketPlace.MercadoLivre.model.request.OpinionRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Positive
    private BigDecimal value;
    @PositiveOrZero
    private Integer amountAvailable;
    @Size(max = 1000)
    private String description;
    private LocalDate instantOfCreation;
    @NotNull
    @ManyToOne
    private Category category;
    @NotNull
    @ManyToOne
    private User owner;
    @Size(min = 3)
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<ProductFeature> productFeatures = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductImage> productImages = new HashSet<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private Set<ProductOpinion> productOpinion = new HashSet<>();

    @Deprecated
    public Product() {
    }

    public Product(@NotBlank String name,
                   @Positive BigDecimal value,
                   @PositiveOrZero Integer amountAvailable,
                   @Size(max = 1000) String description,
                   @NotNull Category category, @NotNull User owner,
                   @Size(min = 3) Collection<FeaturesRequest> features) {

        this.name = name;
        this.value = value;
        this.amountAvailable = amountAvailable;
        this.description = description;
        this.category = category;
        this.owner = owner;
        this.instantOfCreation = LocalDate.now();
        this.productFeatures.addAll(features.stream().map(feature -> feature.toModel(this)).collect(Collectors.toSet()));

        Assert.isTrue(this.productFeatures.size() >= 3, "Todo produto dever ter no minimo 3 carateristicas");
    }

    public User getOwner() {
        return owner;
    }

    public void associatesImages(Set<String> links) {
        Set<ProductImage> images = links.stream().map(link -> new ProductImage(this, link)).collect(Collectors.toSet());
        this.productImages.addAll(images);
    }

    public void associatesOpinions(OpinionRequest request, User userOpinion) {
        this.productOpinion.add(request.toModel(this, userOpinion));
    }

    public boolean belongsUser(User owner) {
        return this.owner.equals(owner);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", value=" + value + ", amountAvailable=" + amountAvailable + ", description='" + description + '\'' + ", instantOfCreation=" + instantOfCreation + ", category=" + category + ", owner=" + owner + ", productFeatures=" + productFeatures + ", productImages=" + productImages + ", productOpinion=" + productOpinion + '}';
    }
}
