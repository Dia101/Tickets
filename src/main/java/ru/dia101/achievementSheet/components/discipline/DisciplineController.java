package ru.dia101.achievementSheet.tables.discipline;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dia101.achievementSheet.files.ControllerFunctions;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.StatusCode;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
@RequiredArgsConstructor
public class DisciplineController {
    private final DisciplineService disciplineService;
    private final ControllerFunctions controllerFunctions;

    @GetMapping
    public ResponseEntity<ResponseWithStatus<List<Discipline>>> findAll(){
        return ResponseEntity.ok(disciplineService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWithStatus<Discipline>> findById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(id, disciplineService::findById, request);
    }

    @GetMapping(params = "discipline")
    public ResponseEntity<ResponseWithStatus<Discipline>> findByName(@RequestParam String disciplineName, HttpServletRequest request){
        return controllerFunctions.responseWithStatus(disciplineName, disciplineService::findByName, request);
    }

    @PostMapping
    public ResponseEntity<StatusCode> save(@RequestBody Discipline discipline, HttpServletRequest request){
        return controllerFunctions.statusCode(discipline, disciplineService::save, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<StatusCode> deleteById(@PathVariable Long id, HttpServletRequest request){
        return controllerFunctions.statusCode(id, disciplineService::deleteById, request);
    }

}
