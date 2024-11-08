# Test Plan and Evidence / Results of Testing

## Game Description

The project involves the programming of a game.

The player is trapped in a prison and has 120 seconds to escape. They must find navigate the prison, find items and use them to escape.

### Game Features and Rules

The game has the following features and/or rules:

- The player can move north, south, east and west
- The player can search for items
- The player can store items in an inventory
- The player can use stored items to remove blocks
- The player has 120 seconds to escape

---

## Test Plan

The following game features / functionality and player actions will need to be tested:

- Movement
- Picking up Items
- Using items
- Running out of time
- Winning

The following tests will be run against the completed game. The tests should result in the expected outcomes shown.


### Movement

The player should be able to move.

#### Action to test:

The player will press the movement keys to navigate.

#### Expected outcome:

The player is moved to the corresponding location. They will be unable to walk to locations that are not connected or through blockades.

### Picking up Items

The player should be able to pick up items

#### Action to test:

The player will press the search button.

#### Expected outcome: 

The player will get a message that either the room is empty or that they found an item which is then added to their inventory.

### Using items

The player should be able to use items they find

#### Action to test:

The player presses the action button.

#### Expected outcome:

The player will get a message saying they used the item in their inventory, or that they have no items to use in that room.

### Running out of time

The player should get a game over when they run out of time

action to test:

The player runs out of time.

Expected result:

The player gets a game over screen and has to restart.

### Winning

The player should win when they get to the exit

#### Action to test:

The player makes it to the end.

#### Expected result:

The player gets a 'you win' screen ands the game ends.

## Evidence / Results of Testing

### Movement

ACTUAL RESULTS OF TESTING SHOWN HERE

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/cfitzsimmons_waimea_school_nz/EeEmTTFjwzhIoT9GUvHm4qQBnBq_MGxOSmJa7MhFDlaQxw?e=xH5sGJ&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

NOTES REGARDING THE RESULTS HERE

Movement works and is error checked

### Items

ACTUAL RESULTS OF TESTING SHOWN HERE

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/cfitzsimmons_waimea_school_nz/EQ0synAbo2VCg5QSWQItcLQBdCX3_S8DT9eoR8MSBS2jwA?e=XBRz0h&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

NOTES REGARDING THE RESULTS HERE

You can pick up items and see them in inventory

### actions

ACTUAL RESULTS OF TESTING SHOWN HERE

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/cfitzsimmons_waimea_school_nz/EeEmTTFjwzhIoT9GUvHm4qQBnBq_MGxOSmJa7MhFDlaQxw?e=xH5sGJ&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

NOTES REGARDING THE RESULTS HERE

You can use items in the inventory to remove blocks using them.

### Running out of time

ACTUAL RESULTS OF TESTING SHOWN HERE

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/cfitzsimmons_waimea_school_nz/EbP0nappcqhAs3_WLylQFfoBQRJ41kqxsjr_9xj7e4HJag?e=UKhLoE&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

NOTES REGARDING THE RESULTS HERE

The player gets a game over screen upon running out of time and is unable to proceed.

### Winning

ACTUAL RESULTS OF TESTING SHOWN HERE

https://mywaimeaschool-my.sharepoint.com/:v:/g/personal/cfitzsimmons_waimea_school_nz/EfR8MLh9MI1En39-qIHhuDcB71Yy2I8Ym4dXWBwQ-zclNA?e=42xaBm&nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJTdHJlYW1XZWJBcHAiLCJyZWZlcnJhbFZpZXciOiJTaGFyZURpYWxvZy1MaW5rIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXcifX0%3D

NOTES REGARDING THE RESULTS HERE

The player gets a screen telling them they have won after they manage to escape.
