package com.spring.mvc.chap04.repository;

import com.spring.mvc.chap04.entity.Grade;
import com.spring.mvc.chap04.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

@Repository("memoryRepository")
public class ScoreRepositoryImpl implements ScoreRepository {

    // 인메모리 저장공간 해시맵
    // key: 학번, value: 성적정보
    private static final Map<Integer, Score> scoreMap;

    // 학번 생성 일렬번호
    private static int seq;


    // 객체 초기화는 직접하는 것보다 주입 또는 생성자를 통해 처리하는게 좋다
    static {
        scoreMap = new HashMap<>();
        Score s1 = new Score("뽀로로", 100, 88, 33, ++seq, 0, 70.0, Grade.C);
        Score s2 = new Score("춘식이", 33, 99, 11, ++seq, 0, 80.0, Grade.B);
        Score s3 = new Score("쿠로미", 66, 55, 22, ++seq, 0, 50.0, Grade.A);

        scoreMap.put(s1.getStuNum(), s1);
        scoreMap.put(s2.getStuNum(), s2);
        scoreMap.put(s3.getStuNum(), s3);

    }

    @Override
    public List<Score> findAll() {
        // 맵에 있는 모든 성적 정보를 꺼내 리스트에 담아라
//        List<Score> temp = new ArrayList<>();
//        for (Integer key : scoreMap.keySet()) {
//            Score score = scoreMap.get(key);
//            temp.add(score);
//        }

        return new ArrayList<>(scoreMap.values())
                .stream()
                .sorted(comparing(Score::getStuNum))
                .collect(toList());
    }

    @Override
    public List<Score> findAll(String sort) {
        Comparator<Score> comparing = comparing(Score::getStuNum);

        switch (sort) {
            case "num":
                comparing = comparing(Score::getStuNum);
                break;
            case "name":
                comparing = comparing(Score::getName);
                break;
            case "avg":
                comparing = comparing(Score::getAverage).reversed();
                break;

        }
        return scoreMap.values().stream()
                .sorted(comparing)
                .collect(Collectors.toList())
                ;
    }

    @Override
    public boolean save(Score score) {
        // 학번 넣어주기
        score.setStuNum(++seq);
        scoreMap.put(score.getStuNum(), score);
        // 중복된 학번
        if (scoreMap.containsKey(score.getStuNum())) {
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(int stuNum) {
        // 없는 학번을 전달 받은 경우
        if (!scoreMap.containsKey(stuNum)) {
            return false;
        }
        scoreMap.remove(stuNum);
        return true;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public boolean edit(Score score, int stuNum) {
        scoreMap.replace(stuNum, score);
        return true;
    }

}
