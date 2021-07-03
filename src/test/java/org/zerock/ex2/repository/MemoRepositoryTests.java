package org.zerock.ex2.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.ex2.entity.Memo;

@SpringBootTest
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;

    // @Test
    // public void testClass(){
    //     System.out.println(memoRepository.getClass().getName());
    // }

    @Test
    public void testInsert(){
     IntStream.rangeClosed(1,100).forEach(i->{
        // Memo memo = Memo.builder().memoText("sample.."+i).build();
        Memo memo = Memo.builder().memoText("sample.."+i).build();
        memoRepository.save(memo);

     });
    }


    
}
