package org.tattour.server.domain.sticker.service.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.domain.Sticker;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class StickerInfo {

	private Integer id;
	private String name;
	private String description;
	private Integer price;

	@Schema(nullable = true)
	private Integer discountRate;
	@Schema(nullable = true)
	private Integer discountPrice;
	private String composition;
	private String size;
	private Boolean isCustom;
	private Integer shippingCost;
	private List<String> stickerThemes;
	private List<String> stickerStyles;
	private List<String> images;


	public static StickerInfo from(Sticker sticker) {
		List<String> stickerImages = new ArrayList<>();
		List<String> stickerThemes = new ArrayList<>();
		List<String> stickerStyles = new ArrayList<>();
		Integer discountRate = null;
		Integer discountPrice = null;

		if (!Objects.isNull(sticker.getDiscount())) {
			discountRate = sticker.getDiscount().getDiscountRate();
			discountPrice = sticker.getDiscountPrice();
		}
		stickerImages.add(sticker.getMainImageUrl());
		sticker.getStickerImages().stream()
				.map(image -> stickerImages.add(image.getImageUrl()))
				.collect(Collectors.toList());
		sticker.getStickerThemes()
				.stream()
				.map(theme -> stickerThemes.add(theme.getTheme().getName()))
				.collect(Collectors.toList());
		sticker.getStickerStyles()
				.stream()
				.map(style -> stickerStyles.add(style.getStyle().getName()))
				.collect(Collectors.toList());
		return StickerInfo.builder()
				.id(sticker.getId())
				.name(sticker.getName())
				.description(sticker.getDescription())
				.price(sticker.getPrice())
				.discountRate(discountRate)
				.discountPrice(discountPrice)
				.composition(sticker.getComposition())
				.size(sticker.getSize())
				.isCustom(sticker.getIsCustom())
				.shippingCost(3000)
				.stickerThemes(stickerThemes)
				.stickerStyles(stickerStyles)
				.images(stickerImages)
				.build();
	}
}
