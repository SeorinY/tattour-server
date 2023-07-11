package org.tattour.server.user.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.service.StickerServiceImpl;
import org.tattour.server.user.domain.ProductLiked;
import org.tattour.server.user.domain.User;
import org.tattour.server.user.repository.impl.ProductLikedRepositoryImpl;
import org.tattour.server.user.service.dto.request.SaveProductLikedReq;
import org.tattour.server.user.service.dto.response.ProductLikedListRes;
import org.tattour.server.user.service.dto.response.ProductLikedRes;

@Service
@RequiredArgsConstructor
public class ProductLikedServiceImpl implements ProductLikedService {
    private final ProductLikedRepositoryImpl productLikedRepository;
    private final UserServiceImpl userService;
    private final StickerServiceImpl stickerService;

    @Override
    public void saveProductLiked(SaveProductLikedReq req) {
        User user = userService.getUserByUserId(req.getUserId());
        Sticker sticker = stickerService.getStickerByStickerId(req.getStickerId());

        productLikedRepository.save(ProductLiked.of(user, sticker));
    }

    @Override
    public ProductLikedListRes getLikedProductsByUserId(Integer userId) {
        List<ProductLiked> productLikedList = productLikedRepository.findAllByUser_Id(userId);
        List<ProductLikedRes> productLikedResList = EntityDtoMapper.INSTANCE.toProductLikedResList(productLikedList);

        return new ProductLikedListRes(productLikedResList);
    }
}