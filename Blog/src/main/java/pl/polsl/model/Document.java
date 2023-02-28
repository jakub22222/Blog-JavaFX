/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.model;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 * Class represents post on a blog
 * @author Jakub Hoś
 * @version 1.3
 */
public class Document {
    /**
     * Date and time when document was added
     */
     LocalDateTime entryDateTime;	
     /**
      * Content of document
      */
     String content;
     /**
      * String contains the relative time
      */
     String timeAgo;
     /**
      * Authors id
      */
     int author;
    /**
     * Returns document entryDateTime
     * @return Date and time when document was added
     */
    public LocalDateTime getEntryDateTime() {
        return entryDateTime;
    }
    /**
     * Returns document content
     * @return Document content
     */
    public String getContent() {
        return content;
    }
    /**
     * Constructor, sets content and entryDateTime of document
     * @param content Content of document
     * @param id Authors id
     */
    public Document(String content, int id) {
        author = id;
        this.content = content;  
        entryDateTime = LocalDateTime.now();
    }
    /**
     * Constructor, sets content and entryDateTime of document
     * @param content Content of document
     * @param id Authors id
     * @param dateTime Entry date and time
     */
    public Document(String content, int id, LocalDateTime dateTime) {
        author = id;
        this.content = content;  
        entryDateTime = dateTime;
    }
    
    public int getAuthor() {
        return author;
    }

    public String getTimeAgo() {
        timeAgo = timeAgoCalc(LocalDateTime.now(),entryDateTime);
        return timeAgo;
    }
    

    public void setContent(String content) {
        this.content = content;
    }
    /**
     * Method returns relative time from now to date of entry time
     * Coppied from www.tipstocode.com and modified a bit
     * @param currentDate Current date time 
     * @param pastDate Date and time when document was added
     * @return 
     */
    public static String timeAgoCalc(LocalDateTime currentDate, LocalDateTime pastDate) {
    long milliSecPerMinute = 60 * 1000; //Milliseconds Per Minute
    long milliSecPerHour = milliSecPerMinute * 60; //Milliseconds Per Hour
    long milliSecPerDay = milliSecPerHour * 24; //Milliseconds Per Day
    long milliSecPerMonth = milliSecPerDay * 30; //Milliseconds Per Month
    long milliSecPerYear = milliSecPerDay * 365; //Milliseconds Per Year
    //Difference in Milliseconds between two dates
    long msExpired = pastDate.until(currentDate, ChronoUnit.MILLIS);
    //Second or Seconds ago calculation
    if (msExpired < milliSecPerMinute) {
      if (Math.round(msExpired / 1000) == 1) {
        return String.valueOf(Math.round(msExpired / 1000)) + " second ago";
      } else {
        return String.valueOf(Math.round(msExpired / 1000) + " seconds ago");
      }
    }
    //Minute or Minutes ago calculation
    else if (msExpired < milliSecPerHour) {
      if (Math.round(msExpired / milliSecPerMinute) == 1) {
        return String.valueOf(Math.round(msExpired / milliSecPerMinute)) + " minute ago";
      } else {
        return String.valueOf(Math.round(msExpired / milliSecPerMinute)) + " minutes ago";
      }
    }
    //Hour or Hours ago calculation
    else if (msExpired < milliSecPerDay) {
      if (Math.round(msExpired / milliSecPerHour) == 1) {
        return String.valueOf(Math.round(msExpired / milliSecPerHour)) + " hour ago";
      } else {
        return String.valueOf(Math.round(msExpired / milliSecPerHour)) + " hours ago";
      }
    }
    //Day or Days ago calculation
    else if (msExpired < milliSecPerMonth) {
      if (Math.round(msExpired / milliSecPerDay) == 1) {
        return String.valueOf(Math.round(msExpired / milliSecPerDay)) + " day ago";
      } else {
        return String.valueOf(Math.round(msExpired / milliSecPerDay)) + " days ago";
      }
    }
    //Month or Months ago calculation 
    else if (msExpired < milliSecPerYear) {
      if (Math.round(msExpired / milliSecPerMonth) == 1) {
        return String.valueOf(Math.round(msExpired / milliSecPerMonth)) + "  month ago";
      } else {
        return String.valueOf(Math.round(msExpired / milliSecPerMonth)) + "  months ago";
      }
    }
    //Year or Years ago calculation 
    else {
      if (Math.round(msExpired / milliSecPerYear) == 1) {
        return String.valueOf(Math.round(msExpired / milliSecPerYear)) + " year ago";
      } else {
        return String.valueOf(Math.round(msExpired / milliSecPerYear)) + " years ago";
      }
  }
}
    
}
