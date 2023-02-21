//package com.oauth2_project.model;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "users")
//public class UserInfo implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name = "id", length = 25)
//	private Integer id;
//
//	@Column(name = "username", length = 50)
//	private String userName;
//
//	@Column(name = "password", length = 800)
//	private String password;
//
//	@Column(name = "role", length = 50)
//	private String role;
//
//	@Column(name = "enabled")
//	private short enabled;
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}
//
//	public short getEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(short enabled) {
//		this.enabled = enabled;
//	}
//
//	@Override
//	public String toString() {
//		return String.format("UserInfo [id=%s, userName=%s, password=%s, role=%s, enabled=%s]", id, userName, password,
//				role, enabled);
//	}
//
//}
