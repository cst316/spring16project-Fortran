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
public class Main {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MediaLibrary myLib = new MediaLibrary();
        boolean media = myLib.add(new MediaDescription("Music", "Routine", "Silent Siren", "Silent Siren", "J-Rock", "Routine.mp3"));
        media = myLib.add(new MediaDescription("Music", "Elvis", "AOA", "", "K-Pop", "Elvis.mp3"));
        media = myLib.add(new MediaDescription("Video", "Have a Nice Day", "World Order", "", "Dance", ".mp4"));
        media = myLib.add(new MediaDescription("Music", "Take Me to the Top", "One Ok Rock", "35xxxv", "J-Rock", "TakeMetotheTop.mp3"));
        myLib.getTitles();
        myLib.getMusicTitles();
        media = myLib.remove("Elvis");
        myLib.getMusicTitles();
        myLib.get("Routine");
        myLib.getVideoTitles();
    }
    
}
