After Several Insertion for Realm DB, following results achieved.
First, 10,000 objects created.
Second, These objects inserted to Realm db. The process took:

##Create

Inserting all 10000 objects into the Realm/Snappy DB.

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Realm (seconds) | 301 | 283 | 290 | 286 | 289 | 285 | 287 | 287 | 272 | 276 |
| SnappyDB (milli seconds) | 1532 | 1407 | 16126 | 20419 | 1985 | 1891 | 1692 | 1408 | 1323 | 1342 |

##Read

Finding a list of items that its Booking.id contains a value (such as 1, 35, etc.)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| ---| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Realm (ms) | 2 | 1 | 9 | 5 | 3 | 2 | 1 | 1 | 1 | 1 | 2 |
| SnappyDB (ms) | 114 | 1 | 6 | 0 | 74 | 7 | 87 | 1 | 7 | 15 |

##Delete

Delete all items with type Booking.class (all 10000 items in DB)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| ---| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Realm (ms) | 23 | 22 | 18 | 25 | 27 | 21 | 42 | 21 | 33 | 19 |
| SnappyDB (ms) | 12 | 0 | 9 | 11 | 8 | 18 | 6 | 7 | 15 | 7 |