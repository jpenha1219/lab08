package model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Access {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private long idRole;
	@Persistent
	private String nameRole;
	@Persistent
	private long idResource;
	@Persistent
	private String nameResource;
	@Persistent
	private boolean state;
	public Access(long idRole, String nameRole, long idResource, String nameResource, Boolean state) {
		super();
		this.idRole = idRole;
		this.nameRole = nameRole;
		this.idResource = idResource;
		this.nameResource = nameResource;
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public long getIdRole() {
		return idRole;
	}
	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	public long getIdResource() {
		return idResource;
	}
	public void setIdResource(long idResource) {
		this.idResource = idResource;
	}
	public String getNameResource() {
		return nameResource;
	}
	public void setNameResource(String nameResource) {
		this.nameResource = nameResource;
	}
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}	
}