/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medialibrary;

/**
 *
 * @author Quy
 */

import java.util.*;

public class MediaLibrary{

    ArrayList<MediaDescription> lib = new ArrayList<>();
    
    public boolean add(MediaDescription aClip) {
        boolean bool = false;
        
        if (lib.add(aClip)) {
            bool = true;
        }
        System.out.println(bool);
        return bool;
    }
    
    public boolean remove(String aTitle) {
        boolean bool = false;
        
        for (int i = 0; i <= lib.size() - 1; i++) {
            if (lib.get(i).getTitle().equals(aTitle)) {
                lib.remove(i);
                bool = true;
            }
        }  
        return bool;
    }
    
    public MediaDescription get(String aTitle) {
        MediaDescription media = new MediaDescription();
        
        for (int i = 0; i <= lib.size() - 1; i++) {
            if (lib.get(i).getTitle().equals(aTitle))
                media = (lib.get(i));
        }
        System.out.println(media);
        return media;
    }
    
    public String[] getTitles() {
        String[] titleNames = new String[lib.size()];
        for (int i = 0; i <= lib.size() - 1; i++) {
            titleNames[i] = lib.get(i).getTitle();
        }
        
        System.out.println(Arrays.toString(titleNames));
        return titleNames;
    }
    
    public String[] getMusicTitles() {
        int count = 0;
        ArrayList<MediaDescription> temp = new ArrayList<>();

        for (int i = 0; i <= lib.size() - 1; i++) {
            if (lib.get(i).getMediaType().equals("Music")) {
                count++;
                temp.add(lib.get(i));  
            }
        }
        
        String[] musicTitles = new String[count];
        for (int j = 0; j <= temp.size() - 1; j++) {
            if (temp.get(j).getMediaType().equals("Music")) {
                musicTitles [j] = temp.get(j).getTitle();
            }
        }
        System.out.println(Arrays.toString(musicTitles));
        return musicTitles;
    }
    
    public String[] getVideoTitles() {
        int count = 0;
        ArrayList<MediaDescription> temp = new ArrayList<>();

        for (int i = 0; i <= lib.size() - 1; i++) {
            if (lib.get(i).getMediaType().equals("Video")) {
                count++;
                temp.add(lib.get(i));  
            }
        }
        
        String[] videoTitles = new String[count];
        for (int j = 0; j <= temp.size() - 1; j++) {
            if (temp.get(j).getMediaType().equals("Video")) {
                videoTitles [j] = temp.get(j).getTitle();
            }
        }
        System.out.println(Arrays.toString(videoTitles));
        return videoTitles;
        
    }
}
