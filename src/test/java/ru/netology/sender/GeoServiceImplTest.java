package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    @DisplayName("GeoServiceImpl")
    @Test
    void tryLocation(){
        var geo = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertEquals(Country.USA, geo.byIp("96.123.85.207").getCountry());
    }
}
