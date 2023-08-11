package org.tattour.server.domain.custom.facade.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.custom.domain.Custom;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadCustomSummaryRes {

	private Integer id;
	private String name;
	private String imageUrl;

	public static ReadCustomSummaryRes from(Custom custom) {
		return ReadCustomSummaryRes.builder()
			.id(custom.getId())
			.name(custom.getName())
			.imageUrl(custom.getMainImageUrl())
			.build();

	}
}