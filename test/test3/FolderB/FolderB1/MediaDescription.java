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
public class MediaDescription {
    private String mediaType;
    private String title;
    private String author;
    private String album;
    private String genre;
    private String filename;
    
    public MediaDescription() {
        mediaType = "";
        title = "";
        author = "";
        album = "";
        genre = "";
        filename = "";
    }
    
    public MediaDescription(String mediaType, String title, String author,
                    String album, String genre, String filename)
    {
        this.mediaType = mediaType;
        this.title = title;
        this.author = author;
        this.album = album;
        this.genre = genre;
        this.filename = filename;
    }
    
    @Override
    public String toString() {
        return "MediaType: " + this.mediaType + ", "
                + "Title: " + this.title + ", "
                + "Author: " + this.author + ", "
                + "Album: " + this.album + ", "
                + "Genre: " + this.genre + ", "
                + "FileName: " + this.filename;
    }
        
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setAlbum(String album) {
        this.album = album;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public void setFileName(String filename) {
        this.filename = filename;
    }
 
    public String getMediaType() {
        return mediaType;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getAlbum() {
        return album;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public String getFileName() {
        return filename;
    }
}