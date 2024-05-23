package com.example.courseprogram.model.DO;

import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>DailyActivity 日常活动类
 * <p>Integer activityId 主键id
 * <p>String studentName 学生们的姓名
 * <p>String beginTime 活动开始时间
 * <p>String endTime 活动结束时间
 * <p>String activityType 活动类型
 * <p>String location 活动地点
 * <p>String activityName 活动名称
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@TableName("daily_activity")
@Table(name = "daily_activity")
@Entity
public class DailyActivity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;

    private String studentName;
    @NotBlank(message = "活动开始时间不能为空")
    private String beginTime;
    @NotBlank(message = "活动结束时间不能为空")
    private String endTime;
    @NotBlank(message = "活动名称不能为空")
    private String activityName;

    /**
     * 活动类型：
     * <p>聚会、旅游、文艺演出、体育活动</p>
     */
    @NotBlank(message = "活动类型不能为空")
    @AllowedValues(allowedValues = {"聚会","旅游","文艺演出","体育活动"},message = "活动类型必须为(聚会、旅游、文艺演出、体育活动)中的一个")
    private String activityType;
    @NotBlank(message = "活动地点不能为空")
    private String location;
}
