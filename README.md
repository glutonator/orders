# Mikroserwis zarządzania udziałem w wydarzeniach

Do czytania dokumentacji storzonej przez swaggera polecam: 
https://editor.swagger.io/

run java application: java -jar orders-0.0.1-SNAPSHOT.jar --server.port=8181
run java application without console open: nohup java -jar orders-0.0.1-SNAPSHOT.jar --server.port=8181 &


Swagger for running application
http://rso-mf.westeurope.cloudapp.azure.com:8181/swagger-ui.html

Kody odpowiedzi: \
200 - okey \
201 - błedna walidacja tokenu - nie ważny token\
202 - błędna walidacja tokenu - nie prawidłowy podpis\
203 - token należy do kogoś innego, użytkownik nieprawidłowy\
204 - komunikacja z mikroserwisem zarządzania biletami i wydarzeniami\
205 - nie ma takich biletów\
206 - nie ma takiego zamówienia\
207 - nie jesteś adminem, uprawniania nie udzielone\
208 - płatnosć nie powiodła się  // nie występuje - zamockowana funkcja na "true"\
209 - zwrot płatności się nie powiódł // nie występuje - zamockowana funkcja na "true"\
210 - zamówienie już zwrócone, nie można zwrócić go jeszcze raz\

funckje zwracają też błedy wynikając z komuniakcji z mikroserwisem zarządzania biletami i wyadzrzeniami, te kody błedu to: 105,109,110,111\
szczególy w dokumentacji tego mikroserwisu

admin może używać następujących końców /all_orders oraz /event/{eventid}
pozostałe końce może używać zarówno admin jak i zwykły użytkownik(premium czy nie premium)

Zwracane statusCode jak jest wszystko dobrze to 200,\
 jak jest coś nie tak to 400,\
 jak jest token expired to 401,\
 a jak nie ważny podpis tokena(nieprawidłowy token) to 402 
 
 Przykładowe requesty: https://documenter.getpostman.com/view/4203408/rso-mfwesteuropecloudappazurecom/RW1hiwGF