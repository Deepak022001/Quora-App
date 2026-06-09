package com.example.QuoraApp.Service.Interfaces;

import com.example.QuoraApp.Dto.LikeRequestDto;
import com.example.QuoraApp.Dto.LikeResponseDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILikeService {

    Mono<LikeResponseDto> createLike(LikeRequestDto likeRequestDto);

    Mono<LikeResponseDto> countLikesByTargetIdAndTargetType(String targetId, String targetType);

    Flux<LikeResponseDto> countDisLikesByTargetIdAndTargetType(String targetId, String targetType);

    Mono<LikeResponseDto> toggleLikes(String targetId, String targetType, Boolean isLike);

}
