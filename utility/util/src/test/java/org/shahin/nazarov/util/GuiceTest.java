package org.shahin.nazarov.util;

import org.shahin.nazarov.util.reflection.Injection;

public class GuiceTest {

    public static void main(String[] args) {
        Injection injection = Injection.getInstance(new PaymentModuleBinding());
        PaymentProcessor instance = injection.get(PaymentProcessor.class);
        instance.pay();
        PaymentProcessor instance2 = Injection.getInstance().get(PaymentProcessor.class);
        instance2.pay();
    }
}
