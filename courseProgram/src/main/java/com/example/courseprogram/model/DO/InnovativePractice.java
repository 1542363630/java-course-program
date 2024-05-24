package com.example.courseprogram.model.DO;
import com.example.courseprogram.Exception.AllowedValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

/**
 * <p>InnovativePractice 创新实践信息类
 * <p>Integer innovativeId 主键id
 * <p>String studentName 学生们的姓名
 * <p>String activityName 活动名称
 * <p>String teacherName 指导老师的姓名
 * <p>String achievement 成果
 * <p>String beginTime 开始时间
 * <p>String endTime 结束时间
 * <p>String type 类型
 * <p>String file 文件路径
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "innovative_practice")
@Entity
public class InnovativePractice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer innovativeId;

    private String studentName;

    @NotBlank(message = "活动名称不能为空")
    private String activityName;

    private String teacherName;

    /**
     * 成果：
     * <p>特等奖、一等奖、二等奖、三等奖、优秀奖</p>
     * <p>金奖、银奖、铜奖、参与</p>
     * <p>发明专利、实用新型专利、外观设计专利</p>
     * <p>志愿者、负责人</p>
     * <p>校级优秀团队（队长）、校级优秀团队（队员）</p>
     * <p>其他</p>
     */
    @NotBlank(message = "成果不能为空")
    @AllowedValues(allowedValues = {
            "特等奖","一等奖","二等奖","三等奖","优秀奖",
            "金奖","银奖","铜奖","参与",
            "发明专利","实用新型专利","外观设计专利",
            "志愿者","负责人",
            "校级优秀团队（队长）","校级优秀团队（队员）",
            "其他"
    },message = "成果必须为(特等奖、一等奖、二等奖、三等奖、优秀奖、" +
            "金奖、银奖、铜奖、参与、" +
            "发明专利、实用新型专利、外观设计专利、" +
            "志愿者、负责人、" +
            "校级优秀团队（队长）、校级优秀团队（队员）、" +
            "其他)中的一个")
    private String achievement;

    @NotBlank(message = "开始时间不能为空")
    private String beginTime;

    @NotBlank(message = "结束时间不能为空")
    private String endTime;

    /**
     * 创新实践类型：
     * <p>社会实践、学科竞赛、科技成果、培训讲座、创新项目、校外实习、志愿服务</p>
     */
    @NotBlank(message = "创新实践类型不能为空")
    @AllowedValues(allowedValues = {"社会实践","学科竞赛","科技成果","培训讲座","创新项目","校外实习","志愿服务"},message = "创新实践类型必须为(社会实践,学科竞赛,科技成果,培训讲座,创新项目,校外实习,志愿服务)中的一个")
    private String type;

    private String file;
}
