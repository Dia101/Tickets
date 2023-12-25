package ru.dia101.achievementSheet.tables.achievementSheet;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dia101.achievementSheet.files.ResponseWithStatus;
import ru.dia101.achievementSheet.files.ServiceFunctions;
import ru.dia101.achievementSheet.files.StatusCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementSheetService {
    private final AchievementSheetRepo achievementSheetRepo;
    private final ServiceFunctions serviceFunctions;
    public List<AchievementSheet> findAllWeb(){
        return achievementSheetRepo.findAll();
    }
    public ResponseWithStatus<List<AchievementSheet>> findAll(){
        return ResponseWithStatus.create(200, achievementSheetRepo.findAll());
    }
    public ResponseWithStatus<AchievementSheet> findById(Long id, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(id, achievementSheetRepo::findById, request);
    }
    public ResponseWithStatus<AchievementSheet> findBySemester(String semester, HttpServletRequest request){
        return serviceFunctions.findByWithAuth(semester, achievementSheetRepo::findBySemester, request);
    }
    public StatusCode save(AchievementSheet achievementSheet, HttpServletRequest request){
        return serviceFunctions.saveWithAuth(
                achievementSheet,
                achievementSheet.getSemester(),
                achievementSheetRepo::findBySemester,
                achievementSheetRepo::save,
                request
        );
    }

    public StatusCode deleteById(Long id, HttpServletRequest request){
        return serviceFunctions.deleteByWithAuth(id, achievementSheetRepo::findById, achievementSheetRepo::deleteById, request);
    }


}