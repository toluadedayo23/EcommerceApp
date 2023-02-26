package com.tutorials.ecommerceapp.dto.auth;

import com.tutorials.ecommerceapp.model.ERole;
import com.tutorials.ecommerceapp.validation.Password;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "username must not be empty")
    @Size(min = 2, max = 15, message = "username must be between 2 and 15 characters")
    private String username;

    @NotBlank(message = "firstname must not be empty")
    @Size(min = 2, max = 15, message = "firstname must be between 2 and 15 characters")
    private String firstName;

    @NotBlank(message = "lastname must not be empty")
    @Size(min = 2, max = 15,message = "lastname must be between 2 and 15 characters" )
    private String lastName;


    @NotBlank(message = "Email is compulsory, please provide a valid one")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "please provide a valid email")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Password
    private String password;

    private Set<ERole> roles;

}
