package com.qa.api.pojo;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactsCredentials {

	private String email;
	private String password;

}
