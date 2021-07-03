package org.zerock.ex2.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.ex2.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo,Long> {
    
    //mno 사이값을 이용하여 역순으로 범위지정해서 값 구하기 
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
    
    Page<Memo> findByMnoBetween(Long from, Long to,  Pageable pageable);

    // void deleteMemoByMnoLessThan(Long num);

    @Query(value = "select * from tbl_memo where mno>0 " , nativeQuery = true)
    List<Memo> getNativeResult();
}
