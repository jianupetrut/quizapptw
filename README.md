# quizapptw
3 decembrie

Doinita:
	
* Am pus un buton in activitatea de login (main activity) ce permite sa dai testul anonim (adica daca apesi pe butonul deschide activitatea cu testul anonim). Am facut asta adaugand metoda JoinTest in LoginActivity.java .

* Am modificat fisierul Manifest, adaugand la activitatea Join Test Activity si cine e activitatea parinte. (PRO TIP:se poate seta asta cand creezi o activitate noua, iti da posibilitatea si sa alegi who's his daddy. ). Asta ajuta ca atunci cand apesi pe butonul inapoi sa te duca la activitatea parinte.

* Am creat o noua activitate Student Main (de folosit in locul celei vechi Student Main Activity) pentru a testa cum merge layout-ul cu drawer. Momentan activitatea se poate accesa apasand pe butonul de login (ca alta idee nu mi-a venit ca sa o pot testa rapid). Layout-ul a creat 4 xml-uri in total pentru activitatea Student Main (nav_header_student_main, content_student_main, app_bar_student_main si activity_student_main2 care nu stiu ce rost mai are daca exista celelalte 3).

* Nu am sters StudentMainActivity in cazul in cae ne hotaram sa il folosim pe ala

4 decembrie

Andreea:

* Am creat activity_generate_code pe care am legat-o la butonul Get Code din activitatea de configurat teste pentru profesori

* Am creat activity_test_options catre care nu putem naviga momentan, trebuie legata la activitatea pentru selectarea unui test pe care nu am facut-o inca

George:

*Refactoring:*

* Am sters activitatile pe nefolositoare/deprecated: teacher activity main, student activity main

* Am sters xml nefolositoare/deprecated: old nav_header, old main_student_content etc

* Am creat doua noi activitati: feedback student, feedback profesor

* Am recreat activitatile existente cu nav drawer: generate code, test options, teacher configure test

* Am creat meniurile pentru profesori si studenti (se pot gasi in res/layout/menu/..._drawer.xml). Mai e de lucrat la routing la fiecare meniu

*Pasi pentru a crea o noua activitate cu nav drawer:*

1. New activity -> Nav Drawer
2. Importi R in numeActivitate.java
3. Te duci in numeActivitate.java si stergi partea de cod cu floatingActionButton
4. Te duci in app_bar_numeActivitate.xml in modul design, selectezi plicul din dreapta jos si apesi delete
5. Creezi designul in content_numeActivitate.xml
6. Editezi meniul in layout/menu/numeActivitate_drawer.xml
7. Faci routing pentru meniu in numeActivitate.java
8. !! Modifici numele itemelor din drawer menu( din nav_slideshow in ce trebuie, o sa ai eroare cand incerci sa dai build daca nu gasesti initial)
* Toate activitatile in afara de login sunt in acest moment facute cu navigation drawer, iar meniurile trebuie doar copiate. Am incercat sa fac un singur xml de meniu, dar nu se poate (trebuie facut cate un meniu pentru fiecare activitate)

6 Decembrie
Petrut:
* Fix buguri  nav_menu ------ cand creati o activitate noua cu drawer sa modificati si numele itemelor din drawer menu!
* Adaugat activitati
* Fix placeholder bug -- cand vreti sa folositi un placeholder pentru textview folositi optiunea de hint, nu de text( de ex. Enter test code)

 Doinita:
 
 Am adaugat activitatea de test si o activitate intermediara intre JoinTest (cum e si in schema). Am incercat sa afisez intrebarile in test cu un fragment ca de fiecare data cand apare o intrebare noua sa se schimbe doar fragmentul si nu intreaga actvitate (o sa o fac sa mearga in curand).

9-10 Ian
Petrut
* Am adaugat clasele pentru tabele
* Fix small bugs
* Am adaugat functionalitate pentru assignment history . TREBUIE MODIFICAT URL-UL
