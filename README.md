## Elevator int the building
This is a simple program that simulates the operation of an elevator. All values about the number of floors, the number of passengers on each floor and the direction of the passenger are generated randomly.

#### Example of work:
```
START
7 floor: [4, 5, 4]
6 floor: [3, 4, 4, 7]
5 floor: [4, 7, 1, 1, 1, 7, 3, 2]
4 floor: [1]
3 floor: [7, 1, 6, 2, 2, 2]
2 floor: [7, 7, 4, 6, 5, 1, 5, 5, 3]
1 floor: [6, 7, 5, 3, 2, 3, 6, 7, 5] Lift:{}

*********** Step 1 ***********
7 floor: [4, 5, 4]
6 floor: [3, 4, 4, 7]
5 floor: [4, 7, 1, 1, 1, 7, 3, 2]
4 floor: [1]
3 floor: [7, 1, 6, 2, 2, 2]
2 floor: [7, 7, 4, 6, 5, 1, 5, 5, 3]
1 floor: [3, 6, 7, 5] Lift:{6 7 5 3 2}
Leave: 0 Entry: 5

*********** Step 2 ***********
7 floor: [4, 5, 4]
6 floor: [3, 4, 4, 7]
5 floor: [4, 7, 1, 1, 1, 7, 3, 2]
4 floor: [1]
3 floor: [7, 1, 6, 2, 2, 2]
2 floor: [7, 4, 6, 5, 1, 5, 5, 3, 5] Lift:{6 7 5 3 7}
1 floor: [3, 6, 7, 5]
Leave: 1 Entry: 1
```