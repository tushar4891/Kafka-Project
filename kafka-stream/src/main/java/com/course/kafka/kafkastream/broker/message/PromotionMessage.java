package com.course.kafka.kafkastream.broker.message;

public class PromotionMessage {
    
    private String promotionCode;

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionMessage(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public PromotionMessage() {
    }

    @Override
    public String toString() {
        return "PromotionMessage [promotionCode=" + promotionCode + "]";
    }

    
}
