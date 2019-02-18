package org.shahin.nazarov.util;


import javax.inject.Singleton;

@Singleton
public class PayPalPaymentProcessor implements PaymentProcessor {
    public PayPalPaymentProcessor() {
        System.out.println("fsdfs");
    }

    @Override
    public void pay() {
        System.out.println("Ready");
    }
}
