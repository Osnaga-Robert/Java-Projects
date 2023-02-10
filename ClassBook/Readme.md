1. Arhitectura aplicatiei
Pentru primul punct al temei, am respectat cerinta, facand toate clasele care sunt cerute. Pentru 
sablonul de proiectare Factory vom da ca argument tipul de user, primul nume si al doilea nume 
dupa care vom returna tipul de user cerut. La clasa Grade, am verificat de fiecare data ca notele 
sa fie diferite de null, in caz contrar le vom ignora. Pentru clasa Group am luat drept colectie 
ordonata colectie TreeSet prin care vom adauga studentii care apartin grupei respective. Clasele 
PartialCourse si FullCourse mostenesc clasa Course si in care am implementat metoda abstracta. 
Pe langa acestea am implementat sablonul de proiectare Builder folosindu-ma doar de obiectele 
fixe, adaugand pe parcurs studenti, note, grupe, etc.
Sablonul de proiectare Observer l-am facut in paralel cu ScoreVisitor deoarece o data ce un 
professor verifica notele, atunci vom crea nota respective si o vom adauga in catalog si vom 
notifica parintele in aceasta privinta. Clasa Notification continue numele si prenumele parintilor, 
notele si numele cursului,. Vom adauga observer-ul in lista atunci cand cream nota. Sablonul de 
proiectare Strategy l-am realizat conform documentatiei, fiecare profesorul avand modul lui de a 
selecta cel mai bun student. Pentru sablonul Memento doar am retinut notele atunci cand se cere, 
si le restaurez in momentul in care se face undo, facand o copie a notelor in Snapshot(care este o 
clasa interna in care se pastreaza o lista de note).
2. Interfata grafica
Pentru interfata grafica ne vom folosi de un system log in prin care fiecare user de autentifica 
prin numele si prenumele sau, putand dupa sa fie selectat tipul de user care se identifica. Pentru 
Student Page vom faca o lista pentru cursurile la care participa iar prin selectarea unui curs si 
apasarea unui buton se vor da diverse informatii pe care le extragem din catalog. In cazul in care 
studentul nu exista, vom transmite acest mesaj.
Pentru teacher/assistant page vom prelua informatiile din catalog si din scorevisitor din care vom 
extrage notele care trebuie verificate si care vor fi verificate de catre asistent sau professor prin 
apasarea unui buton.
Pentru Parent Page vom arata toate notificare pe care acestia le primesc in momentul in care us 
asistent sau un professor adauga o nota. Pentru acest lucru in Student am adaugat o lista de 
string-uri in care am pastrat toate notificarile.
Pentru pagina prin care vom putea adauga note sau sa adaugam asistenti la un anumit curs, vom 
prelua mai intai informatiile din catalog dupa care ii vom da permisiunea persoanei sa adauge 
note la studentii care nu au note, sau sa adauge asistenti care nu exista. Pentru aceste lucruri ne 
vom folosi de JOptionPane folosind un Panel custom pe care il vom crea prin metode.
Pentru pagina prin care adaugam cursuri vom adauga calea pe care fisierul json o are, dupa care 
vom adauga cursul respectiv conform fisierului care ne este dat.
