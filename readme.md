After Several Insertion for Realm DB, following results achieved.
First, 10,000 objects created.
Second, These objects inserted to Realm db. The process took:

##Create

Inserting all 10000 objects into the Realm/Snappy DB.

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Realm (ms) | 1736 | 1611 | 1550 | 1556 | 1708 | 1923 | 1726 | 1843 | 1789 | 1744 |
| SnappyDB (ms) | 1432 | 1447 | 1468 | 1437 | 1683 | 1893 | 1686 | 1774 | 1678 | 1689 |

##Read

Finding a list of items that its Booking.id contains a value (such as 1, 35, etc.)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| ---| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Realm (ms) | 24 | 10 | 3 | 3 | 5 | 6 | 9 | 9 | 6 | 6 | 5 |
| SnappyDB (ms) | 187 | 74 | 7 | 4 | 93 | 111 | 8 | 2 | 108 | 87 |

##Delete

Delete all items with type Booking.class (all 10000 items in DB)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| ---| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Realm (ms) | 20 | 22 | 21 | 28 | 25 | 93 | 21 | 21 | 22 | 22 |
| SnappyDB (ms) | 255 | 238 | 251 | 285 | 253 | 284 | 278 | 295 | 277 | 274 |


##Screenshot
![Screenshot](https://github.com/Hesamedin/DatabaseTester/raw/master/screenshots/screenshot.gif
