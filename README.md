# RideMusic

### What does it do?
I have always struggled when I wanted to play music in a specific train in a themepark server. This plugin adds your players to a show you can then play music to!
The name of the show is identical to the name of the train, and the players will only be added to the show if the train has the tag "RideMusic".

### How does it work?
- Install RideMusic on your server.
- Spawn a train
- Name the train & give it the tag "RideMusic"

Your train now supports shows, but we're not done yet! You have to play music to that show.
- Add a new resource to the plugin with /ridemusic add <shortcode> <url>. The shortcode can be anything you want, as long as it fits on a sign. The URL has to be a music file supported by MCJukebox.
- Create a Traincarts sign:
```
[!train]
RideMusic
music
SHRTCD
```

- You can replace music with "sound" for a sound effect or "stop" to stop the music. SHRTCD is the shortcode you made when adding the resource.
- All done! When the train drives over the sign, a show with the name of the train will start playing your resource!

### Permissions
- ridemusic.use - Use RideMusic commands
- ridemusic.build - Build signs for RideMusic

### Dependencies
- MCJukebox (shows for music)
- TrainCarts (trains)