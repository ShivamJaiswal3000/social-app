package com.springboot.socialapp.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String gender;
	private List<Integer>followers = new ArrayList<>();
	private List<Integer>followings = new ArrayList<>();
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPost= new ArrayList<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public User(Integer id, String fName, String lName, String email, String password, String gender,
			List<Integer> followers, List<Integer> followings) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.followers = followers;
		this.followings = followings;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}




	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public List<Integer> getFollowers() {
		return followers;
	}



	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}



	public List<Integer> getFollowings() {
		return followings;
	}



	public void setFollowings(List<Integer> followings) {
		this.followings = followings;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", fName=" + fName + ", lName=" + lName + ", email=" + email + ", password="
				+ password + ", gender=" + gender + ", followers=" + followers + ", followings=" + followings
				+ ", getId()=" + getId() + ", getGender()=" + getGender() + ", getFollowers()=" + getFollowers()
				+ ", getFollowings()=" + getFollowings() + ", getfName()=" + getfName() + ", getlName()=" + getlName()
				+ ", getEmail()=" + getEmail() + ", getPassword()=" + getPassword() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public List<Post> getSavedPost() {
		return savedPost;
	}



	public void setSavedPost(List<Post> savedPost) {
		this.savedPost = savedPost;
	}
	
	
}
