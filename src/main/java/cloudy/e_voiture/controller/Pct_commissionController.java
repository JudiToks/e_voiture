package cloudy.e_voiture.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cloudy.e_voiture.models.Pct_commission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloudy.e_voiture.repository.Pct_commissionRepository;

@RestController
@RequestMapping("/Pct_commission")
public class Pct_commissionController {

    private final Pct_commissionRepository Pct_commissionRepository;

    @Autowired
    public Pct_commissionController(Pct_commissionRepository Pct_commissionRepository) {
        this.Pct_commissionRepository = Pct_commissionRepository;
    }

    @GetMapping("findById/{id}")
    public Pct_commission findById(@PathVariable int id) {
        return Pct_commissionRepository.findById(id);
    }

    @GetMapping("/all")
    public ArrayList<Pct_commission> findAll() {
        return (ArrayList<Pct_commission>)Pct_commissionRepository.findAll();
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        Pct_commissionRepository.deleteById(id);
    }



    @PostMapping("/save/{pct}/{datetime}")
    public Pct_commission save(@PathVariable int pct, @PathVariable String datetime) {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        LocalDateTime localDateTime = parseLocalDateTime(datetime, dateFormat);

        Timestamp maj = Timestamp.valueOf(localDateTime);

        Pct_commission pctCommission = new Pct_commission(pct, maj);
        return Pct_commissionRepository.save(pctCommission);
    }

    private static LocalDateTime parseLocalDateTime(String dateString, String dateFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDateTime.parse(dateString, formatter);
    }

}