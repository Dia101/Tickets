package ru.dia101.achievementSheet.tables.achievementSheet;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.achievementSheet.files.ControllerFunctions;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/achievementSheets")
@RequiredArgsConstructor
public class AchievementSheetController {
    private final AchievementSheetService achievementSheetService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<AchievementSheet>>> findAll(){
        return ResponseEntity.ok(achievementSheetService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<AchievementSheet>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, achievementSheetService::findById, request);
    }

    @GetMapping(params = "semester")
    public ResponseEntity<ResponseWithStatus<AchievementSheet>> findBySemester(@RequestParam String semester, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(semester, achievementSheetService::findBySemester, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody AchievementSheet achievementSheet, HttpServletRequest request){
        return controllerFunctions.statusCode(achievementSheet, achievementSheetService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, achievementSheetService::deleteById, request);
    }

}
