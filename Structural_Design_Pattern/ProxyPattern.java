package Structural_Design_Pattern;

import java.util.*;

// class VideoDownloader{
//     //caching logic --> but this violates SRP--> so move caching logic to diff layer
//     public void download(){
//         System.out.println("Download video from original source");
//     }
// }

interface downloader {
    String download(String videoURL);
}

class VideoDownloader implements downloader {
    @Override
    public String download(String videoURL) {
        System.out.println("Downloading video from URL: " + videoURL);
        return "Video content from " + videoURL;
    }
}

class CacheVideo implements downloader {
    VideoDownloader videoDownloader;
    HashMap<String, String> cache;

    public CacheVideo() {
        this.videoDownloader = new VideoDownloader();
        this.cache = new HashMap<>();
    }

    @Override
    public String download(String videoURL) {
        if (cache.containsKey(videoURL)) {
            System.out.println("Returning cached video for: " + videoURL);
            return cache.get(videoURL);
        }

        System.out.println("Cache miss. Downloading...");
        String video = videoDownloader.download(videoURL);
        cache.put(videoURL, video);
        return video;
    }

}

public class ProxyPattern {
    public static void main(String[] args) {
        downloader cacheVideoDownloader = new CacheVideo();
        System.out.println("User 1 tries to download the video.");
        cacheVideoDownloader.download("https://video.com/proxy-pattern");

        System.out.println();

        System.out.println("User 2 tries to download the same video again.");
        cacheVideoDownloader.download("https://video.com/proxy-pattern");
    }
}
