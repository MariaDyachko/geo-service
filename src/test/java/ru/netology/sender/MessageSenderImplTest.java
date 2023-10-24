package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class MessageSenderImplTest {

    //@org.junit.jupiter.api.Test
    @Test
    void send() {

        LocalizationServiceImpl loc = new LocalizationServiceImpl();
        GeoServiceImpl geoService = new GeoServiceImpl();
        MessageSenderImpl sender = Mockito.spy(new MessageSenderImpl(geoService, loc));

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(sender.send(headers)).thenReturn("saas");

        //String actual = sender.send(headers);

        //assertEquals(actual, "saas");

    }
}