# Aufgabe 3 — Kinoplaner

## Das Projekt

Gegeben ist die Struktur eines Kinos. Eine `Screening` beschreibt eine einzelne Vorstellung, ein `CinemaSchedule` verwaltet mehrere Vorstellungen und kann die erwarteten Einnahmen berechnen.

## Aufgabe 1 — Vorstellungen modellieren

Das Interface `org.cinema.Screening` beschreibt eine Vorstellung mit Filmtitel, Saalnummer, Dauer in Minuten und Anzahl freier Plätze.

1. Implementieren Sie `org.cinema.MovieScreening`.
2. Verwenden Sie die Felder `title`, `hall`, `durationMinutes` und `freeSeats`.
3. Ergänzen Sie einen Konstruktor in genau dieser Reihenfolge.
4. Werfen Sie eine `IllegalArgumentException`, wenn `durationMinutes <= 0`, `hall <= 0` oder `freeSeats < 0`.

## Aufgabe 2 — Spielplan vervollständigen

Die Klasse `org.cinema.DailyCinemaSchedule` beschreibt alle Vorstellungen eines Tages.

1. Implementieren Sie das Interface `org.cinema.CinemaSchedule`.
2. Nach dem Konstruktor soll der Spielplan leer sein.
3. Implementieren Sie `addScreening`.
4. Implementieren Sie `totalDuration`, die die Summe aller Vorführdauern zurückliefert.
5. Implementieren Sie `expectedRevenue`, wobei jede Vorstellung so berechnet wird:

```text
revenue = soldSeats * ticketPrice
```

`soldSeats` ist `hallCapacity - freeSeats`.

6. Implementieren Sie `largestScreening`, die die Vorstellung mit den wenigsten freien Plätzen zurückliefert. Falls keine Vorstellung vorhanden ist, soll ein leeres `Optional` geliefert werden.

## Aufgabe 3 — Spielplan drucken

Erweitern Sie `org.cinema.SchedulePrinter`.

1. Der übergebene `OutputStream` soll intern buffered werden.
2. Implementieren Sie `printScreening` mit dem Format:

```text
<title>,<hall>,<durationMinutes>,<freeSeats>
```

3. Implementieren Sie `printSchedule`, sodass alle Vorstellungen in Einfügereihenfolge ausgegeben werden.
