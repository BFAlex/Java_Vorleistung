# Aufgabe 1 — Bibliotheksverwaltung

## Das Projekt

In diesem Projekt erhalten Sie die Grundstruktur einer kleinen Bibliothek. Ein `Loan` beschreibt das Ausleihen eines Buches durch eine Person. Eine `Library` verwaltet beliebig viele Ausleihen, kann deren Gebühren berechnen und den aktuellen Zustand ausgeben.

Sie dürfen beliebige Hilfsmethoden und Klassen hinzufügen. Die Signaturen der vorgegebenen Methoden und Interfaces dürfen Sie nicht verändern. Test-Dateien dürfen nicht angepasst werden.

## Aufgabe 1 — Eine Ausleihe modellieren

Das Interface `org.library.Loan` beschreibt eine Ausleihe mit Buchtitel, Name der ausleihenden Person, Dauer in Tagen und einer Rückgabe-Markierung.

1. Implementieren Sie die Klasse `org.library.BookLoan`, die das Interface `Loan` implementiert und die Felder `title`, `borrower`, `days` und `returned` besitzt.
2. Alle Felder sollen unveränderlich sein, mit Ausnahme der Rückgabe-Markierung.
3. Ergänzen Sie einen Konstruktor, der die Felder in der Reihenfolge `title`, `borrower`, `days`, `returned` initialisiert.
4. Werfen Sie eine `IllegalArgumentException`, wenn `days < 0` ist oder `title` bzw. `borrower` leer sind.

## Aufgabe 2 — Die Bibliothek verwalten

Die Klasse `org.library.SimpleLibrary` beschreibt eine Bibliothek, die aus beliebig vielen Ausleihen besteht.

1. Sorgen Sie dafür, dass `SimpleLibrary` das Interface `org.library.Library` implementiert und nach dem Konstruktor keine Ausleihen enthält.
2. Implementieren Sie `SimpleLibrary::addLoan`, sodass eine Ausleihe hinzugefügt wird.
3. Implementieren Sie `SimpleLibrary::openLoans`, sodass nur nicht zurückgegebene Ausleihen geliefert werden.
4. Implementieren Sie `SimpleLibrary::lateFee`, die für alle offenen Ausleihen eine Gebühr berechnet:

```text
fee = overdueDays * pricePerDay
```

`overdueDays` ist `max(0, days - freeDays)`.

5. Implementieren Sie `SimpleLibrary::markReturned`, die eine Ausleihe anhand ihres Titels als zurückgegeben markiert. Existiert kein passender Eintrag, soll eine `NoSuchElementException` geworfen werden.

## Aufgabe 3 — Ausleihen ausgeben

Erweitern Sie `org.library.LibraryPrinter`.

1. Sorgen Sie dafür, dass der intern gehaltene `OutputStream` buffered ist.
2. Implementieren Sie `LibraryPrinter::printLoan`, sodass genau folgendes Format entsteht:

```text
<title>;<borrower>;<days>;<returned>
```

3. Implementieren Sie `LibraryPrinter::printLibrary`, sodass alle Ausleihen jeweils in einer eigenen Zeile in Einfügereihenfolge ausgegeben werden.
