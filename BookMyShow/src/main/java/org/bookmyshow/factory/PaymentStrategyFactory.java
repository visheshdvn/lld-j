package org.bookmyshow.factory;

import org.bookmyshow.enums.PaymentType;
import org.bookmyshow.strategy.payment.CardPaymentStrategy;
import org.bookmyshow.strategy.payment.PaymentStrategy;
import org.bookmyshow.strategy.payment.UPIPaymentStrategy;

public class PaymentStrategyFactory {
    public static PaymentStrategy getStrategy(PaymentType paymentType) {
        if(paymentType == PaymentType.CARD) {
            return new CardPaymentStrategy();
        }

        return new UPIPaymentStrategy();
    }
}
