package com.wdd.test.bean;

import java.util.Date;

public class Role {
		private int id;
		private String name;
		private String desc;
		private int createUserID;
		private Date createDate;
		private int modifyUserId;
		private Date modifyDate;
		private Date date;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public int getCreateUserID() {
			return createUserID;
		}
		public void setCreateUserID(int createUserID) {
			this.createUserID = createUserID;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public int getModifyUserId() {
			return modifyUserId;
		}
		public void setModifyUserId(int modifyUserId) {
			this.modifyUserId = modifyUserId;
		}
		public Date getModifyDate() {
			return modifyDate;
		}
		public void setModifyDate(Date modifyDate) {
			this.modifyDate = modifyDate;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
}
