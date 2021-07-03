package org.zerock.ex2.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import org.zerock.ex2.entity.Memo;

@SpringBootTest
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;

    // @Test
    // public void testClass(){
    //     System.out.println(memoRepository.getClass().getName());
    // }

    // @Test
    // public void testInsert(){
    //  IntStream.rangeClosed(1,100).forEach(i->{
    //     // Memo memo = Memo.builder().memoText("sample.."+i).build();
    //     Memo memo = Memo.builder().memoText("sample.."+i).build();
    //     memoRepository.save(memo);

    //  });
    // }


    // @Test
    // public void testPageDefault(){
    //     Pageable pageable = PageRequest.of(0, 10);
    //     Page<Memo> result = memoRepository.findAll(pageable);
    //     System.out.println(result);


       
    
    // }

    // @ Test
    // public void testQueryMethodes(){

    //     List <Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

    //     for(Memo memo : list){
    //         System.out.println(memo);
    //     }
    // }

    // @Test
    // public void testQueryMethodeWithPageable(){
    //     Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    //     Page<Memo> result = memoRepository.findByMnoBetween(10L, 20L, pageable);

    //     result.get().forEach(memo->System.out.println(memo));


    // }

    @Test
    public void testNativeQueryList(){

      
        List<Memo> result = memoRepository.getNativeResult();
        
        for(Memo memo : result){
            System.out.println(memo);
        }

    }



    
}
