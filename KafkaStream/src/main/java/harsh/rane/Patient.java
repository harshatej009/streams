package harsh.rane;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.stereotype.Component;

@Component
public class Patient {

	private String id;
	private String name;
	private String surname;
	private String email;
	private String city1;
	private String city2;
	
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity1() {
		return city1;
	}
	public void setCity1(String city1) {
		this.city1 = city1;
	}
	public String getCity2() {
		return city2;
	}
	public void setCity2(String city2) {
		this.city2 = city2;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + ", city1=" + city1
				+ ", city2=" + city2 + "]";
	}
	
	
}
