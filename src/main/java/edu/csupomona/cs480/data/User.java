package edu.csupomona.cs480.data;

import java.util.Date;
import com.google.common.base.MoreObjects;

/**
 * The basic user object.
 */
public class User {

	/** The unique user Id */
    private String id;
    /** The unique user Id */
    private String name;
    /** The unique user Id */
    private String major;
    /** The timestamp when the user is being created */
    private String creationTime = new Date(System.currentTimeMillis()).toString();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	//Pedro's Guava method
	@Override
    public String toString() {
        return MoreObjects.toStringHelper(User.class)
            .add("id", this.id)
            .add("name", this.name)
            .add("major", this.major)
            .add("creation time", this.creationTime)
            .toString();
    }
}
