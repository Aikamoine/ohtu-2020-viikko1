package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto virhevarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void miinnustilavuusLuetaanOikein() {
        virhevarasto = new Varasto(-10);
        assertEquals(0, virhevarasto.getTilavuus(), vertailuTarkkuus);
        virhevarasto = new Varasto(-10, 10);
        assertEquals(0, virhevarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void miinussaldoLuetaanOikein() {
        virhevarasto = new Varasto(10, -10);
        assertEquals(0, virhevarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorinSaldoOikein() {
        varasto = new Varasto(10, 8);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(10,10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(8, 10);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        varasto.lisaaVarastoon(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test public void liikaLisaysMeneeMaksimiin() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    //muutos
    @Test
    public void tekstiMuotoOnOikein() {
        varasto.lisaaVarastoon(3);
        assertEquals("saldo = 3.0, vielä tilaa 7.0", varasto.toString());
    }
    
    @Test
    public void negatiivinenOttoEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        assertEquals(0.0, varasto.otaVarastosta(-3), vertailuTarkkuus);
    }
    
    @Test
    public void isoOttoAntaaJaljellaOlevanMaaran() {
        varasto.lisaaVarastoon(5);
        assertEquals(5, varasto.otaVarastosta(100), vertailuTarkkuus);
    }

    
    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}