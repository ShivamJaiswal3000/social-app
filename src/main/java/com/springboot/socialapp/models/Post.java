package com.springboot.socialapp.models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String caption;
	private String image;
	private String video;
	@ManyToOne
	private User user;
	@ManyToMany
	private List<User> liked= new ArrayList<>();
	private LocalDateTime createdAt;
	
	@OneToMany
	private List<Comment> comments= new ArrayList<>();
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTimeAgo() {
	    LocalDateTime currentTime = LocalDateTime.now();
	    Duration duration = Duration.between(createdAt, currentTime);

	    long minutes = duration.toMinutes();
	    long hours = duration.toHours();
	    long days = duration.toDays();
	    
	    if (minutes < 60) {
	        return minutes + " minutes ago";
	    } else if (hours < 24) {
	        return hours + " hours ago";
	    } else if (days == 1) {
	        return "1 day ago";
	    } else if (days <= 6) {
	        return days + " days ago";
	    } else {
	        if (days < 30) {
	            long weeks = days / 7;
	            return weeks + (weeks == 1 ? " week ago" : " weeks ago");
	        } else {
	            long months = days / 30;
	            return months + (months == 1 ? " month ago" : " months ago");
	        }
	    }
	}

	public String getFormattedDate() {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    return createdAt.format(formatter);
	}
	public Post(Integer id, String caption, String image, String video, User user, List<User> liked,
			LocalDateTime createdAt, List<Comment> comments) {
		super();
		this.id = id;
		this.caption = caption;
		this.image = image;
		this.video = video;
		this.user = user;
		this.liked = liked;
		this.createdAt = createdAt;
		this.comments = comments;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getLiked() {
		return liked;
	}
	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
}
