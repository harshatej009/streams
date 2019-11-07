package harsh.rane;

import org.springframework.stereotype.Component;

@Component
public class Address {
	
	private int patientid;
	private String city;
	private String email_address;
	public int getPatientid() {
		return patientid;
	}
	public String getCity() {
		return city;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	@Override
	public String toString() {
		return "Address [patientid=" + patientid + ", city=" + city + ", email_address=" + email_address + "]";
	}
	

}
