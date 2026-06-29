# Aufgabe 2 — Paketzentrum

## Das Projekt

In diesem Projekt arbeiten Sie mit einem Paketzentrum. Ein `Parcel` beschreibt ein einzelnes Paket mit Ziel, Gewicht und Priorität. Ein `ParcelHub` sammelt Pakete, prüft Versandregeln und berechnet Gesamtkosten.

Sie dürfen beliebige Hilfsmethoden und Klassen hinzufügen. Vorgegebene Signaturen dürfen nicht verändert werden.

## Aufgabe 1 — Ein Paket implementieren

Das Interface `org.shipping.Parcel` beschreibt ein Paket mit Empfängeradresse, Gewicht in Kilogramm, Priorität und Versandstatus.

1. Implementieren Sie `org.shipping.StandardParcel`.
2. Die Klasse soll die Felder `address`, `weight`, `priority` und `shipped` besitzen.
3. Ergänzen Sie einen Konstruktor in der Reihenfolge `address`, `weight`, `priority`, `shipped`.
4. Werfen Sie eine `IllegalArgumentException`, wenn `address` leer ist oder `weight <= 0`.

## Aufgabe 2 — Pakete verwalten

Die Klasse `org.shipping.ParcelHubImpl` beschreibt ein Versandzentrum.

1. Sorgen Sie dafür, dass `ParcelHubImpl` das Interface `ParcelHub` implementiert und initial leer ist.
2. Implementieren Sie `addParcel`, sodass ein Paket hinzugefügt wird, solange es noch nicht verschickt wurde.
3. Implementieren Sie `totalWeight`, die das Gesamtgewicht aller Pakete zurückliefert.
4. Implementieren Sie `shippingCost`, wobei sich die Kosten pro Paket wie folgt berechnen:

```text
cost = ceil(weight) * basePrice + prioritySurcharge
```

5. Implementieren Sie `shipAll`, die:
   - eine `IllegalStateException` wirft, wenn keine Pakete vorhanden sind,
   - alle Pakete als verschickt markiert,
   - die Anzahl der verschickten Pakete zurückgibt.

## Aufgabe 3 — Konfiguration einlesen

Erweitern Sie `org.shipping.ParcelConfigReader`.

1. Implementieren Sie `readConfig`, das Zeilen im Format `<address>|<weight>|<priority>` verarbeitet.
2. Leere Zeilen sollen ignoriert werden.
3. Ungültige Zeilen sollen eine `IllegalArgumentException` auslösen.
4. Mehrfach vorkommende Adressen dürfen erhalten bleiben und sollen jeweils als eigene Pakete gespeichert werden.
