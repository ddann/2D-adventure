 
Käyttöohjeet pelille 2D-adventure

1. Pelin voittoehdot
2. Pelin graafisen esitykset selitettynä.
3. Pelin omanlaisista fysiikoista
4. Controllit
5. Tietoja resoluution vaikutuksesta peliin
6. Vaatimukset



1. Pelin voittoehdot
Pelin tarkoituksena on voittaa 'bossi' ampumalla sitä "valkoisilla ympyröillä". Eli siis Voittaakseen pelin on saatava bossin hpt nollalle osumalla ammuksilla.
Mikään vaan osuma olisi kosketus bossiin tai sen ampumaan ammukseen (unimplemented) aiheuttaa häviön pelaajalle.


2. Pelin graafisen esitykset selitettynä.
Pelaaja on pienempi musta ympyrä ja bossi taas iso musta ympyrä.
Taas ammukset ovat valkoisia. Pelaajan ovat ne pienemmät (bossin ampuminen unimplemented).


3. Pelin omanlaisista fysiikoista
Tämä peli käyttää suoraan fysikaalisia kaavoja laskemaan hahmojen liikkeet, tämän takia hahmo ei saa heti nopeutta johonkin suuntaan vaan ainoastaan kiihtyvyyttä, josta seuraa nopeuden muutos (ja näin ollen paikan muutos).
Toisin sanoen fysiikoilta peli on erikoinen ja kannattaa huomata, että liikkuvuus on todella huonoa ja vaikeaa.


4. Controllit
Nuolinäppäin vasemmalle: Aiheuttaa "pelaajan hahmolle kiihtyvyyttä vasemmalle" vaikutus ei näy heti...mutta siis tämä on käytännässä liikkuminen vasemmalle.
Nuolinäppäin oikealle: Aiheuttaa "pelaajan hahmolle kiihtyvyyttä oikealle" vaikutus ei näy heti...mutta siis tämä on käytännässä liikkuminen oikealle.
Näppäin 'a': On ampumisnappi. Tästä hahmo ampuu. Ampumisen välillä on oltava ainakin 5 loopin verran kulunut kun peli pyörii 60 framia sekunnissa (siis FPS=60) (siis 5 * 1/60 s).
Näppäin 'd': On hyppynäppi. Tästä hahmo hyppää, jos on maassa.


5. Tietoja resoluution vaikutuksesta peliin
Peli olettaa, että resoluution, jolla se esitetään on 1920x1080 (ns "FULL HD").
Tämä tarkoittaa sitä, että jotta se näkyy oikein 1920x1080 outputilla on se pelattava Full Screenina.
Jos resoluution on korkeampi, niin paras vaihtoehto on ikkunana, mutta toki Full Screenia voi käyttää silloin vaan ilmestyy harmaata extra aluetta alas ja oikealle.
Jos resoluutio on alempi olen pahoillani, mutta kyseinen tarkkus on käytetty pelissä sen takia, että sen pystyi pitämään miniminä desktopissa ja näin ollen pelin laskut kentässä olisivat tarkempia. (Kannettavassa peli ei välttämättä näy oikein tosiaan)

Pahoittelut kelle vaan josta tästä aiheutuu häiriötä, mutta oli (coh..coff..swingin vika) niin, että muuta tapaa tehdä absoluuttinen tarkka output peltilanteessa ei ollut resurssien puutteen takia mahdollista.
(Olisi ollut mahdollistat tehdä pienen ikkunan, joka olisi ollut erityisesti "FUll HD" ja isommissa resoluutioissa turhan pieni. Mutta silloin kentän olisi oltava ollut pienempi ja näin ollen "pelin tarkkuden" laskuissa myös.)


6. Vaatimukset
(Tehty vähän läpällä)
Pyöriäkseen ohjelma vaatii, että on asennettuna java runtime tietokoneeseen (siis oraclen tai openjdk). Voi olla mahdollista, että liian vanha JRE versio ei toimi.
Tehovaatimukset ovat epämääräisiä, mutta ainakin näytönohjaimen ja RAMin kannalta lähes mikä vaan kelpaa. Mutta CPU vaativuus voi olla kovempi. (Toisin sanoen älä käytä 1990-luvun lopun "minikannettavaa")
Ja näppäimistö on pakollinen voidaakseen pelata peliä. (Paitsi jos on controller, yms. jossa mapattu oikeisiin näppäimiin toki)
Graafinen käyttöliittymä myös on vaativuus toki eikä tekstipohjainen riitä.