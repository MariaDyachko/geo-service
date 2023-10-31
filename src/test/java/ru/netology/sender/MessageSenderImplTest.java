package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {

    @DisplayName("sender")
    @Test
    void testSender() {
        final String testIp = "172.";
        final String testGreeting = "Добро пожаловать";

        final String testIpEng = "96.";
        final String testGreetingEng = "Welcome";


        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(Mockito.any()))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Mockito.any()))
                .thenReturn(testGreeting);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIp);

        Assertions.assertTrue(messageSender.send(headers).contains(testGreeting));




        GeoService geoServiceEng = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceEng.byIp(Mockito.any()))
                .thenReturn(new Location(null, Country.USA, null, 0));

        LocalizationService localizationServiceEng = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceEng.locale(Mockito.any()))
                .thenReturn(testGreetingEng);

        MessageSender messageSenderEng = new MessageSenderImpl(geoServiceEng, localizationServiceEng);

        Map<String, String> headersEng = new HashMap<String, String>();
        headersEng.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIpEng);

        Assertions.assertTrue(messageSenderEng.send(headersEng).contains(testGreetingEng));
    }





}