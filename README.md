# OOP - Ships

Created the classes and algorithms necessary to manage two types of ships:
    - Submarines
    - Fighter jets

## Usage:

`javac ShipManger.java && java ShipManager`

## File Format:
* Submarines:
    * `S,<serial number>,<commision year>,<cylinders>,<fuel>,<hull>,<max depth>`
* Fighter jets:
    * `F,<serial number>,<commision year>,<cylinders>,<fuel>,<wingspan>,<ordnance>`

**Example input file: test.txt**

## Features:

1. Destination Check - displays the ship with the fastest trip
2. Find Duplicates - displays the duplicate ships found in the storage container
3. View ships
4. Load ships from file
5. Save ships to file
6. Manually enter ships

## Data Validation:

* Serial number: a real number in the form XXX.YYY, the first part of the number 
    must be in the range 100 to 300 (inclusive), the second part of the number 
    must be in the range 001 to 999 (inclusive).
* Commision year: an integer number between 1950 and 2022 (inclusive).
* Max depth: real number in the range -500.0 to 0.0.
* Hull: Must be one of the following strings, casing does not matter:
    – “STEEL”
    – “ALLOY”
    – “TITANIUM”
* Ordnance: String, non-empty string is valid.
* Wingspan: real number between 2.20 and 25.6 (inclusive).
* Fuel: Must be one of the following strings, casing does not matter: 
    – “BATTERY”
    – “DIESEL”
    – “BIO”
* Cylinders: integer number between 2 and 20,
