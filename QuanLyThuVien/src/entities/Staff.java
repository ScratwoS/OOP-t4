package entities;

public class Staff {
	private int id, role,activated;
	private String username, password,email,fullname,description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getActivated() {
		return activated;
	}
	public void setActivated(int activated) {
		this.activated = activated;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Staff(int id, int role, int activated, String username, String password, String email, String fullname,
			String description) {
		super();
		this.id = id;
		this.role = role;
		this.activated = activated;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", fullname=" + fullname + ", role=" + role + ", activated=" + activated + ", description="
				+ description + "]";
	}
	public Staff() {
		// TODO Auto-generated constructor stub
	}
	
}
