package org.tattour.server.domain.sticker.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.tattour.server.domain.discount.domain.Discount;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.global.util.AuditingTimeEntity;

@Table(name = "sticker")
@Entity
@Builder
@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Sticker extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    @Column(name = "discount_price")
    private Integer discountPrice;
    @Column(name = "shipping_fee")
    private Integer shippingFee;
    private String composition;
    private String size;
    @Column(columnDefinition = "tinyint")
    private Boolean state;

    @Column(name = "is_custom", columnDefinition = "tinyint")
    private Boolean isCustom;

    @Column(name = "main_image_url", columnDefinition = "text")
    private String mainImageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
    private List<StickerTheme> stickerThemes;

    @OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
    private List<StickerStyle> stickerStyles;

    @OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
    private List<StickerImage> stickerImages;

    @OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
    private List<Order> orderItems;

    @OneToMany(mappedBy = "sticker", cascade = CascadeType.ALL)
    private List<ProductLiked> productLikeds;

    public void setStickerThemes(
            List<StickerTheme> stickerThemes) {
        this.stickerThemes = stickerThemes;
    }

    public void setStickerStyles(
            List<StickerStyle> stickerStyles) {
        this.stickerStyles = stickerStyles;
    }

    public void setImages(List<StickerImage> stickerImages) {
        this.stickerImages = stickerImages;
    }

    public void applyDiscount(Discount discount) {
        this.discount = discount;
        this.discountPrice = this.price * (100 - discount.getDiscountRate()) / 100;
    }

    public static Sticker from(
            String name,
            String description,
            String mainImageUrl,
            Boolean isCustom,
            Integer price,
            String composition,
            String size,
            Integer shippingFee,
            Boolean state
    ) {
        return Sticker.builder()
                .name(name)
                .description(description)
                .mainImageUrl(mainImageUrl)
                .isCustom(isCustom)
                .price(price)
                .composition(composition)
                .size(size)
                .shippingFee(shippingFee)
                .state(state)
                .build();
    }
}



