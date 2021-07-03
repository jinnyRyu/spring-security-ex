package org.zerock.ex2.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;//import 오류가 있다면 vscode에서 플러그인으로 해결
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
//builder를 이용해서 객체 생성하게 하려고, 이걸 사용하려면 모든생성자 추가랑 없는거랑 있어야지 컴파일 에러가 안남
@Entity//해당 클래스가 엔티티를 위한 클래스라는 말,자동으로 테이블 생성 가능
@Table(name = "tbl_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//사용자가 입력하는 값을 사용하는 경우가 아니라면 자동생성하기위해
    private Long mno;

    @Column(length = 200,nullable = false)
    private String memoText;


    
}
