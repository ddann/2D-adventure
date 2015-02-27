 
Tämän ohjelman rakenteen tärkein luokka on 'Engine', joka on siis "pelin Game Engine".
Projektin toki "käynnistyskohta", eli main-luokka on 'App', joka ei tee mitään muuta kun luo 'Engine' luokan.
(Syy main luokan "turhuuteen" on se, että hyvien käytäntöjen mukaan main luokka kuuluisi olla aloituspiste eikä luokka, joka toimii pääluokkana, tai edes ylipäätänsä olisi käytössä muuta kun alustamiseen.) 


Sitten mitä tulee pelin objekteihin (tarkoitan sekä hahmoja, että "objekteja"), niin rakenne on seuraava:
On olemassa luokat 'Player' ja 'Boss', jotka perivät 'Character' luokan ja taas 'Character' luokka perii 'GObject' luokan.
Vastaavasti objektit ovat kuvattu 'GameObject' luokassa, joka perii 'GObject' luokan.
Tämä rakenne näiden osalta on sitä varten, että eri luokkien instansseja olisi helpompi käsitellä keskenään ja tietenkin ei tulee turhaa toistoa.


Kuten ennen sanottu 'Engine' luokka on pääluokka, eli siis se käsittelee käytännössä kaikkia muita luokkia pitkälti.
Se myös luo 'InputManaggerin' (näppäimistökuuntelijan) sekä 'GUI'n instanssin, jotta peliä voisi pelata.
Sekä tietenkin 'Playerin, 'Bossin', 'Stagin' sekä 'GameObjectit'.


Sitten on myös Stage luokka, joka on pitkälti vaan dataa sisälltävä luokka, kuten kentän koko, hahmojen aloituspiste, yms.





Ylempi asia on kuvannut vaan pakkauksen 'project' luokkia, jotka ovat logiikkaluokkia.

Erillisessa 'gui'-pakkauksessa (hyviä käytänteitä noudattaen) on 'GUI' luokka, jonka tehtävänä on nimensä mukaan huolehtia kaikesta grafiikasta.
Sen tehtävänä on piirtää muun muuassa pelitilanne, joten näin ollen se käsittelee 'Charactereita' ja 'GameObjecteja' sekä 'Stagia' datan "hakemiseen".
Se myös käyttää inputien lukemiseksi 'InputManageria', joka taas muuttaa playerin atribuutteja.