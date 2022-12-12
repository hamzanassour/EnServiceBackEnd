package com.example.enserviceback.mapper;

import com.example.enserviceback.dto.TeacherDto;
import com.example.enserviceback.entity.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

   Teacher teacherFromTeacherDto(TeacherDto teacherDto);

}
