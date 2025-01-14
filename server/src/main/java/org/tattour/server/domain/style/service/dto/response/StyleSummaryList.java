package org.tattour.server.domain.style.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.domain.Style;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StyleSummaryList {

    List<StyleSummary> styleSummaries;

    public static StyleSummaryList of(List<Style> styles) {
        List<StyleSummary> styleSummaries = styles.stream()
                .map(StyleSummary::of)
                .collect(Collectors.toList());
        return new StyleSummaryList(styleSummaries);
    }

}
