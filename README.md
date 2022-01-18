<h1>Skabuzz README</h1>
<h2>Οδηγίες Μεταγλώττισης</h2>
Για την εκπόνηση της εργασίας χρησιμοποιήθηκε mvn και, επομένως, η  μεταγλώττιση του προγράμματος πραγματοποιείται μέσω της εντολής <b>«mvn compile assembly:single»</b>.
<h2>Οδηγίες Εκτέλεσης</h2>
Η εκτέλεση του προγράμματος πραγματοποιείται μέσω της εντολής <b>«java -jar "Skabuzz-1 - Release-jar-with-dependencies.jar"»</b>.
<h2>Οδηγίες Χρήσης</h2>
Το πρόγραμμα το οποίο αναπτύχθηκε αφορά ένα ομαδικό παιχνίδι γνώσεων, το οποίο ξεκινάει με την εμφάνιση λειτουργικού μενού εντολών που περιλαμβάνει τις επιλογές «Παίξε», «Οδηγίες» και «Έξοδος». Με την επιλογή «Παίξε» από τον χρήστη, ζητείται ο προσδιορισμός των ονομάτων για τις ομάδες Α και Β αντίστοιχα, η επιλογή των κατηγοριών από τις οποίες θα ήθελαν να τους εμφανιστούν ερωτήσεις («Μυθολογία», «Γεωγραφία», «Ιστορία»), το επίπεδο δυσκολίας το οποίο διαβαθμίζεται σε «Εύκολο», «Κανονικό» και «Δύσκολο» και, τέλος, η επιλογή των πόντων τερματισμού του παιχνιδιού που κυμαίνεται σε κλίμακα από 5 μέχρι 20 ανά 5. Στη συνέχεια, επιλέγεται η εντολή «Πάμε» και εμφανίζονται οι ερωτήσεις, με κάθε ομάδα να μπορεί να διεκδικήσει τη δυνατότητα απάντησής τους μέσω της ταχύτητας με την οποία θα πατήσουν τα πλήκτρα «Α» και «L» στο πληκτρολόγιο. Σε κάθε σελίδα που εμφανίζεται ερώτηση δίνεται και η επιλογή «Πίσω στο Μενού». Επιστρέφοντας στο αρχικό μενού, με την επιλογή «Οδηγίες» εμφανίζονται σαφείς διευκρινήσεις για τους κανόνες και τη χρήση του παιχνιδιού καθώς και για το σύστημα των πόντων. Τέλος, με την επιλογή «Έξοδος» τερματίζει το παιχνίδι. 
<h2>Δομή Αποθετηρίου</h2>
Το γενικό αποθετήριο στο GitHub περιλαμβάνει όλα τα αρχεία με τα οποία το παιχνίδι γίνεται πλήρως λειτουργικό.<br>Αναλυτικότερα:<br>
Κατά την είσοδο στο αποθετήριο (/Skabuzz) εμφανίζονται οι φάκελοι <b>“.idea”</b>, <b>“.settings”</b> και <b>“src”</b> καθώς και 6 αρχεία. Τα αρχεία <u>“Skabuzz-cs-cleanup.xml”</u> και <u>“Skabuzz-cs-formatter.xml"»</u> αφορούν το εργαλείο ανάλυσης κώδικα check style ενώ το <u>“pom.xml”</u> τη δόμηση του προγράμματος με βάση τη mvn.
Ο φάκελος <b>“src”</b> (Skabuzz/src/) περιλαμβάνει τους φακέλους <b>“main”</b> και <b>“test”</b>. Στον φάκελο <b>“main”</b> (Skabuzz/src/main/) υπάρχει ο φάκελος <b>“java/gr/aueb/dmst/jabuzz”</b> o οποίος περιέχει την κλάση για την επικοινωνία με τη βάση (Skabuzz/src/main/java/gr/aueb/dmst/jabuzzz/dbconnector/), τις κλάσεις-οντότητες για: 
<br>την κατηγορία (Skabuzz/src/main/java/gr/aueb/dmst/jabuzz/entities/Category.java/)
<br>το <u>επιπέδο δυσκολίας</u>
(Skabuzz/src/main/java/gr/aueb/dmst/jabuzz/entities/Difficulty.java/)
<br>την <u>ερωτήση</u>
(Skabuzz/src/main/java/gr/aueb/dmst/jabuzz/entities/Question.java/)
<br>το <u>score</u>
(Skabuzz/src/main/java/gr/aueb/dmst/jabuzz/entities/Score.java/)
<br>την <u>ομάδα</u>
(Skabuzz/src/main/java/gr/aueb/dmst/jabuzz/entities/Team.java/)
<br>και τη <u>main</u> μέθοδο στον φάκελο <b>“game”</b> (Skabuzz/src/main/java/gr/aueb/dmst/jabuzz/game).<br>
<br>Στον υποφάκελο <b>“view”</b> του φακέλου <b>“game”</b> , βρίσκονται οι κλάσεις που υλοποιούν την λειτουργικότητα των οθονών του παιχνιδιού.<br>
<br>Επίσης, περιλαμβάνει τον φάκελο <b>“resources”</b> (Skabuzz/src/main/resources/) στον οποίο περιέχονται τα <u>fxml</u> αρχεία που χρησιμοποιούνται για τη γραφική διεπαφή του παιχνιδιού, η <u>βάση δεδομένων</u> (Skabuzz/src/main/resources/database/)  και τις <u>φωτογραφίες</u> που εμφανίζονται στο πρόγραμμα (Skabuzz/src/main/resources/photos/).<br>
<br>Τέλος, στον φάκελο <b>“testClasses”</b> έχει αποθηκευθεί ο <u>κώδικας ελέγχου του προγράμματος</u> (Skabuzz/src/test/java/testClasses/).


