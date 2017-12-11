package com.routineapp.aristaik.routine20;


import java.io.Serializable;

public class Period implements Serializable {

    private String Start_time;
    private String end_time;
    private String course_code;
    private String sub_name;
    private String teacher_name;

    public String getStart_time() {
        return Start_time;
    }

    public void setStart_time(String Start_time) {
        this.Start_time = Start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Period() {
    }

    public Period(String Start_time, String end_time, String course_code, String sub_name, String teacher_name) {
        this.Start_time = Start_time;
        this.end_time = end_time;
        this.course_code = course_code;
        this.sub_name = sub_name;
        this.teacher_name = teacher_name;
    }

}
