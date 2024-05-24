package com.example.courseprogram.model.DO;

import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>HonorInfo 荣誉信息类
 * <p>Integer honorId 主键id
 * <p>Student student 对应的学生
 * <p>String level 荣誉等级
 * <p>String honorTime 获得荣誉的时间
 * <p>String honorFrom 授予荣誉的单位
 * <p>String honorName 荣誉名称
 * <p>String type 荣誉类别
 * <p>String file 证明文件的路径
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "honor_info")
@Entity
public class HonorInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer honorId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @NotBlank(message = "荣誉类型不能为空")
    private String type;

    /**
     * 荣誉等级：
     * <p>班级、年级、院级、校级、市级、省级、国家级、世界级</p>
     */
    @NotBlank(message = "荣誉等级不能为空")
    @AllowedValues(allowedValues = {"班级","年级","院级","校级","市级","省级","国家级","世界级"
            },message = "荣誉等级必须为(班级、年级、院级、校级、市级、省级、国家级、世界级)中的一个")
    private String level;
    @NotBlank(message = "获得荣誉的时间不能为空")
    private String honorTime;
    @NotBlank(message = "授予单位不能为空")
    private String honorFrom;
    @NotBlank(message = "荣誉名称不能为空")
    private String honorName;

    private String file;
}
