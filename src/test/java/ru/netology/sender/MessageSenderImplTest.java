package ru.netology.sender;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
class MessageSenderImplTest {

    @DisplayName("sender")
    @org.junit.jupiter.api.Test
    void sendMessage() {

        LocalizationServiceImpl loc = new LocalizationServiceImpl();
        GeoServiceImpl geoService = new GeoServiceImpl();
        MessageSenderImpl sender = Mockito.spy(new MessageSenderImpl(geoService, loc));

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        Mockito.when(sender.send(headers)).thenReturn("Добро пожаловать");

        String actual = sender.send(headers);
        assertEquals(actual, "Добро пожаловать");

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");
        Mockito.when(sender.send(headers)).thenReturn("Welcome");
        //

    }
    @DisplayName("geoLocation")
    @Test
    void tryLocation(){
        var geo = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geo.byIp("96.123.12.19")).thenReturn(new Location("New York", Country.USA, null,  0));
    }

    @DisplayName("location")
    @Test
    void localeTest(){
        var loc = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(loc.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
    }
}