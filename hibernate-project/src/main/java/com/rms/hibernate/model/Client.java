package com.rms.hibernate.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*
 * @NamedQuery(name = "Client.findClientByCarId", query = "select client from Client client where client.Car_client.car_id = :car_id")
 * */
//select e, m.id from SomeEntity e LEFT JOIN e.manyToMany m
@Entity(name = "Client")
@Table(name = "Client")
@NamedQueries({
		@NamedQuery(name = "Client.findClientByClientId", query = "select client from Client client where client.user_id = :user_id"),
		@NamedQuery(name = "Client.findClientAndJob", query = "select client , job from Client client join client.job job"),
		@NamedQuery(name = "Client.getAll", query = "Select client from Client client"),
		@NamedQuery(name = "Client.findClientByCarId", query = "select client from Client client  join client.carList car where car.carId = :car_id") })

public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_SEQ_GEN")
	@SequenceGenerator(name = "CLIENT_SEQ_GEN", sequenceName = "CLIENT_SEQ", allocationSize = 1, initialValue = 1)
	private long user_id;
	private String name;
	private String description;

	@OneToOne
	@JoinColumn(name = "jobId")
	private Job job;

	// one to many
	@OneToMany(mappedBy = "client")
	private List<Post> posts;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Car_client", joinColumns = @JoinColumn(name = "client_Id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
	private List<Car> carList;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(long user_id, String name, String description, List<Post> posts) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.description = description;
		this.posts = posts;
	}

	public Client(String name, String description, List<Post> posts, List<Car> carList) {
		super();
		this.name = name;
		this.description = description;
		this.posts = posts;
		this.carList = carList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Client [user_id=" + user_id + ", name=" + name + ", description=" + description + "]";
	}

}
