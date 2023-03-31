
package com.example.pwdManager.Model;

import java.net.InetAddress;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "system_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SystemDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String ipAddress;

	@Column(nullable = false)
	private String systemName;

	@Column(nullable = false)
	private LocalDateTime lastRegisteredOn;

	@Column(nullable = false)
	private boolean expired;

	@Column(nullable = false)
	private LocalDateTime lastAccessedOn;

	public void resolveSystemName() {
		try {
			InetAddress address = InetAddress.getByName(ipAddress);
			systemName = address.getHostName();
		} catch (Exception e) {
			// Failed to resolve host name, leave it blank
			systemName = "";
		}
	}


}
