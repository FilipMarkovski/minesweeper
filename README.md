# Softverski proces

### Minsko polje

Ovaj projekat se sastoji od klijentskog dela aplikacije (JavaFX aplikacija), koja pomoću soketa i JAX-WS komunicira sa serverskim delom aplikacije. JAX-WS je implementiran za prijavu korisnika.

Zadatak je bio implementirati igricu minsko polje. Kad se korisnik uloguje na sistem, može da odabere iz padajućeg menija da započne igru ili, na staromodni način, klikom na ikonicu u gornjem delu programa. Za potrebe ove aplikacije korišćena je nit sa klijentske strane za merenje vremena koliko korisnik rešava igricu. To vreme koje protekne se zapravo koristi za bodovanje igrice - što je manji skor tj. vreme, to je bolji rezultat.

U projektu su korišćeni sledeći mikro arhitekturni paterni: Singleton, Bridge, Facade i Template method.
