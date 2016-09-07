After Several Insertion for Realm DB, following results achieved.
First, 10,000 objects created.
Second, These objects inserted to Realm db. The process took:

##Create (Realm)

Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 | #11 | #12
--- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---
Seconds | 301 | 283 | 290 | 286 | 289 | 285 | 287 | 287 | 272 | 276 | 269 | 254

##Read (Realm)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 | #11 |
|---|
| Milli Seconds | 2 | 1 | 9 | 5 | 3 | 2 | 1 | 1 | 1 | 1 | 2 |

##Delete (Realm)

| Attempt | #1 | #2 | #3 | #4 | #5 | #6 | #7 | #8 | #9 | #10 | #11 |
|---|
| Milli Seconds | 23 | 22 | 18 | 25 |  |  |  |  |  |  |  |