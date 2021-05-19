package com.course.kafka.kafkaorder.Request;

public class PromotionRequest {
    
    private String promotionCode;

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionRequest(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionRequest() {
    }

    @Override
    public String toString() {
        return "PromotionRequest [promotionCode=" + promotionCode + "]";
    }

    

}
