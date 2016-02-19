# DimensionGuard
The ultimate dimension and mystcraft age manager
<br/>
Originally developed for use on the PreludeCraft server, it has developed into more then just a Mystcraft dimension manager, but an all around dimension manager compatible with almost everything

# Features
- Dimension claiming
- Owner and Member permission system
- Limit on how many dimensions a player can have

# Commands
<b>/dg claim</b> - Claim the dimension you are in<br/>
<b>/dg delete</b> - Delete the dimension you are in<br/>
<b>/dg forcedelete \<player\></b> - Delete the dimension that the specified player is in. <b>Only usable through console</b><br/>
<b>/dg info</b> - Get info about the dimension you are in<br/>
<b>/dg kick \<player\></b> - Kick a player from a dimension<br/>
<b>/dg list</b> - List all the dimensions registered<br/>
<b>/dg listown</b> - List the dimensions that you are part of<br/>
<b>/dg spawn</b> - Teleport to world spawn<br/>
<b>/dg tpx \<dimension\></b> - Teleport to spawn of world specified<br/>
<b>/dg addowner \<player\></b> - Add an owner to the dimension you are in<br/>
<b>/dg addmember \<player\></b> - Add a member to the dimension you are in<br/>
<b>/dg removeowner \<player\></b> - Remove an owner from the dimension you are in<br/>
<b>/dg removemember \<player\></b> - Remove a member from the dimension you are in<br/>
<b>/dg reload</b> - Reload the plugin configs (including dimension data)<br/>

# Permissions
<b>worldmanager.overrideowner</b> - Override dimension owner, useful for non-opped players<br/>
<b>worldmanager.overridemember</b> - Override dimension member, useful for non-opped players<br/>
<b>worldmanager.reload</b> - The ability to reload plugin configs, needed for /dg reload<br/>
<b>worldmanager.tpx</b> - The ability to teleport to dimensions, needed for /dg tpx<br/>
<b>worldmanager.tp.others</b> - The ability to teleport others to spawn<br/>
<b>worldmanager.kick</b> - The ability to kick players from a dimension, this is given ti dimension owners by default<br/>