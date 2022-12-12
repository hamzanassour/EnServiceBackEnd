package com.example.enserviceback.mapper;

import com.example.enserviceback.dto.StudentDto;
import com.example.enserviceback.entity.Student;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student studentFromStudentDto(StudentDto studentDto);


}
