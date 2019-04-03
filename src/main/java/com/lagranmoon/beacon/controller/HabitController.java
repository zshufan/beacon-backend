package com.lagranmoon.beacon.controller;

import com.lagranmoon.beacon.model.HabitDetailDto;
import com.lagranmoon.beacon.model.HabitDto;
import com.lagranmoon.beacon.model.HabitRequest;
import com.lagranmoon.beacon.model.ResponseDto;
import com.lagranmoon.beacon.service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Lagranmoon
 */
@RestController
public class HabitController {

    @Resource
    private HabitService habitService;

    @GetMapping("/habit")
    public ResponseEntity getHabitList(@SessionAttribute("openId") String openId) {

        List<HabitDto> habitList = habitService.getHabitListByOpenId(openId);

        return ResponseEntity.ok(
                ResponseDto.builder()
                        .data(habitList)
                        .build()
        );
    }

    @GetMapping("/habit/{id}")
    public ResponseEntity getHabitDetail(@SessionAttribute("openId") String openId,
                                         @PathVariable Long id) {

        HabitDetailDto habitDetail = habitService.getHabitDetailById(id);

        return ResponseEntity.ok(
                ResponseDto.builder()
                        .data(habitDetail)
                        .build()
        );
    }

    @PostMapping("/habit")
    public ResponseEntity saveHabit(@RequestBody @Valid HabitRequest habitRequest) {
        habitService.saveHabit(habitRequest);
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .msg("success")
                        .build()
        );
    }

}
