package com.yiyuclub.checkdata.controller;

import com.yiyuclub.checkdata.models.Checkdata;
import com.yiyuclub.checkdata.service.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class CheckController {
    @Autowired
    public ICheckService checkService;

    @GetMapping("getlist")
    public List<Checkdata> getList(String type) {
        try {
            List<Checkdata> list = checkService.getList(type);

            Optional<List<Checkdata>> checks = Optional.ofNullable(list);

            if (checks.isPresent()) {
                return list;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("checkdata")
    public int checkdata(Checkdata checkdata) {
        try {
            int c = checkService.checkdata(checkdata);
            return c;
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("getResult")
    public String getResult(int id) {
        try {
            String result = checkService.getResult(id);
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("getzxt")
    public HashMap getZxt(String type) {
        try {
            HashMap zxt = checkService.getZxt(type);
            return zxt;
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("deldata")
    public int delData(int idgroup) {
        try {
            int i = checkService.delData(idgroup);
            return i;
        } catch (Exception e) {
            return 0;
        }
    }

    @GetMapping("updatedata")
    public int updateData(Checkdata checkdata) {
        try {
            int i = checkService.updateData(checkdata);
            return i;
        } catch (Exception e) {
            return 0;
        }
    }
}
