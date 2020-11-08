**Vaatimusmäärittely**
 
 
Sovelluksen tarkoitus

Sovelluksen tarkoitus on mallintaa ja simuloida planeettaa, jolla asuvat eliöt vakauttavat planeetan ilmastonvaihtelua ja pitävät yllä itselleen sopivia olosuhteita planeetalla. Erityisesti tarkoitus on tarkkailla, miten eliöt korjaavat ulkoisia muutoksia olosuhteissa, kuten vaikkapa tähden aktiivisuuden vaihtelua.

Käyttöliittymäluonnos

Käyttöliittymä koostuu kahdesta osasta: simulaation alkuehtojen määrittely, ja simuloinnin hallinta (katkaisu, tallennus, nopeutus). Hallintavaiheessa käyttöliittymä näyttää käyrillä, miten planeetan eliöiden määrät vaihtelevat ja miten planeetan olosuhteet muuttuvat. Ohjelman auetessa avautuu ikkuna, joka kysyy jatketaanko edellistä ajoa, vai luodaanko uusi.

![Alkuruutu](https://github.com/rasse3/ot-harjoitustyo/blob/master/DaisySim/Dokumentaatio/kuvat/alkuruutu.bmp)

![Ajoruutu](https://github.com/rasse3/ot-harjoitustyo/blob/master/DaisySim/Dokumentaatio/kuvat/ajoruutu.bmp)


Perusversion tarjoama toiminnallisuus

Perusversiossa on mahdollista alustaa simulaatio seuraavin parametrein:
Tähti
Peruskuumuus (tässä tulee mahdollisuus valita listasta eri spektriluokan tähtiä tai asettaa ihan oma arvo).
Flaren todennäköisyys (jälleen mahdollista valita listasta olemassaolevia arvoja tai asettaa oma prosentti).
Flaren kuumuus prosentteina tähden normaalista tehosta.
Kaikkia näitä arvioita voi säätää aloitusruudulla tarjotuilla (ajan) funktioilla tai sitten käyttäjä voi määrittää oman funktion.
Valmiita funktoita on esim. sinikäyrää noudattava peruskuumuus

Kiertorata
Kierorata vaikutta saadun säteilyn määrään erityisesti etäisyyden puolesta
Myös radan soikeutta voi säätää

Ilmakehä
Ilmakehän kaasuista seurataan hapen, hiilidioksidin ja vesihöyryn määriä.
Kaasut synnyttävät kasvihuoneilmiön. 
Hapen suuri määrä saattaa aiheuttaa metsäpaloja, joissa vapautuu hiilidioksidia ja sitoutuu happea. Hiilidioksidin määrä vaikuttaa kasvien ominaisuuksiin, samoin hapen määrä eläinten ominaisuuksiin
Kasvit saattavat aiheuttaa pilvien tiivistymistä, joka viilentää planeettaa.
Oletuksena maapallon tämänhetkiset parametrit kaasuosapaineista.


Kasvit
Kasveilla on ominainen ihannelämpötila, jonka lisäksi ne heijastavat tähden valoa eri tavoin. Lisäksi niiden tehokkuus hapen tuotannossa vaihtelee sen mukaan, minkä verran ne tarvitsevat hiilidioksidia ja minkä verran ne kykenevät sitä sitomaan, eli kasvilla on erikseen hiilidioksidikynnys ja sen kulutus.

Eläimet
Eläimet syövät joko kasveja tai toisia eläimiä. Niillä on lisääntymisnopeus, ruokahalu ja elinkikä, jotka osittain riippuvat toisistaan.
Kasvinsyöjät kuluttavat kasveja ja happea, tuottavat hiilidioksidia. Lihansyöjät kuluttavat kasvinsyöjiä ja tuottavat hiildioksidia.
Isot eläimet syövät yleensä enemmän, lisääntyvät hitaammin ja kehittyvät hitaammin, mutta elävät pitempään.
Pienillä eläimillä päinvastoin.


Simulaation alustus
Pelaaja saa simulaatiota alustaessaan valita ainakin seuraavat asiat:
Parametrit tähdelle, eliöille ja kiertoradalle sekä ilmaston alkuehdot
Pelaaja saa valita, muuttuvatko eliöt hieman ajan myötä (evoluutio)
Jos evoluutio valitaan, voidaan myös määrätä saako uusia lajeja syntyä
Pelaajalla on myös vapaus valita valmiista simulaatioasetuksista ja myös mahdollisuus lisätä tietokantaan aloitusparametrejä. Myös jo aloitettuja simulaatioita voi ladata ja jatkaa
Voidaan määrittää myös mahdollinen meren osuus planeetasta, jossa osuudessa ei siis ole eliöitä (alkuversiossa)
Eryityisesti voidaan määritellä simuloitavaksi alkuperäinen ”Daisyworld”, jossa mitattiin vain kasvien määriä (tummat ja vaaleat)
Tässä on mahdollista alustaa laaja verkosto eri tavoin toimivia kasveja ja eläimiä. Vaikkapa ominaisuuksiltaan todellisen maailman rottia, jäniksiä ja lehmiä vastaavia eliöitä. 
Simulaation on tarkoitus käyttää oikeaan dataan perustuvia taulukoita siitä, miten lihansyöjien määrä riippuu kasvinsyöjien määrästä (ns. Lotka-Volterra). Lisäksi ilman lihansyöjiä kasvinsyöjät ennen pitkää tyhjentävät planeetan syötävästä (kasveista). Myös oikeisiin parametreihin perustuvia prototyyppieliöitä luodaan ja simulaation komponentteja voi tallentaa ja ladata.
Simulaation kulku
Pelaaja voi hallita simulaatiota ainakin seuraavasti:
Pysäytys, jatko, tallennus, nopeutus/hidastus
Datan tulostaminen muodossa, jonka voi syöttää plotattavaksi esim. Exceliin tai R:ään.
Data voidaan myös siirtää toisen simulaation aloituskonfiguraatioksi, jolloin voidaan muuttaa asetuksia kesken simuloinnin. 
Simulaatio esittää koko ajan käyrien muodossa eriteltyinä eri eliöt, tähden tilanne, paikka kiertoradalla ja ilmakehän kaasujen pitoisuudet ja hiilen määrä maaperässä.
Simulaation snapshot-tiheyttä voidaan vaihtaa vaikka kesken simuloinnin.



Jatkokehitysideoita

Vuodenajat
Pallon lohkominen sektoreihin, joilla oma (”mikro”-)ilmasto
Jäätiköt
Kasvien ja pilvien määrään perustuva automaattisesti generoituva elävä kuva planeetasta
Muiden ravinteiden kuin hiilen määrien seurantaa
Simulaatioden jako tietokannassa internetissä
Meriekosysteemit (eläimet, kasvit)
Levät, jotka toimivat kuin kasvit, mutta vedessä
Muut kaasut (metaani, rikkidioksidi)
Maaperän ravinteet ja hajottajat



