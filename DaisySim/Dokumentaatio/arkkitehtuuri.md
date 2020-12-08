
Ohjelmassa on kolme pakettia, logiikka, gui ja database. Näistä gui on käyttäjän ja logiikan välissä ja databaseen tallennetaan simulaatioita ja niiden alkuparametreja. 

Ohjelman logiikka sisältää kolme toisistaan riippuvaista dynaamista elementtiä, eläimet ja kasvit ja planeetta. Eläimet eivät vuorovaikuta vahvasti planeetan kanssa, mutta kasvisto säätelee voimakkaasti sen oloja. Ohjelman suoritus etenee tällä hetkellä päivä kerrallaan, niin että "Day"-luokka päivittää kaikkien muiden luokkien arvot, hoitaa tietojen tallennuksen logiin ja alustaa seuraavan iteraation. 

Käyttöliittymä

Käyttöliittymä tulee sisältämään kaksi osaa, simulaation alustuksen ja sen hallinnan. 






![Pakkauskaavio](https://github.com/rasse3/ot-harjoitustyo/blob/master/DaisySim/Dokumentaatio/kuvat/pakkauskaavio.png)
![Sekvenssikaavio](https://github.com/rasse3/ot-harjoitustyo/blob/master/DaisySim/Dokumentaatio/kuvat/Paiva_daisysimissa.png)
