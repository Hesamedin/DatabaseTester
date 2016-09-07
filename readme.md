After Several Insertion for Realm DB, following results achieved.
First, 10,000 objects created.
Second, These objects inserted to Realm db. The process took:

##Create (Realm)

Inserting all 10000 objects into the Realm DB.

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 | #11 | #12 |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Seconds | 301 | 283 | 290 | 286 | 289 | 285 | 287 | 287 | 272 | 276 | 269 | 254 |

##Read (Realm)

Finding a list of items that its Booking.id contains a value (such as 1, 35, etc.)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 | #11 |
| ---| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Milli Seconds | 2 | 1 | 9 | 5 | 3 | 2 | 1 | 1 | 1 | 1 | 2 |

##Delete (Realm)

Delete all items with type Booking.class (all 10000 items in DB)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 |
| ---| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Milli Seconds | 23 | 22 | 18 | 25 | 27 | 21 | 42 | 21 | 33 | 19 |