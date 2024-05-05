package com.LeaveApplication.Leave.Model;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="audit_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditid;

    @OneToOne
    @JoinColumn(name = "userid", nullable = false,referencedColumnName = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Users userid;

    @OneToOne
    @JoinColumn(name = "requestid", nullable = false,referencedColumnName = "request_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LeaveRequest requestsid;

    @Column
    private LocalDateTime M1;

    @Column
    private LocalDateTime M2;

    @Column
    private LocalDateTime M3;

    @Column
    private String M1status;

    @Column
    private String M2status;

    @Column
    private String M3status;

    @Transient
    private String Manager1;

    @Transient
    private String Manager2;

    @Transient
    private String Manager3;

}


