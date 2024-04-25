package com.project.network.util

// 400: 잘못된 요청
class BadRequestException(
    override val message: String?
) : RuntimeException()

// 401: 승인되지 않음
class UnauthorizedException(
    override val message: String?
) : RuntimeException()

// 403: 금지
class ForBiddenException(
    override val message: String?
) : RuntimeException()

// 404: 찾을 수 없음
class NotFoundException(
    override val message: String?
) : RuntimeException()

// 50X: 서버 에러
class ServerException(
    override val message: String?
) : RuntimeException()

// 정의되지 않은 HTTP 상태 코드나 사용자 정의 상태 코드
class OtherHttpException(
    val code: Int,
    override val message: String?
) : RuntimeException()

// 예상하지 못한 에러
class UnKnownException(
    override val message: String?
) : RuntimeException()