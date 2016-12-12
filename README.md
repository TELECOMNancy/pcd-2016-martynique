Martynique !
===================


Welcome guys for a intensive programming week! We have to develop a desktop app interfacing with Youtube.


#Before starting

First of all, install all softwares you need to work. Minimum requirements are:
 - an IDE ([eclipse](https://eclipse.org/downloads/), [IntelliJ IDEA](https://www.jetbrains.com/idea/), [netBeans](https://netbeans.org/downloads/))
 - [Git](https://git-scm.com/downloads) (surprising isn't it ?)
 - [JUnit4](http://junit.org/junit4/) ([Who will make tests?](http://www.commitstrip.com/shop/45-thickbox_default/affiche-tester-c-est-douter.jpg))
 - [SceneBuilder](http://gluonhq.com/labs/scene-builder/) if we choose javaFX
 - A build manager (maven, gradle) if we have time


Ok, here we are! Be sure you can clone the repo (use [SSH keys](https://help.github.com/articles/generating-an-ssh-key/) to be more faster).

#Config
the ID clients OAuth 2.0 is stored in *src/main/resources/secrets.json*

	{
		"installed": {
			"client_id": "XXXXX",
		    "client_secret": "XXXXX"
		}
	}

The API key is stored in *src/main/resources/youtube.properties*
	
	youtube.apikey=XXXXX


# Roles

@Mcdostone: Project manager
@MartyEz: Crazy coder
@Answermouth: User Interface Developer and test developper
@Minous: User Interface Developer and test developper

# Expected features


1. Searching a video using the Youtube API
2. Watching a video hosted on Youtube
3. Getting the list of videos depending on a user query
4. Controlling the video player (play, pause, volume, fullscreen ...)
5. Watching an offline video
6. Saving favorites videos
7. Creating a playlist
8. Improving user experience thanks to advanced thumbnails
9. Suggesting videos the user depending on its criteria
10. Making navigation more easier for the user
11. Authentificating the user on Youtube
12. Commenting or liking  a video
13. Displaying more information about a video (comments, description)



#Day 1#Monday

features
----

Minious and Answermouth: features 2 and 4
MartyEz and Mcdostone: features 1 and 9


# Some resources
 - [jfoenix](http://www.jfoenix.com/),  implementation of Google Material Design using Java components.
 - [Example of JavaFX app](https://github.com/Mcdostone/sort-my-photos)

#authors

@MartyEz, Lucas MARTINEZ

@Answermouth, Ansel GAMET

@Minious, Eliot GODARD

@Mcdostone, Yann PRONO

