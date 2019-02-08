package org.shaheen.nazarov.utility.crypto;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public interface Constants {

    String SERVER_PRIVATE_KEY = "server-private-key";
    String SERVER_PUBLIC_KEY = "server-public-key";


    interface Crypt {
        String EC_ALGORITHM = "EC";
        String ECDH_ALGORITHM = "ECDH";
        int DEFAULT_KEY_SIZE = 256;
        String ALGORITHM_ENCRYPTION = "DES/ECB/PKCS5Padding";
        String ALGORITHM_SECRET = "DES";
    }

    interface SMS {
        String URL = "http://apps.lsim.az/sendsmsjm/restful/quicksms/send";
        String P_LOGIN = "login";
        String P_NUMBER = "msisdn";
        String P_TEXT = "text";
        String P_SENDER = "sender";
        String P_KEY = "key";
        String DEFAULT_SENDER = "Asan Imza";
        String DEFAULT_LOGIN = "asanimza";
        String CONFIRMATION_MESSAGE = "Use __verification-code__ to verify your account.";
        String M_CONFIRMATION_CODE = "__verification-code__";
    }

    interface REGEX {
        String INTERNATIONAL_MOBILE_PHONE_FORMAT = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        String VEHICLE_NUMBER_FORMAT = "^[0-9]{2}+-[A-Z]{2}+-[0-9]{3}$";
        String JUST_DOT = "\\.";
    }

    interface DATE {
        String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        String DATE_PATTERN = "yyyy-MM-dd";
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String TIME_PATTERN = "HH:mm:ss";
        DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

        String CARD_EXPIRE_DATE_PATTERN = "MM/yyyy";
        DateTimeFormatter CARD_EXPIRE_DATE_FORMATTER = DateTimeFormatter.ofPattern(CARD_EXPIRE_DATE_PATTERN);

        ZoneId UTC_ZONE = ZoneId.from(ZoneOffset.UTC);
        ZoneId GMT_PLUS_4_ZONE = ZoneId.from(ZoneOffset.of("+4"));
        ZoneId SERVER_ZONE = UTC_ZONE;
    }

}
