package Behavioral_Design_Pattern;

import java.util.*;

//================== Video Class representing single Video =====================
class Video {
    private String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }
}

// ===================== Youtube playlist class =================================
class YoutubePlayList {
    private List<Video> videos = new ArrayList<>();

    // Method to add video to the youtube playlist
    public void addVideo(Video video) {
        videos.add(video);
    }

    // Method to expose internal Video playlist
    // public List<Video> getVideos() {
    //     return this.videos;
    // }

    //Refined Approach - Instead of exposing the list, return an iterator
    public YoutubePlaylistIterator iterator(){
        return new YoutubePlaylistIterator(videos);
    }
}

// ======================= Iterator Interface ====================================
interface PlaylistIterator {
    boolean hasNext();

    Video Next();
}

// ================== Concrete Implementation of Iterator Interface ===============
class YoutubePlaylistIterator implements PlaylistIterator {
    List<Video> videos;
    int position;

    YoutubePlaylistIterator(List<Video> videos){
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video Next() {
        return hasNext() ? videos.get(position++) : null;
    }

}

public class IteratorPattern {
    public static void main(String[] args) {
        YoutubePlayList playList = new YoutubePlayList();
        playList.addVideo(new Video("Low Level Design"));
        playList.addVideo(new Video("Creational Design Pattern"));
        playList.addVideo(new Video("Structural Design Pattern"));
        playList.addVideo(new Video("Behavioural Design Pattern"));

        //YoutubePlaylistIterator it = new YoutubePlaylistIterator(playList.getVideos());
        //calling post refined logic addition
        YoutubePlaylistIterator it = playList.iterator();
        while (it.hasNext()) {
            System.out.println(it.Next().getTitle());
        }
    }
}
