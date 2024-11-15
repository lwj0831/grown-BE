package vision.grown.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vision.grown.funding.OrderFunding;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String password;
    private String name;
    @Column(name = "phone_num")
    private String phoneNum;
    @Enumerated(EnumType.STRING)
    private MemberType memberType; // Default : NORMAL

    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<OrderFunding> orderFundingList = new ArrayList<>();

    public void updatePassword(String password){
        this.password = password;
    }

    @Builder
    public Member(String email, String password, String name, String phoneNum) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNum = phoneNum;
        this.memberType = MemberType.NORMAL;
    }
}
