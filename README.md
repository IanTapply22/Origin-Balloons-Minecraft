# Origin-Balloons-Minecraft
The Minecraft implementation of the origin balloons logic repository. Easily expandable and easy to use!

## How To Create A Balloon
1. Create a new class that extends `BalloonType` and implement the abstract methods.
   1. `nodeCount` - Number of nodes/models within the balloon.
   2. `distanceBetweenNodes` - Distance between each node/model. This is calculated in blocks and can be a float.
   3. `headNodeOffset, bodyNodeOffset, tailNodeOffset` - The offset of the head, body, and tail nodes from the base `distanceBetweenNodes`. This is calculated in blocks and can be a float.
   4. `maxNodeJointAngle` - The maximum angle that the nodes can rotate to. This is calculated in degrees and can be a float.
   5. `headNodeItem, bodyNodeItem, tailNodeItem` - The item that represents the head, body, and tail nodes. This is a new `Item` object and can be created with the `BalloonModel.createBlankModel` method. **Note: To use custom models you must create a resource pack and you must place the custom model data value from the resource pack in the `customModelData` field when creating the model.**
2. In the `registerBalloons` method in the `OriginBalloons` class, put the new balloon in the `balloonTypeMap` list along with the name.
3. When your plugin is built and reloaded into the server, the balloon will be available to use! Run the command `/spawnballoon <balloon name>` to spawn the balloon and `/destroyballoon` to remove the balloon.
## Video Demo - In Game
https://github.com/IanTapply22/Origin-Balloons-Minecraft/assets/114881396/0cc204cc-bb4c-4e1c-8adf-24fc0516263d
