package com.example.Medinexus.Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Medinexus.Model.Doctor;
import com.example.Medinexus.Model.ERole;
import com.example.Medinexus.Model.Nurse;
import com.example.Medinexus.Model.Patient;
import com.example.Medinexus.Model.Pharmacist;
import com.example.Medinexus.Model.Role;
import com.example.Medinexus.Model.User;
import com.example.Medinexus.Payload.Request.LoginRequest;
import com.example.Medinexus.Payload.Request.SignUpRequest;
import com.example.Medinexus.Payload.Response.JwtResponse;
import com.example.Medinexus.Payload.Response.MessageResponse;
import com.example.Medinexus.Repository.DoctorRepository;
import com.example.Medinexus.Repository.NurseRepository;
import com.example.Medinexus.Repository.PatientRepository;
import com.example.Medinexus.Repository.PharmacistRepository;
import com.example.Medinexus.Repository.RoleRepository;
import com.example.Medinexus.Repository.UserRepository;
import com.example.Medinexus.Security.JWT.JwtUtils;
import com.example.Medinexus.Security.Services.UserDetailsImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    NurseRepository nurseRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    PharmacistRepository pharmacistRepository;

	@Autowired
	PasswordEncoder encoder;
    
    @Autowired
	JwtUtils jwtUtils;

    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Encode the password and create a new user
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())
        );

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Role must be specified!"));
        } else {
            strRoles.forEach(role -> {
                switch (role.toLowerCase()) {
                    case "admin":
                        roles.add(roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role ADMIN not found.")));
                        break;
                    case "doctor":
                        roles.add(roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role DOCTOR not found.")));
                        break;
                    case "nurse":
                        roles.add(roleRepository.findByName(ERole.ROLE_NURSE)
                                .orElseThrow(() -> new RuntimeException("Error: Role NURSE not found.")));
                        break;
                    case "pharmacist":
                        roles.add(roleRepository.findByName(ERole.ROLE_PHARMACIST)
                                .orElseThrow(() -> new RuntimeException("Error: Role PHARMACIST not found.")));
                        break;
                    case "patient":
                        roles.add(roleRepository.findByName(ERole.ROLE_PATIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role PATIENT not found.")));
                        break;
                    default:
                        throw new RuntimeException("Error: Invalid role specified.");
                }
            });
        }

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

        // Save role-specific details
        if (strRoles.contains("doctor")) {
            Doctor doctor = new Doctor();
            doctor.setUserId(savedUser.getId()); // Link to the User collection
            doctor.setFullName(signUpRequest.getFullName());
            doctor.setGender(signUpRequest.getGender());
            doctor.setDateOfBirth(signUpRequest.getDateOfBirth());
            doctor.setSpecialization(signUpRequest.getSpecialization());
            doctor.setMedicalLicenseNumber(signUpRequest.getMedicalLicenseNumber());
            doctor.setYearsOfExperience(signUpRequest.getYearsOfExperience());
            doctor.setPhoneNumber(signUpRequest.getPhoneNumber());
            doctor.setClinicAddress(signUpRequest.getClinicAddress());
            doctor.setMedicalCertificationFilePath(signUpRequest.getMedicalCertificationFilePath());
            doctor.setProfilePictureFilePath(signUpRequest.getProfilePictureFilePath());
            doctor.setAvailableDays(signUpRequest.getAvailableDays());
            doctorRepository.save(doctor);
        } else if (strRoles.contains("nurse")) {
            Nurse nurse = new Nurse();
            nurse.setUserId(savedUser.getId()); // Link to the User collection
            nurse.setFullName(signUpRequest.getFullName());
            nurse.setGender(signUpRequest.getGender());
            nurse.setDateOfBirth(signUpRequest.getDateOfBirth());
            nurse.setSpecialization(signUpRequest.getSpecialization());
            nurse.setMedicalLicenseNumber(signUpRequest.getMedicalLicenseNumber());
            nurse.setYearsOfExperience(signUpRequest.getYearsOfExperience());
            nurse.setPhoneNumber(signUpRequest.getPhoneNumber());
            nurse.setAddress(signUpRequest.getAddress());
            nurse.setMedicalCertificationFilePath(signUpRequest.getMedicalCertificationFilePath());
            nurse.setAvailableDays(signUpRequest.getAvailableDays());
            nurseRepository.save(nurse);
        } else if (strRoles.contains("patient")) {
            Patient patient = new Patient();
            patient.setUserId(savedUser.getId()); // Link to the User collection
            patient.setFullName(signUpRequest.getFullName());
            patient.setGender(signUpRequest.getGender());
            // patient.setDateOfBirth(signUpRequest.getDateOfBirth());
            patient.setBloodGroup(signUpRequest.getBloodGroup());
            patient.setAllergies(signUpRequest.getAllergies());
            patient.setExistingMedicalConditions(signUpRequest.getExistingMedicalConditions());
            patient.setPhoneNumber(signUpRequest.getPhoneNumber());
            patient.setAddress(signUpRequest.getAddress());
            patient.setEmergencyContactName(signUpRequest.getEmergencyContactName());
            patient.setEmergencyContactNumber(signUpRequest.getEmergencyContactNumber());
            patient.setEmergencyContactRelationship(signUpRequest.getEmergencyContactRelationship());
            patientRepository.save(patient);
        } else if (strRoles.contains("pharmacist")) {
            Pharmacist pharmacist = new Pharmacist();
            pharmacist.setUserId(savedUser.getId()); // Link to the User collection
            pharmacist.setFullName(signUpRequest.getFullName());
            pharmacist.setGender(signUpRequest.getGender());
            pharmacist.setDateOfBirth(signUpRequest.getDateOfBirth());
            pharmacist.setMedicalLicenseNumber(signUpRequest.getMedicalLicenseNumber());
            pharmacist.setYearsOfExperience(signUpRequest.getYearsOfExperience());
            pharmacist.setPharmacyName(signUpRequest.getPharmacyName());
            pharmacist.setSpecialization(signUpRequest.getSpecialization());
            pharmacist.setPhoneNumber(signUpRequest.getPhoneNumber());
            pharmacist.setAddress(signUpRequest.getAddress());
            pharmacist.setMedicalCertificationFilePath(signUpRequest.getMedicalCertificationFilePath());
            pharmacist.setDrugList(signUpRequest.getDrugList());
            pharmacistRepository.save(pharmacist);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}